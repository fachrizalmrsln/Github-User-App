package com.fachrizalmrsln.githubuserapp.presentation.detail_page

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fachrizalmrsln.githubuserapp.base.BaseViewModel
import com.fachrizalmrsln.githubuserapp.data.remote.repository.IRemoteRepository
import com.fachrizalmrsln.githubuserapp.model.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailPageViewModel @Inject constructor(
    private val repository: IRemoteRepository
) : BaseViewModel() {

    private var _mUserDetailResults = MutableLiveData<UserModel>()
    val mUserDetailResults: LiveData<UserModel>
        get() = _mUserDetailResults

    suspend fun getDetailUser(userName: String) {
        showLoading()
        restartJob(INITIAL)
        launch {
            repository.getDetailUser(userName)
                .catch {
                    _messageToUI.value = it.message
                    restartJob()
                }
                .onCompletion { hideLoading() }
                .cancellable()
                .collect { _mUserDetailResults.value = it }
        }
    }

}