package com.fachrizalmrsln.githubuserapp.presentation.splash_screen

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor() : ViewModel(), LifecycleObserver {

    private val mSplashScreenDuration = 3000L
    val mLaunchToHome: Flow<Boolean> = flow {
        delay(mSplashScreenDuration)
        emit(true)
    }

}