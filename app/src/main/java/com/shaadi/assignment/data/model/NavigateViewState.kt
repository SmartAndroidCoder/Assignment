package com.shaadi.assignment.data.model

import androidx.annotation.StringRes
import com.shaadi.assignment.data.model.userMaches.User

sealed class NavigateViewState {
    data class UserListSuccess(
        val users: List<User>
    ) : NavigateViewState()

    data class ErrorUserMessage(
        val errMsg: String? = null,
        @StringRes val errMsgId: Int? = null
    ) : NavigateViewState()

    data class UpdateUserSuccess(
        val status: Boolean
    ) : NavigateViewState()

    data class UpdateErrorMessage(
        val errMsg: String? = null,
        @StringRes val errMsgId: Int? = null
    ) : NavigateViewState()
}