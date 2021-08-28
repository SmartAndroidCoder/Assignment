package com.shaadi.assignment.data.model.userMaches


import androidx.room.Embedded
import com.google.gson.annotations.SerializedName


data class Location(
    @SerializedName("street")
    @Embedded(prefix = "street_")
    val street: Street,

    @SerializedName("city")
    val city: String,

    @SerializedName("state")
    val state: String,

    @SerializedName("country")
    val country: String,

    @SerializedName("postcode")
    val postcode: String,

    @SerializedName("coordinates")
    @Embedded(prefix = "coordinates_")
    val coordinates: Coordinates,

    @SerializedName("timezone")
    @Embedded(prefix = "timezone_")
    val timezone: Timezone
)