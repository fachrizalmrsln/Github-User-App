package com.fachrizalmrsln.githubuserapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.fachrizalmrsln.githubuserapp.CoroutineTestRule
import com.fachrizalmrsln.githubuserapp.data.usecase.UseCase
import com.fachrizalmrsln.githubuserapp.model.SearchItemModel
import com.fachrizalmrsln.githubuserapp.presentation.home_page.HomePageViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class HomePageViewModelUnitTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineScope = CoroutineTestRule()

    @Mock
    private lateinit var mObserver: Observer<List<SearchItemModel>>
    private lateinit var mViewModel: HomePageViewModel

    @Mock
    private lateinit var mUseCase: UseCase

    @Mock
    private lateinit var mResponse: List<SearchItemModel>

    @Captor
    private lateinit var captor: ArgumentCaptor<List<SearchItemModel>>

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        mViewModel = HomePageViewModel(mUseCase)
    }

    @Test
    fun getSearchUser_isCorrect() {
        val mQuery = "fachrizalmrsln"
        val mDataExpected = SearchItemModel(
            id = 28893080,
            avatar_url = "https://avatars.githubusercontent.com/u/28893080?v=4",
            user_full_name = "Fachrizal Mursalin",
            login = "fachrizalmrsln",
            bio = "Learner & Code writer",
            location = "South Jakarta, Jakarta, Indonesia",
            email = "fachrizalmrsln@gmail.com",
            follower = "3",
            following = "26",
            primaryKey = "12312",
            _querySearchID = "fachrizalmrsln"
        )
        runBlocking {
            val flow = flow { emit(mResponse) }

            `when`(mUseCase.getSearchUser(mQuery)).thenReturn(flow)
            `when`(mResponse[0]).thenReturn(mDataExpected)

            mViewModel.searchUser(mQuery)
            val liveData = mViewModel.mSearchResults
            liveData.observeForever(mObserver)

            Mockito.verify(mObserver).onChanged(captor.capture())
            assertEquals(0, captor.value.size)
            assertEquals(mQuery, captor.value[0].login)
        }
    }

    @Test
    fun getSearchUser_isInCorrect() {
        val mQuery = "fachrizalmrsln"
        val mDataExpected = SearchItemModel(
            id = 28893080,
            avatar_url = "https://avatars.githubusercontent.com/u/28893080?v=4",
            user_full_name = "Fachrizal Mursalin",
            login = "@fachrizalmrsln",
            bio = "Learner & Code writer",
            location = "South Jakarta, Jakarta, Indonesia",
            email = "fachrizalmrsln@gmail.com",
            follower = "3",
            following = "26",
            primaryKey = "12312",
            _querySearchID = "fachrizalmrsln"
        )
        runBlocking {
            val flow = flow { emit(mResponse) }

            `when`(mUseCase.getSearchUser(mQuery)).thenReturn(flow)
            `when`(mResponse[0]).thenReturn(mDataExpected)

            mViewModel.searchUser(mQuery)
            val liveData = mViewModel.mSearchResults
            liveData.observeForever(mObserver)

            Mockito.verify(mObserver).onChanged(captor.capture())
            assertNotEquals(1, captor.value.size)
            assertNotEquals(mQuery, captor.value[0].login)
        }
    }

}