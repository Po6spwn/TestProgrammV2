package com.example.testprogrammv2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testprogrammv2.login.LoginFragment


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.mainView, LoginFragment())
                .commit()
        }
    }
}