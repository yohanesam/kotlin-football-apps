package com.yohanesam.footballmatchschedule.testing

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.yohanesam.footballmatchschedule.R.id.*
import com.yohanesam.footballmatchschedule.view.activities.HomeActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class AppsInstrumentationTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun testAppBehaviour() {
        onView(withId(rvLastMatchRecycleView))
            .check(matches(isDisplayed()))
        Thread.sleep(1000)

        onView(withId(spLastMatchHomeSpinner))
            .check(matches(isDisplayed()))
        Thread.sleep(1000)

        onView(withId(spLastMatchHomeSpinner))
            .perform(click())
        Thread.sleep(1000)

        onView(withText("Italy Serie A")).perform(click())
        Thread.sleep(1000)

        onView(withId(rvLastMatchRecycleView))
            .perform(swipeUp())
        Thread.sleep(1000)

        onView(withId(rvLastMatchRecycleView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(8, click()))
        Thread.sleep(1000)

        onView(withId(addToFavorite))
            .check(matches(isDisplayed()))

        onView(withId(addToFavorite)).perform(click())
        onView(isRoot()).perform(pressBack())
        Thread.sleep(1000)

        onView((withId(srlLastMatchPullNavigateUpRefresh)))
            .check(matches(isDisplayed()))

        onView(withId(rvLastMatchRecycleView))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1))

        onView((withId(srlLastMatchPullNavigateUpRefresh)))
            .perform(swipeDown())
        Thread.sleep(1000)

        onView(withId(vpMatchViewPager))
            .check(matches(isDisplayed()))
            .perform(swipeLeft())
        Thread.sleep(1000)

        onView(withId(rvNextMatchRecycleView))
            .check(matches(isDisplayed()))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
        Thread.sleep(1000)

        onView(withId(addToFavorite))
            .check(matches(isDisplayed()))

        onView(withId(addToFavorite)).perform(click())
        onView(isRoot()).perform(pressBack())
        Thread.sleep(1000)

        onView((withId(srlNextMatchPullNavigateUpRefresh)))
            .check(matches(isDisplayed()))

        onView(withId(rvNextMatchRecycleView))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1))

        onView((withId(srlNextMatchPullNavigateUpRefresh)))
            .perform(swipeDown())
        Thread.sleep(1000)

        onView(withId(bnvBottomNavigation))
            .check(matches(isDisplayed()))

        onView(withId(favorites)).perform(click())
        Thread.sleep(1000)

        onView((withId(rvFavoriteRecycleView)))
            .check(matches(isDisplayed()))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(1000)

        onView(withId(addToFavorite)).perform(click())
        onView(isRoot()).perform(pressBack())
        Thread.sleep(1000)


        onView((withId(srlFavoritePullNavigateUpRefresh)))
            .check(matches(isDisplayed()))
            .perform(swipeDown())
        Thread.sleep(1000)

        onView(withId(matches)).perform(click())
        Thread.sleep(1000)

    }

}