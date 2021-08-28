package com.shaadi.assignment.data.api

import okhttp3.ResponseBody

sealed class APIResult {

    data class Success<T>(
        val response: T?
    ) : APIResult()

    data class Error(
        val errorBody: ResponseBody?
    ) : APIResult()
}