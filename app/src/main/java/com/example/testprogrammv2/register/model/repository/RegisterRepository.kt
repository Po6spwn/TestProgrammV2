package com.example.testprogrammv2.register.model.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.testprogrammv2.room.RoomDB
import com.example.testprogrammv2.room.UserDAO
import com.example.testprogrammv2.room.UserDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

// класс для обработки полученных от Источника данных (Объекта).

class RegisterRepository (private val userDao: UserDAO) {

    companion object {

        var database: RoomDB? = null

        private fun initializeDB(context: Context) : RoomDB {
            return RoomDB.getDatabase(context)
        }

        fun insertData(context: Context, username: String, email: String, password: String) {
            database = initializeDB(context)
            CoroutineScope(IO).launch {
                val loginDetails = UserDB(username = username, email = email, password = password)
                database!!.userDao().addUser(loginDetails)
            }

        }
    }
}