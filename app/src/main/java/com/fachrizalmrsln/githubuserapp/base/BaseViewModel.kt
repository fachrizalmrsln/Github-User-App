package com.fachrizalmrsln.githubuserapp.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope {

    companion object {
        const val INITIAL = "INITIAL"
        const val EXCEPTION = "EXCEPTION"
        const val CANCEL = "CANCEL"
    }
    private val _loadingStatus = MutableLiveData<Boolean>()
    val loadingStatus: LiveData<Boolean> = _loadingStatus

    protected val _messageToUI = MutableLiveData<String>()
    val messageToUI: LiveData<String> = _messageToUI

    lateinit var mScope: CompletableJob
    private fun setupJob() {
        mScope = Job()
        mScope.invokeOnCompletion {
            if (it?.message == INITIAL) {
                _loadingStatus.value = false
                _loadingStatus.value = true
            } else _loadingStatus.value = false
        }
    }

    private fun cancelJob(cancelMessage: String = CANCEL) {
        if (mScope.isActive) mScope.cancel(CancellationException(cancelMessage))
    }

    protected fun restartJob(restartMessage: String = EXCEPTION) {
        cancelJob(restartMessage)
        setupJob()
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + mScope

    init {
        setupJob()
    }

    override fun onCleared() {
        super.onCleared()
        cancelJob()
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