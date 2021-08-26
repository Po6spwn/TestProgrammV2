package com.example.testprogrammv2.util

import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import com.google.android.material.snackbar.Snackbar

class BackListener (_view : View, _fragment: FragmentActivity, _lifecycle:LifecycleOwner) {

    private var view : View = _view
    private var fragment: FragmentActivity = _fragment
    private var lifecycle: LifecycleOwner = _lifecycle

    private var backPressed: Long = 0

    init {
        fragment.onBackPressedDispatcher.addCallback(lifecycle) {
           back()
        }
    }


    private fun back()
    {
        if (backPressed.plus(3000) > System.currentTimeMillis()) {
            fragment.finish()
        }else{
            Snackbar.make(view, "Click again to exit", Snackbar.LENGTH_LONG).show()
        }

        backPressed = System.currentTimeMillis()
    }
}