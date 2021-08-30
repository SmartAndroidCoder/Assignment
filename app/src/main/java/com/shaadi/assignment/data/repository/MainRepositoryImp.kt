package com.shaadi.assignment.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.shaadi.assignment.data.api.API
import com.shaadi.assignment.data.api.APIResult
import com.shaadi.assignment.data.model.userMaches.Info
import com.shaadi.assignment.data.model.userMaches.UsersResponse
import com.shaadi.assignment.data.room.dao.UsersResponseDao
import com.shaadi.assignment.ui.BaseActivity
import kotlinx.coroutines.*
import okhttp3.ResponseBody.Companion.toResponseBody
import kotlin.coroutines.CoroutineContext

class MainRepositoryImp(
    private val usersResponseDao: UsersResponseDao,
    private val api: API
) : MainRepository {

    private val backgroundContext: CoroutineContext by lazy { Dispatchers.IO }
    private val foregroundContext: CoroutineContext by lazy { Dispatchers.Main }

    private var fetchUserJob: CompletableJob? = null
    private var updateUserJob: CompletableJob? = null


    override fun getUsers(userCount: Int): LiveData<APIResult> {
        fetchUserJob = Job()
        return object : LiveData<APIResult>() {
            override fun onActive() {
                fetchUserJob?.let { job ->
                    CoroutineScope(backgroundContext + job).launch {
//                        delay(10000)
                        val result: APIResult? = try {
                            //Data From LocaleDatabase
                            val users = usersResponseDao.getUsers()
                            Log.d(BaseActivity.TAG, "getUsers=====>${users.size}")

                            if (!users.isNullOrEmpty()) {
                                APIResult.Success(
                                    UsersResponse(
                                        users = users,
                                        info = Info("", userCount, 1, "1")
                                    )
                                )
                            } else {
                                val response = api.getUsers(userCount)
                                if (response.isSuccessful) {
                                    val usersResponse = response.body()
                                    usersResponse?.let { usersResponseDao.insertUsers(it.users) }
                                    APIResult.Success(usersResponse)
                                } else
                                    APIResult.Error(response.errorBody())
                            }
                        } catch (exception: Exception) {
                            APIResult.Error("Something went wrong".toResponseBody())
                        }

                        withContext(foregroundContext) {
                            value = result
                            job.complete()
                        }
                    }
                }
            }
        }
    }

    // For updating user data
    private suspend fun refreshUser(userCount: Int) {

    }


    override fun updateStatus(email: String, status: Int): LiveData<APIResult> {
        updateUserJob = Job()
        return object : LiveData<APIResult>() {
            override fun onActive() {
                updateUserJob?.let { job ->
                    CoroutineScope(backgroundContext + job).launch {

                        val result: APIResult? = try {
                            usersResponseDao.updateStatus(email, status)
                            APIResult.Success(true)

                        } catch (exception: Exception) {
                            APIResult.Error("Something went wrong".toResponseBody())
                        }

                        withContext(foregroundContext) {
                            value = result
                            job.complete()
                        }
                    }
                }
            }
        }

    }


    override fun cancelUserActiveJob() {
        fetchUserJob?.let {
            if (it.isActive)
                it.cancel()
        }

        updateUserJob?.let {
            if (it.isActive)
                it.cancel()
        }
    }


}