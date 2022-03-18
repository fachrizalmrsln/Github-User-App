package com.fachrizalmrsln.githubuserapp.presentation.home_page

import com.fachrizalmrsln.githubuserapp.base.BaseViewModel
import com.fachrizalmrsln.githubuserapp.data.local.repository.IRemoteRepository
import com.fachrizalmrsln.githubuserapp.model.SearchItemModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val repository: IRemoteRepository
) : BaseViewModel() {

    suspend fun searchUser(query: String): Flow<List<SearchItemModel>> {
        showLoading()
        return repository.getSearchUser(query)
            .catch {
                _messageToUI.value = it.message
            }
            .onCompletion { hideLoading() }
    }

}