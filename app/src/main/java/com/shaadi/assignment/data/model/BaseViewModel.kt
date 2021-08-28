package com.shaadi.assignment.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    protected val navigateState = MediatorLiveData<NavigateViewState>()
    val navigate: LiveData<NavigateViewState> get() = navigateState

    protected val loadingState = MutableLiveData<ProcessLoadingState>()
    val loading: LiveData<ProcessLoadingState> get() = loadingState
}