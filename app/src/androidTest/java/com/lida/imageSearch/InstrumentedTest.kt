package com.lida.imageSearch

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.lida.imagesearch", appContext.packageName)
    }

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)


    @Test
    @Throws(Exception::class)
    fun ensureListViewIsPresent() { // Specify a valid string.

        Espresso.onView(withId(R.id.editTextSearch))
            .perform(ViewActions.typeText("building"), ViewActions.closeSoftKeyboard())

        Espresso.onView(withId(R.id.searchButton)).perform(ViewActions.click())
        //Check if images result can be shown
        Espresso.onView(withId(R.id.itemImage1))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.itemImage10))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }


}
