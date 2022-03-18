package com.fachrizalmrsln.githubuserapp.presentation.home_page

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.fachrizalmrsln.githubuserapp.data.local.repository.IRemoteRepository
import com.fachrizalmrsln.githubuserapp.model.SearchItemModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val repository: IRemoteRepository
) : ViewModel(), LifecycleObserver {

    suspend fun searchUser(query: String): Flow<List<SearchItemModel>> {
        return repository.getSearchUser(query)
    }

}