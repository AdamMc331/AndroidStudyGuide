package com.adammcneilly.androidstudyguide.analytics

/**
 * We want to create our own contract for tracking analytics
 * events in the codebase, so that we aren't tightly coupled
 * to any specific third party vendor.
 *
 * By having our ViewModels depend on this interface, rather than
 * directly on Firebase, Segment, etc - we gives ourselves the capability
 * to swap between those tools, and also easily create fake implementations
 * in unit tests.
 */
interface AnalyticsTracker {

    /**
     * This method should be called for any event in the application that we want to track.
     */
    fun trackEvent(
        event: AnalyticsEvent,
    )
}
