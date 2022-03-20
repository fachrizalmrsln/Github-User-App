package com.fachrizalmrsln.githubuserapp.presentation.detail_page

import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fachrizalmrsln.githubuserapp.R
import com.fachrizalmrsln.githubuserapp.base.BaseActivity
import com.fachrizalmrsln.githubuserapp.databinding.ActivityDetailPageBinding
import com.fachrizalmrsln.githubuserapp.model.SearchItemModel
import com.fachrizalmrsln.githubuserapp.presentation.detail_page.adapter.AdapterUserRepositories
import com.fachrizalmrsln.githubuserapp.utils.data.getParcelable
import com.fachrizalmrsln.githubuserapp.utils.image.loadImage
import com.fachrizalmrsln.githubuserapp.utils.strings.checkNullOrEmpty
import com.fachrizalmrsln.githubuserapp.utils.strings.isUserName
import com.fachrizalmrsln.githubuserapp.utils.views.gone
import com.fachrizalmrsln.githubuserapp.utils.views.loadDrawable
import com.fachrizalmrsln.githubuserapp.utils.views.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailPageActivity : BaseActivity<ActivityDetailPageBinding>() {

    private val mViewModel: DetailPageViewModel by viewModels()

    private var mData: SearchItemModel? = null
    private lateinit var mAdapter: AdapterUserRepositories

    companion object {
        const val ARGUMENT_USER_DETAIL = "USER_DETAIL'"
    }

    override val mBindingInflater: (LayoutInflater) -> ActivityDetailPageBinding
        get() = ActivityDetailPageBinding::inflate

    override fun onPause() {
        super.onPause()
        mViewModel.restartViewModelJob()
    }

    override fun initializeViews() {
        getData()
        eventLister()
        initDataToUI()

        setupRecyclerView()

        btnTryAgainClick()
    }

    private fun setupRecyclerView() = with(mBinding) {
        mAdapter = AdapterUserRepositories()
        rvRepositories.apply {
            layoutManager = LinearLayoutManager(this@DetailPageActivity)
            adapter = mAdapter
            setHasFixedSize(true)
        }
    }

    override fun networkError() {
        mViewModel.messageToUI.observe(this) {
            when {
                it.contains(getString(R.string.no_connection_error)) -> showErrorUI()
                else -> showToastLong(it)
            }
        }
    }

    private fun getData() {
        mData = mData ?: getParcelable(ARGUMENT_USER_DETAIL)
        launch {
            mData?.login?.let { mViewModel.getUserRepositories(it) }
        }
    }

    private fun eventLister() {
        mViewModel.mUserRepositories.observe(this) { userRepositories ->
            mAdapter.insertData(userRepositories)
        }
        mViewModel.loadingStatus.observe(this) {
            mAdapter.loadingState(it)
        }
    }

    private fun initDataToUI() = with(mBinding) {
        mData?.let {
            ivImage.loadImage(this@DetailPageActivity, it.avatar_url)
            ivFollower.loadImage(this@DetailPageActivity, loadDrawable(R.drawable.ic_follower))
            ivLocation.loadImage(this@DetailPageActivity, loadDrawable(R.drawable.ic_location))
            ivEmail.loadImage(this@DetailPageActivity, loadDrawable(R.drawable.ic_email))
            tvName.text = it.user_full_name.checkNullOrEmpty()
            tvUserName.text = it.login.isUserName()
            tvAbout.text = it.bio.checkNullOrEmpty()
            tvFollower.text = it.follower.checkNullOrEmpty()
            tvFollowing.text = it.following.checkNullOrEmpty()
            tvLocation.text = it.location.checkNullOrEmpty()
            tvEmail.text = it.email.checkNullOrEmpty()
        }
    }

    private fun btnTryAgainClick() = with(mBinding) {
        includedViewError.btnTryAgain.setOnClickListener {
            hideErrorUI()
            getData()
        }
    }

    private fun showErrorUI() = with(mBinding) {
        includedViewError.rlContainer.visible()
        rvRepositories.gone()
    }

    private fun hideErrorUI() = with(mBinding) {
        includedViewError.rlContainer.gone()
        rvRepositories.visible()
    }

}