package com.fachrizalmrsln.githubuserapp.presentation.splash_screen

import android.annotation.SuppressLint
import android.view.LayoutInflater
import com.fachrizalmrsln.githubuserapp.base.BaseActivity
import com.fachrizalmrsln.githubuserapp.databinding.ActivitySplashScreenBinding
import com.fachrizalmrsln.githubuserapp.navigation.navigateToHome
import com.fachrizalmrsln.githubuserapp.utils.constant.SPLASH_SCREEN_DURATION
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreen : BaseActivity<ActivitySplashScreenBinding>() {

    override val mBindingInflater: (LayoutInflater) -> ActivitySplashScreenBinding
        get() = ActivitySplashScreenBinding::inflate

    override fun initializeViews() {
        launch {
            delay(SPLASH_SCREEN_DURATION)
            navigateToHome(clearStack = true)
        }
    }

    override fun networkError() {}

}