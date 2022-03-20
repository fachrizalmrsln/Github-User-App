package com.fachrizalmrsln.githubuserapp.data.local.repository

import com.fachrizalmrsln.githubuserapp.data.local.source.ILocalSource
import com.fachrizalmrsln.githubuserapp.model.SearchItemModel
import com.fachrizalmrsln.githubuserapp.model.UserRepositories
import javax.inject.Inject

class LocalRepository @Inject constructor(
    private val mDao: ILocalSource
) : ILocalRepository {

    override suspend fun saveSearchDataHistory(dataSearch: List<SearchItemModel>) {
        mDao.saveSearchDataHistory(dataSearch)
    }

    override suspend fun saveRepositories(dataRepositories: UserRepositories) {
        mDao.saveRepositories(dataRepositories)
    }

    override suspend fun getSearchDataHistory(query: String): List<SearchItemModel> {
        return mDao.getSearchDataHistory(query)
    }

    override suspend fun getRepositories(): List<UserRepositories> {
        return mDao.getRepositories()
    }

}