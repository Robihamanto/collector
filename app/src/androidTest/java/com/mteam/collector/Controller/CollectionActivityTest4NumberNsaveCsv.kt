package com.mteam.collector.Controller


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.GeneralClickAction
import android.support.test.espresso.action.GeneralLocation
import android.support.test.espresso.action.Press
import android.support.test.espresso.action.Tap
import android.support.test.espresso.action.ViewActions.actionWithAssertions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers.withClassName
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.rule.GrantPermissionRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.InputDevice
import android.view.MotionEvent
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
class CollectionActivityTest4NumberNsaveCsv {

    var counter = 1

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(CollectionActivity::class.java)

    @Rule
    @JvmField
    var mGrantPermissionRule =
        GrantPermissionRule.grant(
            "android.permission.WRITE_EXTERNAL_STORAGE"
        )

    @Test
    fun collectionActivityTest4NumberNsaveCsv() {
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
        recyclerView4.perform(actionOnItemAtPosition<ViewHolder>(76, click()))

        val recyclerView6 = onView(
            allOf(
                withId(R.id.numberListView),
                childAtPosition(
                    withClassName(`is`("android.support.constraint.ConstraintLayout")),
                    1
                )
            )
        )
        recyclerView4.perform(actionOnItemAtPosition<ViewHolder>(29, click()))

        val recyclerView7 = onView(
            allOf(
                withId(R.id.numberListView),
                childAtPosition(
                    withClassName(`is`("android.support.constraint.ConstraintLayout")),
                    1
                )
            )
        )
        recyclerView4.perform(actionOnItemAtPosition<ViewHolder>(8, click()))

        val recyclerView8 = onView(
            allOf(
                withId(R.id.numberListView),
                childAtPosition(
                    withClassName(`is`("android.support.constraint.ConstraintLayout")),
                    1
                )
            )
        )
        recyclerView4.perform(actionOnItemAtPosition<ViewHolder>(57, click()))

        val recyclerView9 = onView(
            allOf(
                withId(R.id.numberListView),
                childAtPosition(
                    withClassName(`is`("android.support.constraint.ConstraintLayout")),
                    1
                )
            )
        )
        recyclerView4.perform(actionOnItemAtPosition<ViewHolder>(23, click()))

        val recyclerView10 = onView(
            allOf(
                withId(R.id.numberListView),
                childAtPosition(
                    withClassName(`is`("android.support.constraint.ConstraintLayout")),
                    1
                )
            )
        )
        recyclerView4.perform(actionOnItemAtPosition<ViewHolder>(13, click()))
    }

    fun click(): ViewAction {

        var action: ViewAction = actionWithAssertions(
            GeneralClickAction(
                Tap.SINGLE,
                GeneralLocation.CENTER_LEFT,
                Press.THUMB,
                InputDevice.SOURCE_UNKNOWN,
                MotionEvent.BUTTON_PRIMARY
            )
        )

        when {
            counter == 1 ->
                action = actionWithAssertions(
                GeneralClickAction(
                    Tap.SINGLE,
                    GeneralLocation.VISIBLE_CENTER,
                    Press.THUMB,
                    InputDevice.SOURCE_UNKNOWN,
                    MotionEvent.BUTTON_PRIMARY
                )
            )
            counter == 2 ->
                action = actionWithAssertions(
                    GeneralClickAction(
                        Tap.SINGLE,
                        GeneralLocation.BOTTOM_CENTER,
                        Press.FINGER,
                        InputDevice.SOURCE_UNKNOWN,
                        MotionEvent.BUTTON_PRIMARY
                    )
                )
            counter == 3 ->
                action = actionWithAssertions(
                    GeneralClickAction(
                        Tap.LONG,
                        GeneralLocation.CENTER_RIGHT,
                        Press.PINPOINT,
                        InputDevice.SOURCE_UNKNOWN,
                        MotionEvent.BUTTON_PRIMARY
                    )
                )
            counter == 4 ->
                action = actionWithAssertions(
                    GeneralClickAction(
                        Tap.DOUBLE,
                        GeneralLocation.TOP_RIGHT,
                        Press.THUMB,
                        InputDevice.SOURCE_UNKNOWN,
                        MotionEvent.BUTTON_PRIMARY
                    )
                )
            counter == 5 ->
                action = actionWithAssertions(
                    GeneralClickAction(
                        Tap.SINGLE,
                        GeneralLocation.VISIBLE_CENTER,
                        Press.THUMB,
                        InputDevice.SOURCE_UNKNOWN,
                        MotionEvent.BUTTON_PRIMARY
                    )
                )
            counter == 6 ->
                action = actionWithAssertions(
                    GeneralClickAction(
                        Tap.SINGLE,
                        GeneralLocation.BOTTOM_CENTER,
                        Press.FINGER,
                        InputDevice.SOURCE_UNKNOWN,
                        MotionEvent.BUTTON_PRIMARY
                    )
                )
            counter == 7 ->
                action = actionWithAssertions(
                    GeneralClickAction(
                        Tap.LONG,
                        GeneralLocation.CENTER_RIGHT,
                        Press.PINPOINT,
                        InputDevice.SOURCE_UNKNOWN,
                        MotionEvent.BUTTON_PRIMARY
                    )
                )
            counter == 8 ->
                action = actionWithAssertions(
                    GeneralClickAction(
                        Tap.DOUBLE,
                        GeneralLocation.TOP_RIGHT,
                        Press.THUMB,
                        InputDevice.SOURCE_UNKNOWN,
                        MotionEvent.BUTTON_PRIMARY
                    )
                )
            counter == 9 ->
                action = actionWithAssertions(
                    GeneralClickAction(
                        Tap.SINGLE,
                        GeneralLocation.VISIBLE_CENTER,
                        Press.THUMB,
                        InputDevice.SOURCE_UNKNOWN,
                        MotionEvent.BUTTON_PRIMARY
                    )
                )
            counter == 10 ->
                action = actionWithAssertions(
                    GeneralClickAction(
                        Tap.SINGLE,
                        GeneralLocation.BOTTOM_CENTER,
                        Press.FINGER,
                        InputDevice.SOURCE_UNKNOWN,
                        MotionEvent.BUTTON_PRIMARY
                    )
                )
            }

        counter += 1
        return action
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
