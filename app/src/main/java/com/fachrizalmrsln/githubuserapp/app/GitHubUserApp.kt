package com.fachrizalmrsln.githubuserapp.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GitHubUserApp: Application() {

    override fun onCreate() {
        super.onCreate()
    }

}