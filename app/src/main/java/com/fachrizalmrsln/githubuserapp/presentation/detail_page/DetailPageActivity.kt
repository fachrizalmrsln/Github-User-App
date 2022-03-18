package com.fachrizalmrsln.githubuserapp.presentation.detail_page

import android.view.LayoutInflater
import com.fachrizalmrsln.githubuserapp.base.BaseActivity
import com.fachrizalmrsln.githubuserapp.databinding.ActivityDetailPageBinding
import com.fachrizalmrsln.githubuserapp.utils.data.getStringExtra
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPageActivity : BaseActivity<ActivityDetailPageBinding>() {

    companion object {
        const val ARGUMENT_USER_ID = "USER_ID"
    }

    override val mBindingInflater: (LayoutInflater) -> ActivityDetailPageBinding
        get() = ActivityDetailPageBinding::inflate

    override fun initializeViews() {
        showToastLong(getStringExtra(ARGUMENT_USER_ID))
    }

    override fun networkError() {}

}