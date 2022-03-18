package com.fachrizalmrsln.githubuserapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

abstract class BaseActivity<T: ViewBinding> : AppCompatActivity() {

    private var _mBinding: ViewBinding? = null
    @Suppress("UNCHECKED_CAST")
    protected val mBinding: T
            get() = _mBinding as T

   private var _mActivityScope: CoroutineScope? = null
   protected val mActivityScope: CoroutineScope
            get() = _mActivityScope!!

    abstract val mBindingInflater: (LayoutInflater) -> T
    abstract fun initializeViews()
    abstract fun networkError()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewBinding()
        setupActivityScope()

        initializeViews()
        networkError()
    }

    private fun setupViewBinding() {
        _mBinding = mBindingInflater.invoke(layoutInflater)
        setContentView(requireNotNull(_mBinding).root)
    }

    private fun setupActivityScope() {
        _mActivityScope = CoroutineScope(Dispatchers.Main)
    }

    fun showToastShort(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun showToastLength(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }

}