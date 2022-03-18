package com.fachrizalmrsln.githubuserapp.presentation.home_page

import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fachrizalmrsln.githubuserapp.base.BaseActivity
import com.fachrizalmrsln.githubuserapp.databinding.ActivityHomePageBinding
import com.fachrizalmrsln.githubuserapp.model.SearchItemModel
import com.fachrizalmrsln.githubuserapp.navigation.navigateToDetail
import com.fachrizalmrsln.githubuserapp.presentation.home_page.adapter.AdapterSearchResults
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomePageActivity
    : BaseActivity<ActivityHomePageBinding>(),
    AdapterSearchResults.ListenerSearchResults {

    private val mViewModel: HomePageViewModel by viewModels()
    private lateinit var mAdapter: AdapterSearchResults
    private var mSearchResultsEmpty = true

    override val mBindingInflater: (LayoutInflater) -> ActivityHomePageBinding
        get() = ActivityHomePageBinding::inflate

    override fun initializeViews() {
        setupRecyclerView()
        searchListener()
        eventLister()
    }

    override fun networkError() {
        mViewModel.messageToUI.observe(this@HomePageActivity) {
            if (mSearchResultsEmpty) mBinding.llSearchResults.visibility = View.GONE
            showToastLong(it.toString())
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
        launch {
            mAdapter.clearData()
            mViewModel.searchUser(query)
        }
    }

    private fun eventLister() {
        mViewModel.loadingStatus.observe(this) {
            if (it) mBinding.llSearchResults.visibility = View.VISIBLE
            mAdapter.loadingState(it)
        }
        mViewModel.mSearchResults.observe(this) { searchResults ->
            mAdapter.insertData(searchResults)
            mSearchResultsEmpty = false
        }
    }

}