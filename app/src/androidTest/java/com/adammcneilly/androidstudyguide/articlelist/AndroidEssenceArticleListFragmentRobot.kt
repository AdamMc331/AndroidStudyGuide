package com.adammcneilly.androidstudyguide.articlelist

import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.adammcneilly.androidstudyguide.MainActivity
import com.adammcneilly.androidstudyguide.R
import com.adammcneilly.androidstudyguide.utils.ViewVisibilityIdlingResource
import org.hamcrest.Matchers.not

class AndroidEssenceArticleListFragmentRobot(
    private val activityScenario: ActivityScenario<MainActivity>
) {

    fun waitForProgressBarToBeGone() = apply {
        activityScenario.onActivity { mainActivity ->
            val idlingResource = ViewVisibilityIdlingResource(
                mainActivity,
                R.id.progress_bar,
                View.GONE
            )

            IdlingRegistry
                .getInstance()
                .register(idlingResource)
        }
    }

    fun assertProgressBarVisible() = apply {
        onView(PROGRESS_BAR).check(matches(isDisplayed()))
    }

    fun assertProgressBarGone() = apply {
        onView(PROGRESS_BAR).check(matches(not(isDisplayed())))
    }

    fun assertArticleListVisible() = apply {
        onView(ARTICLE_LIST).check(matches(isDisplayed()))
    }

    fun assertArticleListGone() = apply {
        onView(ARTICLE_LIST).check(matches(not(isDisplayed())))
    }

    companion object {
        private val PROGRESS_BAR = withId(R.id.progress_bar)
        private val ARTICLE_LIST = withId(R.id.article_list)
    }
}
