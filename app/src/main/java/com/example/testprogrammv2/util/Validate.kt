package com.example.testprogrammv2.util

import com.example.testprogrammv2.R

class Validate() {

    fun isValidate(email:String, username: String?, password: String?): StatusForm
    {
        if (username?.isEmpty() == true) {
            return StatusForm(usernameError = R.string.invalid_username)
        }
        else if (!isEmailValid(email)) {
            return StatusForm(emailError = R.string.invalid_email)
        }
        else if (password?.let { isPasswordValid(it) } == false) {
            return StatusForm(passwordError = R.string.invalid_password)
        }
        else {
           return StatusForm(isOk = true)
        }
    }

    private fun isPasswordValid (password: String) : Boolean {
        // Хотяб одна заглавная, строчная, цифра.
        val PASSWORD_REGEX = """^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8,}$""".toRegex()
        return PASSWORD_REGEX.matches(password)
    }

    private fun isEmailValid(email: String): Boolean {
        val EMAIL_REGEX = """[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,6}""".toRegex()
        return EMAIL_REGEX.matches(email)
    }
}