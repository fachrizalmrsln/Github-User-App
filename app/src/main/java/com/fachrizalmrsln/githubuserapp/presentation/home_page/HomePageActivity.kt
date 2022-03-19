package com.fachrizalmrsln.githubuserapp.presentation.home_page

import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fachrizalmrsln.githubuserapp.R
import com.fachrizalmrsln.githubuserapp.base.BaseActivity
import com.fachrizalmrsln.githubuserapp.databinding.ActivityHomePageBinding
import com.fachrizalmrsln.githubuserapp.model.SearchItemModel
import com.fachrizalmrsln.githubuserapp.navigation.navigateToDetail
import com.fachrizalmrsln.githubuserapp.presentation.home_page.adapter.AdapterSearchResults
import com.fachrizalmrsln.githubuserapp.utils.views.gone
import com.fachrizalmrsln.githubuserapp.utils.views.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomePageActivity
    : BaseActivity<ActivityHomePageBinding>(),
    AdapterSearchResults.ListenerSearchResults {

    private val mViewModel: HomePageViewModel by viewModels()
    private lateinit var mAdapter: AdapterSearchResults
    private var mSearchResultsEmpty = true
    private lateinit var mQuery: String

    override val mBindingInflater: (LayoutInflater) -> ActivityHomePageBinding
        get() = ActivityHomePageBinding::inflate

    override fun onPause() {
        super.onPause()
        mViewModel.restartViewModelJob()
    }

    override fun initializeViews() {
        setupRecyclerView()
        searchListener()
        eventLister()

        btnTryAgainClick()
    }

    override fun networkError() {
        mViewModel.messageToUI.observe(this@HomePageActivity) {
            when {
                it.contains(getString(R.string.no_connection_error)) -> showErrorUI()
                it.contains(getString(R.string.no_results_error)) -> {
                    hideSearchResultsContainer()
                    showToastLong(String.format(getString(R.string.search_not_found), mQuery))
                }
                else -> {
                    hideSearchResultsContainer()
                    showToastLong(it)
                }
            }
        }
    }

    override fun onSearchItemCLick(result: SearchItemModel) {
        navigateToDetail(result)
    }

    private fun setupRecyclerView() = with(mBinding) {
        mAdapter = AdapterSearchResults()
        mAdapter.mListener = this@HomePageActivity
        rvSearch.apply {
            layoutManager = LinearLayoutManager(this@HomePageActivity)
            adapter = mAdapter
            setHasFixedSize(true)
        }
    }

    private fun searchListener() {
        mBinding.etSearch.apply {
            requestFocus()
            setOnEditorActionListener { _, actionId, _ ->
                val query = text.toString().trim()
                if (actionId == IME_ACTION_SEARCH)
                    if (query.isNotBlank() && query.isNotEmpty()) searchUser(query)
                true
            }
        }
    }

    private fun searchUser(query: String) {
        mQuery = query
        launch {
            mAdapter.clearData()
            mViewModel.searchUser(mQuery)
        }
    }

    private fun eventLister() {
        mViewModel.loadingStatus.observe(this) {
            if (it) mBinding.llSearchResults.visible()
            mAdapter.loadingState(it)
        }
        mViewModel.mSearchResults.observe(this) { searchResults ->
            mAdapter.insertData(searchResults)
            mSearchResultsEmpty = false
        }
    }

    private fun btnTryAgainClick() = with(mBinding) {
        includedViewError.btnTryAgain.setOnClickListener {
            hideErrorUI()
            searchUser(mQuery)
        }
    }

    private fun showErrorUI() = with(mBinding) {
        includedViewError.rlContainer.visible()
        rvSearch.gone()
    }

    private fun hideErrorUI() = with(mBinding) {
        includedViewError.rlContainer.gone()
        rvSearch.visible()
    }

    private fun hideSearchResultsContainer() = with(mBinding) {
        if (mSearchResultsEmpty) llSearchResults.gone()
    }

}