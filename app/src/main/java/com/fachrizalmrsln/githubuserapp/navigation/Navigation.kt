package com.fachrizalmrsln.githubuserapp.navigation

import android.app.Activity
import android.content.Intent
import com.fachrizalmrsln.githubuserapp.presentation.MainActivity

fun Activity.navigateToHome(clearStack: Boolean = false) {
    startActivity(
        Intent(this, MainActivity::class.java)
    )
    if (clearStack) finish()
}