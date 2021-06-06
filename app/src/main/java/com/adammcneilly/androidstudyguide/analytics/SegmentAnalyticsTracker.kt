package com.adammcneilly.androidstudyguide.analytics

import com.segment.analytics.Analytics
import com.segment.analytics.Properties

/**
 * A concrete implementation of an [AnalyticsTracker] that will send all events to our [segmentInstance].
 */
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
