package com.shaadi.assignment.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.shaadi.assignment.R

abstract class BaseActivity : AppCompatActivity() {

    private var doubleBackToExitPressedOnce = false

    @get:IdRes
    abstract val rootViewId: Int

    abstract fun init()
    abstract fun initObservers()
    abstract fun initDestroy()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        initObservers()
    }

    override fun onDestroy() {
        initDestroy()
        super.onDestroy()
    }

    override fun onBackPressed() {
        if (!doubleBackToExitPressedOnce) {
            doubleBackToExitPressedOnce = true
            Snackbar.make(
                findViewById(rootViewId),
                getString(R.string.back_string),
                Snackbar.LENGTH_SHORT
            ).show()
            Handler(Looper.getMainLooper()).postDelayed({
                doubleBackToExitPressedOnce = false
            }, 2000)
        } else {
            super.onBackPressed()
        }
    }
}