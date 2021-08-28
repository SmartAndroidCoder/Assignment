package com.shaadi.assignment.data.model.userMaches

import com.google.gson.annotations.SerializedName


data class Id(
    @SerializedName("name")
    val name: String?,
    @SerializedName("value")
    val value: String?
)