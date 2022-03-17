package com.fachrizalmrsln.githubuserapp.presentation.splash_screen

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.fachrizalmrsln.githubuserapp.utils.SPLASH_SCREEN_DURATION
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor() : ViewModel(), LifecycleObserver {

    val mLaunchToHome: Flow<Boolean> = flow {
        delay(SPLASH_SCREEN_DURATION)
        emit(true)
    }

}