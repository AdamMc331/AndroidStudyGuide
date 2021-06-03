package com.adammcneilly.androidstudyguide.analytics

/**
 * @property[eventName] A unique identifier for the event being tracked.
 * @property[properties] A map of properties related to the event that provide more
 * detailed information, such as which item was clicked or how a switch was toggled.
 */
interface AnalyticsEvent {
    val eventName: String

    val properties: Map<String, Any>
}
