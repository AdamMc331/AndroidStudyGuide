package com.adammcneilly.androidstudyguide.analytics

import androidx.core.os.bundleOf
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

/**
 * This is a concrete implementation of [AnalyticsTracker] that
 * will send any events tracked by the app to [FirebaseAnalytics].
 */
class FirebaseAnalyticsTracker : AnalyticsTracker {

    override fun trackEvent(eventName: String, properties: Map<String, Any>) {
        Firebase.analytics.logEvent(
            eventName,
            bundleOf(*properties.toList().toTypedArray())
        )
    }
}
