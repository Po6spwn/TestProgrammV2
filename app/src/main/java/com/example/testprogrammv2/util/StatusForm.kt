package com.example.testprogrammv2.util

data class StatusForm (
    val usernameError: Int? = null,
    val emailError: Int? = null,
    val passwordError: Int? = null,
    val isOk: Boolean = false
)