package com.fachrizalmrsln.githubuserapp.presentation.home_page

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fachrizalmrsln.githubuserapp.base.BaseViewModel
import com.fachrizalmrsln.githubuserapp.data.local.repository.ILocalRepository
import com.fachrizalmrsln.githubuserapp.data.remote.repository.IRemoteRepository
import com.fachrizalmrsln.githubuserapp.model.SearchItemModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val mRemoteRepo: IRemoteRepository,
    private val mLocalRepo: ILocalRepository
) : BaseViewModel() {

    private var _mSearchResults = MutableLiveData<List<SearchItemModel>>()
    val mSearchResults: LiveData<List<SearchItemModel>>
        get() = _mSearchResults

    private var _mSearchHistories = MutableLiveData<List<SearchItemModel>>()
    val mSearchHistories: LiveData<List<SearchItemModel>>
        get() = _mSearchHistories

    suspend fun searchUser(query: String) {
        showLoading()
        restartJob(INITIAL)
        launch {
            mRemoteRepo.getSearchUser(query)
                .catch {
                    _messageToUI.value = it.message
                    restartJob()
                }
                .onCompletion { hideLoading() }
                .cancellable()
                .collect { _mSearchResults.value = it }
        }
    }

    fun saveSearchHistory(dataSearch: SearchItemModel) {
        CoroutineScope(Dispatchers.IO).launch {
            mLocalRepo.saveSearchHistory(dataSearch)
        }
    }

    fun getSearchHistory() {
        launch {
            mLocalRepo.getSearchHistory()
                .collect {
                    _mSearchHistories.value = it
                }
        }
    }

    fun restartViewModelJob() {
        restartJob()
    }

}