package com.fachrizalmrsln.githubuserapp.navigation

import android.app.Activity
import android.content.Intent
import com.fachrizalmrsln.githubuserapp.presentation.HomePageActivity

fun Activity.navigateToHome(clearStack: Boolean = false) {
    startActivity(
        Intent(this, HomePageActivity::class.java)
    )
    if (clearStack) finish()
}