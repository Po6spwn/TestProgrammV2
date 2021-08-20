package com.example.testprogrammv2.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class NavFragment (_fragmentManager: FragmentManager, _id_view: Int) {

    private val fragmentManager: FragmentManager = _fragmentManager
    private val idView: Int = _id_view

    fun openFragment(fragment: Fragment)
    {
        fragmentManager.beginTransaction()
            .replace(idView, fragment)
            .addToBackStack(null)
            .commit()
    }
}