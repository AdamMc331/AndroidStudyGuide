package com.adammcneilly.androidstudyguide.util

import android.view.View

/**
 * Configures the visibility of a View to visible or gone based on the supplied [condition].
 */
fun View.visibleIf(condition: Boolean) {
    this.visibility = if (condition) {
        View.VISIBLE
    } else {
        View.GONE
    }
}
