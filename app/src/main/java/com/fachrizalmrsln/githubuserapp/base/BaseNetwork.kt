package com.fachrizalmrsln.githubuserapp.base

import com.fachrizalmrsln.githubuserapp.BuildConfig
import com.fachrizalmrsln.githubuserapp.data.remote.IRemoteSource
import com.fachrizalmrsln.githubuserapp.utils.constant.AUTHORIZATION
import com.fachrizalmrsln.githubuserapp.utils.constant.BASE_URL
import com.fachrizalmrsln.githubuserapp.utils.typeadapter.TypeInteger
import com.fachrizalmrsln.githubuserapp.utils.typeadapter.TypeString
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BaseNetwork {

    @Singleton
    @Provides
    fun initializeNetwork(): IRemoteSource {
        val gsonBuilder = GsonBuilder()
            .registerTypeAdapter(Int::class.java, TypeInteger())
            .registerTypeAdapter(String::class.java, TypeString())
            .serializeNulls()

        val mInterceptor = HttpLoggingInterceptor()
        mInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addNetworkInterceptor {
                val requestBuilder: Request.Builder = it.request().newBuilder()
                requestBuilder.addHeader(AUTHORIZATION, BuildConfig.API_KEY)
                it.proceed(requestBuilder.build())
            }
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
        client.addInterceptor(mInterceptor)

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .build().create(IRemoteSource::class.java)
    }

}