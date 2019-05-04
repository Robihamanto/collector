package com.mteam.collector.Controller


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers.withClassName
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup
import com.mteam.collector.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class Test5Number {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(CollectionActivity::class.java)

    @Test
    fun test5Number() {
        val recyclerView = onView(
            allOf(
                withId(R.id.numberListView),
                childAtPosition(
                    withClassName(`is`("android.support.constraint.ConstraintLayout")),
                    1
                )
            )
        )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(9, click()))

        val recyclerView2 = onView(
            allOf(
                withId(R.id.numberListView),
                childAtPosition(
                    withClassName(`is`("android.support.constraint.ConstraintLayout")),
                    1
                )
            )
        )
        recyclerView2.perform(actionOnItemAtPosition<ViewHolder>(47, click()))

        val recyclerView3 = onView(
            allOf(
                withId(R.id.numberListView),
                childAtPosition(
                    withClassName(`is`("android.support.constraint.ConstraintLayout")),
                    1
                )
            )
        )
        recyclerView3.perform(actionOnItemAtPosition<ViewHolder>(36, click()))

        val recyclerView4 = onView(
            allOf(
                withId(R.id.numberListView),
                childAtPosition(
                    withClassName(`is`("android.support.constraint.ConstraintLayout")),
                    1
                )
            )
        )
        recyclerView4.perform(actionOnItemAtPosition<ViewHolder>(14, click()))

        val recyclerView5 = onView(
            allOf(
                withId(R.id.numberListView),
                childAtPosition(
                    withClassName(`is`("android.support.constraint.ConstraintLayout")),
                    1
                )
            )
        )
        recyclerView5.perform(actionOnItemAtPosition<ViewHolder>(76, click()))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
