package com.shaadi.assignment.data.model.userMaches

import com.google.gson.annotations.SerializedName


data class Registered(
    @SerializedName("date")
    val date: String?,

    @SerializedName("age")
    val age: Int?
)