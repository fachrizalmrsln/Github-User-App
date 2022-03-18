package com.fachrizalmrsln.githubuserapp.base

import okhttp3.Response
import retrofit2.HttpException

sealed class BaseNetworkResponse<out T : Any?> {

    class Ok<out T : Any>(
        val value: T?,
        val message: String = ""
    ) : BaseNetworkResponse<T>() {
        override fun toString() = "Result.Ok{value=$value, response=$message}"
    }

    class Error(
        override val exception: HttpException,
        override val response: Response
    ) : BaseNetworkResponse<Nothing>(),
        ErrorResult,
        ResponseResult {
        override fun toString() = "Result.Error{exception=$exception}"
    }

    class ErrorCode(
        val code: Int,
        val message: String
    ) : BaseNetworkResponse<Nothing>() {
        override fun toString() = "Result.TokenExpired{$message}"
    }

    class NoNetwork(
        val exception: Throwable
    ) : BaseNetworkResponse<Nothing>() {
        override fun toString() = "No Network Available"
    }

    class Exception(
        val exception: Throwable
    ) : BaseNetworkResponse<Nothing>() {
        override fun toString() = "Result.Exception{$exception}"
    }

}

interface ResponseResult {
    val response: Response
}

interface ErrorResult {
    val exception: Throwable
}
