package com.fachrizalmrsln.githubuserapp.app

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.fachrizalmrsln.githubuserapp.data.local.database.DatabaseInstance
import com.fachrizalmrsln.githubuserapp.data.local.repository.ILocalRepository
import com.fachrizalmrsln.githubuserapp.data.local.repository.LocalRepository
import com.fachrizalmrsln.githubuserapp.data.remote.repository.IRemoteRepository
import com.fachrizalmrsln.githubuserapp.data.remote.repository.RemoteRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@HiltAndroidApp
class GitHubUserAppApplication: Application()

@Module
@InstallIn(SingletonComponent::class)
abstract class GitHubUserAppModule {

    @Binds
    @Singleton
    abstract fun bindsRemoteRepository(
        remoteRepository: RemoteRepository
    ): IRemoteRepository

    @Binds
    @Singleton
    abstract fun bindsLocalRepository(
        localRepository: LocalRepository
    ): ILocalRepository

}

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        DatabaseInstance::class.java,
        "GitHubUserApp"
    ).build()

    @Provides
    @Singleton
    fun provideDatabaseInstance(db: DatabaseInstance) = db.databaseObject()

}
