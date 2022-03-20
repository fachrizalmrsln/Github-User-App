package com.fachrizalmrsln.githubuserapp

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.fachrizalmrsln.githubuserapp.data.local.source.DatabaseInstance
import com.fachrizalmrsln.githubuserapp.data.local.source.ILocalSource
import com.fachrizalmrsln.githubuserapp.presentation.splash_screen.SplashScreen
import com.fachrizalmrsln.githubuserapp.utils.constant.SPLASH_SCREEN_DURATION
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailPageActivityInstrumentedTest {

    private lateinit var userDao: ILocalSource
    private lateinit var db: DatabaseInstance

    @get: Rule
    var mActivityRule = ActivityTestRule(SplashScreen::class.java)

    private val mMockWebServer = MockWebServer()

    @Before
    fun setup() {
        mMockWebServer.start(8080)
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, DatabaseInstance::class.java).build()
        userDao = db.databaseObject()
    }

    @Test
    fun actionSearchAndSelectItemThenScrollTheRepositoryList() {
        waitForSplashScreen()
        
        onView(withId(R.id.et_search))
            .check(matches(isCompletelyDisplayed()))
            .perform(click())
            .perform(typeText("Fachrizal"))
            .perform(pressImeActionButton())

        onView(withId(R.id.rv_search))
            .check(matches(isCompletelyDisplayed()))
            .perform(scrollToPosition<RecyclerView.ViewHolder>(6))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))

        onView(withId(R.id.rv_repositories))
            .check(matches(isCompletelyDisplayed()))
            .perform(scrollToPosition<RecyclerView.ViewHolder>(19))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
    }

    @Test
    fun actionSearchAndSelectItemBackAndReselectThenScrollTheRepositoryList() {
        waitForSplashScreen()

        onView(withId(R.id.et_search))
            .check(matches(isCompletelyDisplayed()))
            .perform(click())
            .perform(typeText("Fachrizalmrsln"))
            .perform(pressImeActionButton())

        onView(withId(R.id.rv_search))
            .check(matches(isCompletelyDisplayed()))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.rv_repositories))
            .check(matches(isCompletelyDisplayed()))
            .perform(scrollToPosition<RecyclerView.ViewHolder>(19))

        onView(isRoot())
            .check(matches(isCompletelyDisplayed()))
            .perform(pressBack())

        onView(withId(R.id.rv_search))
            .check(matches(isCompletelyDisplayed()))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.rv_repositories))
            .check(matches(isCompletelyDisplayed()))
            .perform(scrollToPosition<RecyclerView.ViewHolder>(19))
    }

    private fun waitForSplashScreen() = runBlocking {
        delay(SPLASH_SCREEN_DURATION)
    }

    @After
    fun teardown() {
        mMockWebServer.shutdown()
        db.close()
    }

}
