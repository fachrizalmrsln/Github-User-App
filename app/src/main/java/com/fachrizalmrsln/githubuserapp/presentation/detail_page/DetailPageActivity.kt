package com.fachrizalmrsln.githubuserapp.presentation.detail_page

import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.fachrizalmrsln.githubuserapp.base.BaseActivity
import com.fachrizalmrsln.githubuserapp.databinding.ActivityDetailPageBinding
import com.fachrizalmrsln.githubuserapp.utils.data.getStringExtra
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailPageActivity : BaseActivity<ActivityDetailPageBinding>() {

    private val mViewModel: DetailPageViewModel by viewModels()

    companion object {
        const val ARGUMENT_USER_NAME = "USER_NAME"
    }

    override val mBindingInflater: (LayoutInflater) -> ActivityDetailPageBinding
        get() = ActivityDetailPageBinding::inflate

    override fun initializeViews() {
        getData()
        eventLister()
    }

    override fun networkError() {}

    private fun getData() {
        launch {
            mViewModel.getDetailUser(getStringExtra(ARGUMENT_USER_NAME))
        }
    }

    private fun eventLister() {
        mViewModel.mUserDetailResults.observe(this) {
            Log.d("Test", it.toString())
        }
        mViewModel.loadingStatus.observe(this) {
            showToastShort(it.toString())
        }
    }

}