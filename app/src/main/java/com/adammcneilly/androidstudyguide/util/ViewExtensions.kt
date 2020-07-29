package com.adammcneilly.androidstudyguide.util

import android.view.View

fun View.visibleIf(condition: Boolean) {
    this.visibility = if (condition) {
        View.VISIBLE
    } else {
        View.GONE
    }
}
