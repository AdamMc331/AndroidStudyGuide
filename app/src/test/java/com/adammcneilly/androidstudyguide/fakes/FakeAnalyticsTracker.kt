package com.adammcneilly.androidstudyguide.fakes

import com.adammcneilly.androidstudyguide.analytics.AnalyticsEvent
import com.adammcneilly.androidstudyguide.analytics.AnalyticsTracker
import com.google.common.truth.Truth.assertThat

class FakeAnalyticsTracker : AnalyticsTracker {

    private val trackedEvents: MutableList<AnalyticsEvent> = mutableListOf()

    override fun trackEvent(event: AnalyticsEvent) {
        trackedEvents.add(event)
    }

    fun assertEventTracked(expectedEvent: AnalyticsEvent) {
        assertThat(trackedEvents).contains(expectedEvent)
    }
}
