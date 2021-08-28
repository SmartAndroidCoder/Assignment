package com.shaadi.assignment.data.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shaadi.assignment.data.model.userMaches.Name

class DataTypeConverters {
    companion object {
        var gson = Gson()

        @TypeConverter
        fun stringToName(data: String?): Name {
            val listType = object : TypeToken<Name?>() {}.type
            return gson.fromJson(data, listType)
        }

    }
}