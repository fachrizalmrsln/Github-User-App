package com.fachrizalmrsln.githubuserapp.presentation.detail_page

import android.view.LayoutInflater
import androidx.activity.viewModels
import com.fachrizalmrsln.githubuserapp.base.BaseActivity
import com.fachrizalmrsln.githubuserapp.databinding.ActivityDetailPageBinding
import com.fachrizalmrsln.githubuserapp.model.SearchItemModel
import com.fachrizalmrsln.githubuserapp.utils.data.getParcelable
import com.fachrizalmrsln.githubuserapp.utils.image.loadImage
import com.fachrizalmrsln.githubuserapp.utils.strings.checkNullOrEmpty
import com.fachrizalmrsln.githubuserapp.utils.strings.isUserName
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailPageActivity : BaseActivity<ActivityDetailPageBinding>() {

    private val mViewModel: DetailPageViewModel by viewModels()

    private var mData: SearchItemModel? = null

    companion object {
        const val ARGUMENT_USER_DETAIL = "USER_DETAIL'"
    }

    override val mBindingInflater: (LayoutInflater) -> ActivityDetailPageBinding
        get() = ActivityDetailPageBinding::inflate

    override fun initializeViews() {
        getData()
        eventLister()
        initDataToUI()
    }

    override fun networkError() {}

    private fun getData() {
        mData = getParcelable(ARGUMENT_USER_DETAIL)
        launch {
            mData?.login?.let { mViewModel.getUserRepositories(it) }
        }
    }

    private fun eventLister() {
        mViewModel.mUserRepositories.observe(this) { userRepositories ->
            userRepositories.forEach { repositories ->
                println(repositories.toString())
            }
        }
        mViewModel.loadingStatus.observe(this) {
        }
    }

    private fun initDataToUI() = with(mBinding) {
        mData?.let {
            ivImage.loadImage(this@DetailPageActivity, it.avatar_url)
            tvName.text = it.user_full_name.checkNullOrEmpty()
            tvUserName.text = it.login.isUserName()
            tvAbout.text = it.bio.checkNullOrEmpty()
            tvFollower.text = it.follower.checkNullOrEmpty()
            tvFollowing.text = it.following.checkNullOrEmpty()
            tvLocation.text = it.location.checkNullOrEmpty()
            tvEmail.text = it.email.checkNullOrEmpty()
        }
    }

}