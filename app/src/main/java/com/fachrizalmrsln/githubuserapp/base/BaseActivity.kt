package com.fachrizalmrsln.githubuserapp.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

abstract class BaseActivity<T: ViewBinding> : AppCompatActivity() {

    private var _mBinding: ViewBinding? = null
    abstract val mBindingInflater: (LayoutInflater) -> T

    @Suppress("UNCHECKED_CAST")
    protected val mBinding: T
            get() = _mBinding as T

    lateinit var mScopeMain: CoroutineScope
    abstract fun activityScope(scope: CoroutineScope)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _mBinding = mBindingInflater.invoke(layoutInflater)
        setContentView(requireNotNull(_mBinding).root)

        mScopeMain = CoroutineScope(Dispatchers.Main)
        activityScope(mScopeMain)
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }

}