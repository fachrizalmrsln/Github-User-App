package com.fachrizalmrsln.githubuserapp.presentation

import android.os.Bundle
import com.fachrizalmrsln.githubuserapp.R
import com.fachrizalmrsln.githubuserapp.base.BaseClass
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomePageActivity : BaseClass() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
    }

}