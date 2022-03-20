package com.fachrizalmrsln.githubuserapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity<T : ViewBinding> : AppCompatActivity(), CoroutineScope {

    private var _mBinding: ViewBinding? = null

    @Suppress("UNCHECKED_CAST")
    protected val mBinding: T
        get() = _mBinding as T

    private lateinit var mScope: CompletableJob
    private fun setupJob() {
        mScope = Job()
    }

    private fun cancelJob() {
        if (mScope.isActive) mScope.cancel()
    }

    protected fun restartJob() {
        cancelJob()
        setupJob()
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + mScope

    abstract val mBindingInflater: (LayoutInflater) -> T
    abstract fun initializeViews()
    abstract fun networkError()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewBinding()
        setupJob()

        initializeViews()
        networkError()
    }

    private fun setupViewBinding() {
        _mBinding = mBindingInflater.invoke(layoutInflater)
        setContentView(requireNotNull(_mBinding).root)
    }

    fun showToastShort(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun showToastLong(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        cancelJob()
        _mBinding = null
    }

}