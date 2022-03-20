package com.fachrizalmrsln.githubuserapp.data.local.repository

import com.fachrizalmrsln.githubuserapp.data.local.IDatabaseObject
import com.fachrizalmrsln.githubuserapp.model.SearchItemModel
import com.fachrizalmrsln.githubuserapp.model.UserRepositories
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalRepository @Inject constructor(
    private val mDao: IDatabaseObject
) : ILocalRepository {

    override suspend fun saveSearchHistory(dataSearch: SearchItemModel) {
        mDao.saveSearchHistory(dataSearch)
    }

    override suspend fun saveRepositories(dataRepositories: UserRepositories) {
        mDao.saveRepositories(dataRepositories)
    }

    override suspend fun getSearchHistory(): Flow<List<SearchItemModel>> {
        return flow {
            emit(mDao.getSearchHistory())
        }
    }

    override suspend fun getRepositories(): List<UserRepositories> {
        return mDao.getRepositories()
    }

}