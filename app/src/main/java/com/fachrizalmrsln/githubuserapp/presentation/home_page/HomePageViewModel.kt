package com.fachrizalmrsln.githubuserapp.presentation.home_page

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fachrizalmrsln.githubuserapp.base.BaseViewModel
import com.fachrizalmrsln.githubuserapp.data.usecase.IUseCase
import com.fachrizalmrsln.githubuserapp.model.SearchItemModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val mUseCase: IUseCase
) : BaseViewModel() {

    private var _mSearchResults = MutableLiveData<List<SearchItemModel>>()
    val mSearchResults: LiveData<List<SearchItemModel>>
        get() = _mSearchResults

    suspend fun searchUser(query: String) {
        showLoading()
        restartJob(INITIAL)
        launch {
            mUseCase.getSearchUser(query)
                .catch {
                    _messageToUI.value = it.message
                    restartJob()
                }
                .onCompletion { hideLoading() }
                .cancellable()
                .collect { _mSearchResults.value = it }
        }
    }

    fun restartViewModelJob() {
        restartJob()
    }

}