package com.adammcneilly.androidstudyguide

import android.app.Application
import com.segment.analytics.Analytics
import com.segment.analytics.AnalyticsContext
import dagger.hilt.android.HiltAndroidApp

/**
 * The application class is where we should configure any application wide dependencies or libraries,
 * such as Dagger Hilt, hence the [HiltAndroidApp] annotation.
 */
@HiltAndroidApp
class StudyGuideApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initializeSegment()
    }

    /**
     * This initializes a Segment [Analytics] instance, which is a third party tool for
     * analytics tracking in mobile apps.
     */
    private fun initializeSegment() {
        val analytics: Analytics = Analytics.Builder(
            applicationContext,
            BuildConfig.SEGMENT_API_KEY,
        )
            .trackApplicationLifecycleEvents()
            .recordScreenViews()
            .build()

        Analytics.setSingletonInstance(analytics)
    }
}
