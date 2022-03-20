package com.fachrizalmrsln.githubuserapp.presentation.detail_page

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fachrizalmrsln.githubuserapp.base.BaseViewModel
import com.fachrizalmrsln.githubuserapp.data.usecase.IUseCase
import com.fachrizalmrsln.githubuserapp.model.UserRepositoriesModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailPageViewModel @Inject constructor(
    private val mUseCase: IUseCase
) : BaseViewModel() {

    private var _mUserRepositories = MutableLiveData<List<UserRepositoriesModel>>()
    val mUserRepositories: LiveData<List<UserRepositoriesModel>>
        get() = _mUserRepositories

    suspend fun getUserRepositories(userName: String) {
        showLoading()
        restartJob(INITIAL)
        launch {
            mUseCase.getUserRepositories(userName)
                .catch {
                    _messageToUI.value = it.message
                    restartJob()
                }
                .onCompletion { hideLoading() }
                .cancellable()
                .collect { _mUserRepositories.value = it }
        }
    }

    fun restartViewModelJob() {
        restartJob()
    }

}