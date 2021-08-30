package com.shaadi.assignment.ui.main.viewmodel

import android.util.Log
import com.shaadi.assignment.data.api.APIResult
import com.shaadi.assignment.data.model.BaseViewModel
import com.shaadi.assignment.data.model.NavigateViewState
import com.shaadi.assignment.data.model.ProcessLoadingState
import com.shaadi.assignment.data.model.userMaches.UsersResponse
import com.shaadi.assignment.data.repository.MainRepository
import com.shaadi.assignment.ui.BaseActivity.Companion.TAG
import com.shaadi.assignment.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : BaseViewModel() {

init {
    fetchUsers(10)
}
    fun fetchUsers(userCount: Int) {
        loadingState.value = ProcessLoadingState.ShowLoading
        val source = mainRepository.getUsers(userCount)
//        navigateState.removeSource(source)
        navigateState.addSource(source) { result ->
            when (result) {
                is APIResult.Success<*> -> {
                    navigateState.value =
                        NavigateViewState.UserListSuccess((result.response as UsersResponse).users)
                }
                is APIResult.Error -> {
                    navigateState.value = NavigateViewState.ErrorUserMessage()
                }
            }
            loadingState.value = ProcessLoadingState.HideLoading
        }
    }

    fun cancelJobs() {
//        mainRepository.cancelUserActiveJob()
    }

    fun updateMatchStatus(email: String, status: Int) {

        loadingState.value = ProcessLoadingState.ShowLoading
        val source = mainRepository.updateStatus(email, status)
//        navigateState.removeSource(source)
        navigateState.addSource(source) { result ->
            when (result) {
                is APIResult.Success<*> -> {
                    navigateState.value =
                        NavigateViewState.UpdateUserSuccess(result.response as Boolean)
                }
                is APIResult.Error -> {
                    navigateState.value = NavigateViewState.UpdateErrorMessage()
                }
            }
            loadingState.value = ProcessLoadingState.HideLoading
        }
    }

    override fun onCleared() {
        mainRepository.cancelUserActiveJob()
        super.onCleared()
    }

}