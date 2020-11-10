package com.example.weChat

import android.view.View
import android.view.ViewGroup
import androidx.core.view.size
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.weChat.view.MainActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun loadInitData() {

        Thread.sleep(5000L)
        onView(withId(R.id.profile_nick)).check(matches(withText("John Smith")))
        onView(withId(R.id.moments)).check(matches(withChildViewCount(5, withId(R.id.moment))))
    }

    @Test
    fun loadTenDataWhenPullUpToFresh() {
        Thread.sleep(5000L)

        repeat(2) {
            onView(withId(R.id.nestedScrollView)).perform(swipeUp())
        }

        onView(withId(R.id.moments)).check(matches(withChildViewCount(10, withId(R.id.moment))))
    }

    @Test
    fun loadFiveDataWhenPullDownToReset() {

        Thread.sleep(5000L)

        onView(withId(R.id.nestedScrollView)).perform(swipeDown())

        onView(withId(R.id.moments)).check(matches(withChildViewCount(5, withId(R.id.moment))))
    }


    private fun withChildViewCount(
        count: Int,
        childMatcher: Matcher<View>
    ): BoundedMatcher<View, ViewGroup> {
        return object : BoundedMatcher<View, ViewGroup>(ViewGroup::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("ViewGroup with child-count=$count and")
                childMatcher.describeTo(description)
            }

            override fun matchesSafely(item: ViewGroup?): Boolean {
                var matchCount: Int = 0
                if (item != null) {
                    for (i in 0..item.size) {
                        if (childMatcher.matches(item.getChildAt(i))) {
                            matchCount++
                        }
                    }

                }
                return matchCount == count
            }
        }
    }
}
