package com.fachrizalmrsln.githubuserapp.app

import android.app.Application
import com.fachrizalmrsln.githubuserapp.data.local.repository.IRemoteRepository
import com.fachrizalmrsln.githubuserapp.data.local.repository.RemoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@HiltAndroidApp
class GitHubUserAppApplication: Application()

@Module
@InstallIn(SingletonComponent::class)
abstract class GitHubUserAppModule {

    @Binds
    @Singleton
    abstract fun initializeRepository(
        remoteRepository: RemoteRepository
    ): IRemoteRepository

}
