package com.fachrizalmrsln.githubuserapp.base

import retrofit2.Response

open class BaseRepository(private val networkStateManager: NetworkStateManager) {

    fun <T : Any, DATA : T> Response<DATA>.toResult(): BaseNetworkResponse<T> {
        return try {
            if (networkStateManager.isOnline()) {
                val response = this
                if (response.isSuccessful)
                    onResultSuccess(response.body(), response.message())
                else {
                    val dataString = response.errorBody()?.charStream()?.readText()
                    onResultError(dataString, response.message())
                }
            } else BaseNetworkResponse.NoNetwork(Exception("No Internet"))

        } catch (e: Exception) {
            BaseNetworkResponse.Exception(e)
        }
    }

    open fun <T : Any> onResultSuccess(body: T?, message: String): BaseNetworkResponse<T> {
        return BaseNetworkResponse.Ok(body, message)

    }

    open fun <T : Any> onResultError(body: String?, message: String): BaseNetworkResponse<T> {
        return BaseNetworkResponse.Exception(Exception(message))
    }


}