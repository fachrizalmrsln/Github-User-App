package com.fachrizalmrsln.githubuserapp.navigation

import android.app.Activity
import android.content.Intent
import com.fachrizalmrsln.githubuserapp.presentation.detail_page.DetailPageActivity
import com.fachrizalmrsln.githubuserapp.presentation.home_page.HomePageActivity

fun Activity.navigateToHome(clearStack: Boolean = false) {
    startActivity(
        Intent(this, HomePageActivity::class.java)
    )
    if (clearStack) finish()
}

fun Activity.navigateToDetail(userName: String) {
    startActivity(
        Intent(this, DetailPageActivity::class.java).apply {
            putExtra(DetailPageActivity.ARGUMENT_USER_NAME, userName)
        }
    )
}