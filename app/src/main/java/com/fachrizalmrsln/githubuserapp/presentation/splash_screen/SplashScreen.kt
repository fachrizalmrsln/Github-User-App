package com.fachrizalmrsln.githubuserapp.presentation.splash_screen

import android.annotation.SuppressLint
import android.os.Bundle
import com.fachrizalmrsln.githubuserapp.R
import com.fachrizalmrsln.githubuserapp.base.BaseClass
import com.fachrizalmrsln.githubuserapp.navigation.navigateToHome
import com.fachrizalmrsln.githubuserapp.utils.constant.SPLASH_SCREEN_DURATION
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreen : BaseClass() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        delayingScreen()
    }

    private fun delayingScreen() = mScopeMain.launch {
        delay(SPLASH_SCREEN_DURATION)
        navigateToHome(clearStack = true)
    }

}