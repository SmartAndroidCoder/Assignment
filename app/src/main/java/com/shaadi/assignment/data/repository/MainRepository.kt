package com.shaadi.assignment.data.repository

import androidx.lifecycle.LiveData
import com.shaadi.assignment.data.api.APIResult

interface MainRepository {

    fun getUsers(userCount: Int): LiveData<APIResult>
    fun cancelUserActiveJob()
    fun updateStatus(email: String, status: Int): LiveData<APIResult>

}