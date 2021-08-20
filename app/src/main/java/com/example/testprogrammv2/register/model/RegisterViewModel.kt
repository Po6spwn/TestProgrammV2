package com.example.testprogrammv2.register.model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testprogrammv2.R
import com.example.testprogrammv2.register.model.repository.RegisterRepository
import com.example.testprogrammv2.room.UserDB
import com.example.testprogrammv2.util.StatusForm
import com.example.testprogrammv2.util.Validate

class RegisterViewModel : ViewModel()
{

    // Сделано так, что обезопасить изменение данных во внешних классах
    private val _regForm = MutableLiveData<StatusForm>()
    val registerForm: LiveData<StatusForm> = _regForm


    fun registration(context: Context, email: String, username: String, password: String) {
        RegisterRepository.insertData(context, email,username,password)
    }


    fun isDataChanged(email:String, username: String, password: String) {
        _regForm.value = Validate().isValidate(email, username, password)
    }

}