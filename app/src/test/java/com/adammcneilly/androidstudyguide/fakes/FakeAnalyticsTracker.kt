package com.adammcneilly.androidstudyguide.fakes

import com.adammcneilly.androidstudyguide.analytics.AnalyticsTracker

class FakeAnalyticsTracker : AnalyticsTracker {

    override fun trackEvent(eventName: String, properties: Map<String, Any>) {
        TODO("Not yet implemented")
    }
}
