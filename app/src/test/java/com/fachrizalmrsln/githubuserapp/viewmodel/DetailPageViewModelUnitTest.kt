package com.fachrizalmrsln.githubuserapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.fachrizalmrsln.githubuserapp.CoroutineTestRule
import com.fachrizalmrsln.githubuserapp.data.usecase.UseCase
import com.fachrizalmrsln.githubuserapp.model.UserModel
import com.fachrizalmrsln.githubuserapp.model.UserRepositoriesModel
import com.fachrizalmrsln.githubuserapp.presentation.detail_page.DetailPageViewModel
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
class DetailPageViewModelUnitTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineScope = CoroutineTestRule()

    @Mock
    private lateinit var mObserver: Observer<List<UserRepositoriesModel>>
    private lateinit var mViewModel: DetailPageViewModel

    @Mock
    private lateinit var mUseCase: UseCase

    @Mock
    private lateinit var mResponse: List<UserRepositoriesModel>

    @Captor
    private lateinit var captor: ArgumentCaptor<List<UserRepositoriesModel>>

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        mViewModel = DetailPageViewModel(mUseCase)
    }

    @Test
    fun getSearchUser_isCorrect() {
        val mQuery = "fachrizalmrsln"
        val mDataExpected = UserRepositoriesModel(
            id = 223750327,
            avatar_url = "https://avatars.githubusercontent.com/u/28893080?v=4",
            name = "Beautiful-Place-App",
            description = "Application for certification in AndroidPemula class from dicoding.",
            stargazers_count = 0,
            updated_at = "2019-12-15T09:25:37Z",
            owner = UserModel(
                avatar_url = "https://avatars.githubusercontent.com/u/28893080?v=4",
                bio = "Learner & Code writer",
                location = "South Jakarta, Jakarta, Indonesia",
                email = "fachrizalmrsln@gmail.com",
                following = 26,
                followers = 3,
                name = "Fachrizal Mursalin",
                login = "fachrizalmrsln"
            )
        )
        runBlocking {
            val flow = flow { emit(mResponse) }

            `when`(mUseCase.getUserRepositories(mQuery)).thenReturn(flow)
            `when`(mResponse[0]).thenReturn(mDataExpected)

            mViewModel.getUserRepositories(mQuery)
            val liveData = mViewModel.mUserRepositories
            liveData.observeForever(mObserver)

            Mockito.verify(mObserver).onChanged(captor.capture())
            assertEquals(0, captor.value.size)
            assertEquals(223750327, captor.value[0].id)
        }
    }

    @Test
    fun getSearchUser_isInCorrect() {
        val mQuery = "fachrizalmrsln"
        val mDataExpected = UserRepositoriesModel(
            id = 111111,
            avatar_url = "https://avatars.githubusercontent.com/u/28893080?v=4",
            name = "Beautiful-Place-App",
            description = "Application for certification in AndroidPemula class from dicoding.",
            stargazers_count = 0,
            updated_at = "2019-12-15T09:25:37Z",
            owner = UserModel(
                avatar_url = "https://avatars.githubusercontent.com/u/28893080?v=4",
                bio = "Learner & Code writer",
                location = "South Jakarta, Jakarta, Indonesia",
                email = "fachrizalmrsln@gmail.com",
                following = 26,
                followers = 3,
                name = "Fachrizal Mursalin",
                login = "fachrizalmrsln"
            )
        )
        runBlocking {
            val flow = flow { emit(mResponse) }

            `when`(mUseCase.getUserRepositories(mQuery)).thenReturn(flow)
            `when`(mResponse[0]).thenReturn(mDataExpected)

            mViewModel.getUserRepositories(mQuery)
            val liveData = mViewModel.mUserRepositories
            liveData.observeForever(mObserver)

            Mockito.verify(mObserver).onChanged(captor.capture())
            assertNotEquals(1, captor.value.size)
            assertNotEquals(223750327, captor.value[0].id)
        }
    }

}