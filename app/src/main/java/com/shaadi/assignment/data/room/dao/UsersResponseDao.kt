package com.shaadi.assignment.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shaadi.assignment.data.model.userMaches.User

@Dao
interface UsersResponseDao {
    @Query("Select * from User")
    suspend fun getUsers(): List<User>

    @Query("UPDATE User SET match_status = :status WHERE email = :email")
    suspend fun updateStatus(email: String, status: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(result: List<User>)
}
