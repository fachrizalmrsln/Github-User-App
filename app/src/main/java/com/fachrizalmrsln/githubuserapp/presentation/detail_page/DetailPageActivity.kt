package com.fachrizalmrsln.githubuserapp.presentation.detail_page

import android.view.LayoutInflater
import androidx.activity.viewModels
import com.fachrizalmrsln.githubuserapp.base.BaseActivity
import com.fachrizalmrsln.githubuserapp.databinding.ActivityDetailPageBinding
import com.fachrizalmrsln.githubuserapp.model.UserModel
import com.fachrizalmrsln.githubuserapp.utils.data.getStringExtra
import com.fachrizalmrsln.githubuserapp.utils.image.loadImage
import com.fachrizalmrsln.githubuserapp.utils.strings.checkNullOrEmpty
import com.fachrizalmrsln.githubuserapp.utils.strings.isUserName
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
            initDataToUI(it)
        }
        mViewModel.loadingStatus.observe(this) {
        }
    }

    private fun initDataToUI(data: UserModel) = with(mBinding) {
        data.avatar_url?.let { ivImage.loadImage(this@DetailPageActivity, it) }
        tvName.text = data.name.checkNullOrEmpty()
        tvUserName.text = data.login.isUserName()
        tvAbout.text = data.bio.checkNullOrEmpty()
        tvFollower.text = "${data.followers ?: 0}"
        tvFollowing.text = "${data.following ?: 0}"
        tvLocation.text = data.location.checkNullOrEmpty()
        tvEmail.text = data.email.checkNullOrEmpty()
    }

}