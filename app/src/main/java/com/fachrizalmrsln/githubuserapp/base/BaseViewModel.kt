package com.fachrizalmrsln.githubuserapp.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope {

    private val _loadingStatus = MutableLiveData<Boolean>()
    val loadingStatus: LiveData<Boolean> = _loadingStatus

    protected val _messageToUI = MutableLiveData<String>()
    val messageToUI: LiveData<String> = _messageToUI

    private val job = Job()
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

}