package com.fachrizalmrsln.githubuserapp.data.local.repository

import com.fachrizalmrsln.githubuserapp.data.local.source.ILocalSource
import com.fachrizalmrsln.githubuserapp.model.SearchItemModel
import com.fachrizalmrsln.githubuserapp.model.SearchQueryModel
import com.fachrizalmrsln.githubuserapp.model.SearchRoomResultModel
import com.fachrizalmrsln.githubuserapp.model.UserRepositoriesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalRepository @Inject constructor(
    private val mDao: ILocalSource
) : ILocalRepository {

    override suspend fun saveSearchQuery(dataQuery: SearchQueryModel) {
        withContext(Dispatchers.IO) {
            mDao.saveSearchQuery(dataQuery)
        }
    }

    override suspend fun saveSearchDataHistory(dataSearch: List<SearchItemModel>) {
        withContext(Dispatchers.IO) {
            mDao.saveSearchDataHistory(dataSearch)
        }
    }

    override suspend fun saveRepositories(dataRepositories: List<UserRepositoriesModel>) {
        withContext(Dispatchers.IO) {
            mDao.saveRepositories(dataRepositories)
        }
    }

    override suspend fun getSearchQuery(query: String): SearchQueryModel? {
        return withContext(Dispatchers.IO) {
            mDao.getSearchQuery(query)
        }
    }

    override suspend fun getSearchHistory(): List<SearchRoomResultModel> {
        return withContext(Dispatchers.IO) {
            mDao.getSearchHistory()
        }
    }

    override suspend fun getSearchDataHistory(query: String): List<SearchItemModel> {
        return withContext(Dispatchers.IO) {
            mDao.getSearchDataHistory(query)
        }
    }

    override suspend fun getRepositories(userName: String): List<UserRepositoriesModel> {
        return withContext(Dispatchers.IO) {
            mDao.getRepositories(userName)
        }
    }

}