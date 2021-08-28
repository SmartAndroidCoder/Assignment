package com.shaadi.assignment.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shaadi.assignment.data.model.userMaches.User
import com.shaadi.assignment.data.room.dao.UsersResponseDao


@Database(
    entities = [User::class],
    version = 1, exportSchema = false
)
//@TypeConverters(DataTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getUsersResponseDao(): UsersResponseDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "shadi-db")
                .build()
        }
    }
}
