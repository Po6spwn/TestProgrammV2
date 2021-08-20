package com.example.testprogrammv2.login.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.testprogrammv2.room.RoomDB
import com.example.testprogrammv2.room.UserDAO
import com.example.testprogrammv2.room.UserDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class LoginRepository (private val userDao: UserDAO) {

    companion object {

        var database: RoomDB? = null


        fun initializeDB(context: Context) : RoomDB {
            return RoomDB.getDatabase(context)
        }


        fun getLoginDetails(context: Context, email: String, password: String) : UserDB? {
            database = initializeDB(context)
            return runBlocking {
                database!!.userDao().getUser(email, password)
            }
        }

    }
}