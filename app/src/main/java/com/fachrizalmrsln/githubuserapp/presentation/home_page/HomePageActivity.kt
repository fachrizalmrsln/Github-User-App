package com.fachrizalmrsln.githubuserapp.presentation.home_page

import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fachrizalmrsln.githubuserapp.base.BaseActivity
import com.fachrizalmrsln.githubuserapp.databinding.ActivityHomePageBinding
import com.fachrizalmrsln.githubuserapp.model.SearchItemModel
import com.fachrizalmrsln.githubuserapp.presentation.home_page.adapter.AdapterSearchResults
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomePageActivity
    : BaseActivity<ActivityHomePageBinding>(),
    AdapterSearchResults.ListenerSearchResults {

    private val mViewModel: HomePageViewModel by viewModels()
    private lateinit var mAdapter: AdapterSearchResults

    override val mBindingInflater: (LayoutInflater) -> ActivityHomePageBinding
        get() = ActivityHomePageBinding::inflate

    override fun initializeViews() {
        setupAdapter()
        searchListener()
        eventLister()
    }

    override fun networkError() {
        mViewModel.messageToUI.observe(this@HomePageActivity) {
            showToastShort(it.toString())
        }
    }

    override fun onSearchItemCLick(result: SearchItemModel) {
        Toast.makeText(this, result.login, Toast.LENGTH_LONG).show()
    }

    private fun setupAdapter() {
        mAdapter = AdapterSearchResults()
        mAdapter.mListener = this

        setupRecyclerView()
    }

    private fun setupRecyclerView() = with(mBinding) {
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

    private fun searchUser(query: String) = mActivityScope.launch {
        var inputState = true
        mViewModel.searchUser(query).collect { searchResults ->
            mAdapter.insertData(searchResults, inputState)
            inputState = false
        }
    }

    private fun eventLister() {
        mViewModel.loadingStatus.observe(this) {
            if (it) mBinding.llSearchResults.visibility = View.VISIBLE
            mAdapter.loadingState(it)
        }
    }

}