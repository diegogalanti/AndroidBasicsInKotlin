package com.gallardo.tiptime

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatoTests {
    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun check_switch() {
        onView(withId(R.id.round_up_switch)).check(matches(isChecked()))
    }

    @Test
    fun check_switch_off() {
        onView(withId(R.id.round_up_switch)).perform(click())
        onView(withId(R.id.round_up_switch)).check(matches(isNotChecked()))
    }

    @Test
    fun calculate_20_percent_tip_round() {
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("49.00")).perform(ViewActions.closeSoftKeyboard())
        onView(withId(R.id.round_up_switch)).check(matches(isChecked()))
        onView(withId(R.id.option_twenty_percent)).check(matches(isChecked()))
        onView(withId(R.id.calculate_button))
            .perform(click())
        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("$10.00"))))
    }

    @Test
    fun calculate_20_percent_tip_not_round() {
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("49.00")).perform(ViewActions.closeSoftKeyboard())
        onView(withId(R.id.round_up_switch)).perform(click())
        onView(withId(R.id.round_up_switch)).check(matches(isNotChecked()))
        onView(withId(R.id.calculate_button))
            .perform(click())
        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("$9.80"))))
    }

    @Test
    fun calculate_18_percent_tip_round() {
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("49.00")).perform(ViewActions.closeSoftKeyboard())
        onView(withId(R.id.round_up_switch)).check(matches(isChecked()))
        onView(withId(R.id.option_eighteen_percent)).perform(click())
        onView(withId(R.id.option_eighteen_percent)).check(matches(isChecked()))
        onView(withId(R.id.calculate_button))
            .perform(click())
        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("$9.00"))))
    }

    @Test
    fun calculate_18_percent_tip_not_round() {
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("49.00")).perform(ViewActions.closeSoftKeyboard())
        onView(withId(R.id.round_up_switch)).perform(click())
        onView(withId(R.id.round_up_switch)).check(matches(isNotChecked()))
        onView(withId(R.id.option_eighteen_percent)).perform(click())
        onView(withId(R.id.option_eighteen_percent)).check(matches(isChecked()))
        onView(withId(R.id.calculate_button))
            .perform(click())
        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("$8.82"))))
    }

    @Test
    fun calculate_15_percent_tip_round() {
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("49.00")).perform(ViewActions.closeSoftKeyboard())
        onView(withId(R.id.round_up_switch)).check(matches(isChecked()))
        onView(withId(R.id.option_fifteen_percent)).perform(click())
        onView(withId(R.id.option_fifteen_percent)).check(matches(isChecked()))
        onView(withId(R.id.calculate_button))
            .perform(click())
        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("$8.00"))))
    }

    @Test
    fun calculate_15_percent_tip_not_round() {
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("49.00")).perform(ViewActions.closeSoftKeyboard())
        onView(withId(R.id.round_up_switch)).perform(click())
        onView(withId(R.id.round_up_switch)).check(matches(isNotChecked()))
        onView(withId(R.id.option_fifteen_percent)).perform(click())
        onView(withId(R.id.option_fifteen_percent)).check(matches(isChecked()))
        onView(withId(R.id.calculate_button))
            .perform(click())
        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("$7.35"))))
    }
}