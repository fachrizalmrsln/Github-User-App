package com.fachrizalmrsln.githubuserapp.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fachrizalmrsln.githubuserapp.model.Message
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope {

    private val _loadingStatus = MutableLiveData<Boolean>()
    val loadingStatus: LiveData<Boolean> = _loadingStatus

    protected val _stringMessage = MutableLiveData<String>()
    val stringMessage: LiveData<String> = _stringMessage

    protected val _message = MutableLiveData<Message>()
    val message: LiveData<Message> = _message

    protected val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    protected fun showLoading() {
        _loadingStatus.value = true
    }

    protected fun hideLoading() {
        if (_loadingStatus.value == true) {
            _loadingStatus.value = false
        }
    }

    protected suspend fun <T> BaseNetworkResponse<T>.onResult(result: suspend (T?, code: Int, message: String) -> Unit) {
        when (this) {
            is BaseNetworkResponse.Ok -> result.invoke(value, 200, "")
            is BaseNetworkResponse.Error -> _message.value = Message.Dialog(response.message)
            is BaseNetworkResponse.Exception -> _message.value = Message.Dialog(exception.message)
            is BaseNetworkResponse.ErrorCode -> errorResultCodeHandle(message)
            is BaseNetworkResponse.NoNetwork -> _message.value = Message.Dialog(exception.message)
        }
    }

    protected fun errorResultCodeHandle(message: String) {
        _message.value = Message.Dialog(message)
    }

}