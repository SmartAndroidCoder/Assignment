package com.shaadi.assignment.data.model

sealed class ProcessLoadingState {
    object ShowLoading : ProcessLoadingState()
    object HideLoading : ProcessLoadingState()
}
