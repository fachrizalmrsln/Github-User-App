package com.fachrizalmrsln.githubuserapp.presentation.splash_screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import com.fachrizalmrsln.githubuserapp.base.BaseActivity
import com.fachrizalmrsln.githubuserapp.databinding.ActivitySplashScreenBinding
import com.fachrizalmrsln.githubuserapp.navigation.navigateToHome
import com.fachrizalmrsln.githubuserapp.utils.constant.SPLASH_SCREEN_DURATION
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreen : BaseActivity<ActivitySplashScreenBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        delayingScreen()
    }

    override val mBindingInflater: (LayoutInflater) -> ActivitySplashScreenBinding
        get() = ActivitySplashScreenBinding::inflate

    private fun delayingScreen() = mScopeMain.launch {
    }

    override fun activityScope(scope: CoroutineScope) {
        scope.launch {
            delay(SPLASH_SCREEN_DURATION)
            navigateToHome(clearStack = true)
        }
    }

}