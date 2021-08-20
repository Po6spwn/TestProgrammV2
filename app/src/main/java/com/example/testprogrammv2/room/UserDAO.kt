package com.example.testprogrammv2.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: UserDB)

    @Query("SELECT * FROM user_table WHERE email =:email and password =:password")
    suspend fun getUser(email: String, password: String): UserDB
}