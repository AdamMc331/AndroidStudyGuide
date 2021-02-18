package com.adammcneilly.androidstudyguide.articlelist

import android.view.View
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adammcneilly.androidstudyguide.MainActivity
import com.adammcneilly.androidstudyguide.R
import com.adammcneilly.androidstudyguide.utils.ViewVisibilityIdlingResource
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AndroidEssenceArticleListFragmentTest {

    @Test
    fun createFragment() {
        val scenario = launchActivity<MainActivity>()

        onView(withId(R.id.progress_bar)).check(matches(isDisplayed()))

        scenario.onActivity { mainActivity ->
            IdlingRegistry.getInstance().register(
                ViewVisibilityIdlingResource(
                    mainActivity,
                    R.id.progress_bar,
                    View.GONE
                )
            )
        }

        onView(withId(R.id.article_list)).check(matches(isDisplayed()))
    }
}
