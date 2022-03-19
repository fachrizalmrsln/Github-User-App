package com.fachrizalmrsln.githubuserapp

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
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

    @get: Rule
    var mActivityRule = ActivityTestRule(SplashScreen::class.java)

    private val mMockWebServer = MockWebServer()

    @Before
    fun setup() {
        mMockWebServer.start(8080)
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

    private fun waitForSplashScreen() = runBlocking {
        delay(SPLASH_SCREEN_DURATION)
    }

    @After
    fun teardown() {
        mMockWebServer.shutdown()
    }

}
