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
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomePageActivityInstrumentedTest {

    @get: Rule
    var activityTestRule = ActivityTestRule(SplashScreen::class.java)

    @Test
    fun actionSearchAndSelectItem() {
        runBlocking { delay(3000L) }

        onView(withId(R.id.et_search))
            .check(matches(isCompletelyDisplayed()))
            .perform(click())
            .perform(typeText("Fachrizal"))
            .perform(pressImeActionButton())
        runBlocking { delay(5000L) }

        onView(withId(R.id.rv_search))
            .check(matches(isCompletelyDisplayed()))
            .perform(scrollToPosition<RecyclerView.ViewHolder>(6))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
        runBlocking { delay(5000L) }
    }

}
