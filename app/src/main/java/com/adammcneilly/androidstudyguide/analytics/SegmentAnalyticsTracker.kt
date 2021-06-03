package com.adammcneilly.androidstudyguide.analytics

import com.segment.analytics.Analytics
import com.segment.analytics.Properties

class SegmentAnalyticsTracker(
    private val segmentInstance: Analytics,
) : AnalyticsTracker {

    override fun trackEvent(event: AnalyticsEvent) {
        val segmentProperties = Properties()

        event.properties.forEach { (key, value) ->
            segmentProperties[key] = value
        }

        segmentInstance.track(
            event.eventName,
            segmentProperties,
        )
    }
}
