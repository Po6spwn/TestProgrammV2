package com.example.testprogrammv2.login.model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testprogrammv2.login.repository.LoginRepository
import com.example.testprogrammv2.room.UserDB
import com.example.testprogrammv2.util.StatusForm
import com.example.testprogrammv2.util.Validate

class LoginViewModel :ViewModel() {

//    var liveDataLogin: LiveData<UserDB>? = null

    private val _liveDataLogin = MutableLiveData<UserDB?>()
    var liveDataLogin: LiveData<UserDB?> = _liveDataLogin

    private val _logForm = MutableLiveData<StatusForm>()
    val loginForm: LiveData<StatusForm> = _logForm


    fun getLoginDetails(context: Context, email: String, password: String) {
        _liveDataLogin.value = LoginRepository.getLoginDetails(context, email, password)
    }

     fun isDataChanged(email:String) {
       _logForm.value = Validate().isValidate(email, null, null)
    }
}