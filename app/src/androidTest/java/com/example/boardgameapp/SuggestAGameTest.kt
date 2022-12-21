package com.example.boardgameapp


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class SuggestAGameTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun suggestAGameTest() {
        val materialButton = onView(
            allOf(
                withId(R.id.ue_enter_event_button), withText("ENTER EVENT"),
                childAtPosition(
                    allOf(
                        withId(R.id.card_content),
                        childAtPosition(
                            withClassName(`is`("com.google.android.material.card.MaterialCardView")),
                            0
                        )
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())

//        val materialButton2 = onView(
//            allOf(
//                withId(R.id.suggest_game_button), withText("SUGGEST A GAME"),
//                childAtPosition(
//                    childAtPosition(
//                        withClassName(`is`("android.widget.ScrollView")),
//                        0
//                    ),
//                    6
//                )
//            )
//        )
//        materialButton2.perform(scrollTo(), click())
//
//        val appCompatSpinner = onView(
//            allOf(
//                withId(R.id.chG_spinner),
//                childAtPosition(
//                    childAtPosition(
//                        withId(android.R.id.content),
//                        0
//                    ),
//                    2
//                ),
//                isDisplayed()
//            )
//        )
//        appCompatSpinner.perform(click())
//
//        val materialButton3 = onView(
//            allOf(
//                withId(R.id.chG_confirm), withText("Confirm"),
//                childAtPosition(
//                    childAtPosition(
//                        withId(android.R.id.content),
//                        0
//                    ),
//                    3
//                ),
//                isDisplayed()
//            )
//        )
//        materialButton3.perform(click())
//
//        val textView = onView(
//            allOf(
//                withId(R.id.suggestedGames), withText("Suggested games:\n Uno"),
//                withParent(withParent(IsInstanceOf.instanceOf(android.widget.ScrollView::class.java))),
//                isDisplayed()
//            )
//        )
//        textView.check(matches(withText("Suggested games:  Uno")))
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