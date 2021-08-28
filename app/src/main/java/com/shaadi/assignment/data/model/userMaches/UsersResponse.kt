package com.shaadi.assignment.data.model.userMaches

import com.google.gson.annotations.SerializedName


data class UsersResponse(
    @SerializedName("results")
    val users: List<User>,
    @SerializedName("info")
    val info: Info
)