package com.fachrizalmrsln.githubuserapp.presentation.splash_screen

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.fachrizalmrsln.githubuserapp.R
import com.fachrizalmrsln.githubuserapp.navigation.navigateToHome
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreen : AppCompatActivity() {

    private val mViewModel: SplashScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        initiateScreen()
    }

    private fun initiateScreen() = CoroutineScope(Dispatchers.Main).launch {
        mViewModel.mLaunchToHome.collect { isLaunch ->
            if (isLaunch) navigateToHome(clearStack = true)
            finish()
        }
    }

}