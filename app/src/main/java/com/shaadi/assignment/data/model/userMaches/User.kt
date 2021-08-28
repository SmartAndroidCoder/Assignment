package com.shaadi.assignment.data.model.userMaches

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class User(
    @SerializedName("gender")
    val gender: String,

    @SerializedName("name")
    @Embedded(prefix = "name_")
    val name: Name,

    @SerializedName("location")
    @Embedded(prefix = "location_")
    val location: Location,

    @PrimaryKey
    @SerializedName("email")
    val email: String,

    @SerializedName("login")
    @Embedded(prefix = "login_")
    val login: Login?,

    @SerializedName("dob")
    @Embedded(prefix = "dob_")
    val dob: Dob,

    @SerializedName("registered")
    @Embedded(prefix = "registered_")
    val registered: Registered?,

    @SerializedName("phone")
    val phone: String?,

    @SerializedName("cell")
    val cell: String?,

    @SerializedName("id")
    @Embedded(prefix = "id_")
    val id: Id?,

    @SerializedName("picture")
    @Embedded(prefix = "picture_")
    val picture: Picture?,

    @SerializedName("nat")
    val nat: String?,
    @ColumnInfo(name = "match_status")
    var matchStatus: Int = -1
)