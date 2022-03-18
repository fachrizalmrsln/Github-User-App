package com.fachrizalmrsln.githubuserapp.presentation.detail_page

import android.view.LayoutInflater
import com.fachrizalmrsln.githubuserapp.base.BaseActivity
import com.fachrizalmrsln.githubuserapp.databinding.ActivityDetailPageBinding

class DetailPageActivity : BaseActivity<ActivityDetailPageBinding>() {

    override val mBindingInflater: (LayoutInflater) -> ActivityDetailPageBinding
        get() = ActivityDetailPageBinding::inflate

    override fun initializeViews() {}

    override fun networkError() {}

}