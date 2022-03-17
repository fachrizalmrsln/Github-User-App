package com.fachrizalmrsln.githubuserapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

abstract class BaseClass : AppCompatActivity() {

    lateinit var mScopeMain: CoroutineScope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mScopeMain = CoroutineScope(Dispatchers.Main)
    }

}