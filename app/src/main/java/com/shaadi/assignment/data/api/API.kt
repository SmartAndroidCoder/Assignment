package com.shaadi.assignment.data.api

import com.shaadi.assignment.data.model.userMaches.UsersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    @GET("api")
    suspend fun getUsers(@Query("results") results: Int): Response<UsersResponse>

}