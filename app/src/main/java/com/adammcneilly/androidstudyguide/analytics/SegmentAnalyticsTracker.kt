package com.adammcneilly.androidstudyguide.analytics

import com.segment.analytics.Analytics
import com.segment.analytics.Properties

class SegmentAnalyticsTracker(
    private val segmentInstance: Analytics,
) : AnalyticsTracker {

    override fun trackEvent(eventName: String, properties: Map<String, Any>) {
        val segmentProperties = Properties()

        properties.forEach { (key, value) ->
            segmentProperties[key] = value
        }

        segmentInstance.track(
            eventName,
            segmentProperties,
        )
    }
}
