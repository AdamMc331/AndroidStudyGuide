package com.adammcneilly.androidstudyguide.articlelist

import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adammcneilly.androidstudyguide.MainActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AndroidEssenceArticleListFragmentTest {

    /**
     * Because we can't launch the fragment in its own scenario, we have to first launch an instance
     * of [MainActivity]. Since the [AndroidEssenceArticleListFragment] is the first fragment
     * that appears in the app, we can run assertions on it right away.
     *
     * https://stackoverflow.com/a/63239932/3131147
     */
    @Test
    fun createFragment() {
        val scenario = launchActivity<MainActivity>()

        AndroidEssenceArticleListFragmentRobot(scenario)
            .assertArticleListGone()
            .assertProgressBarVisible()
            .waitForProgressBarToBeGone()
            .assertProgressBarGone()
            .assertArticleListVisible()
    }
}
