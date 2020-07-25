package com.adammcneilly.androidstudyguide.util

import android.text.Spanned
import androidx.core.text.HtmlCompat

/**
 * https://kotlinlang.org/docs/reference/inline-classes.html
 */
inline class HtmlString(private val input: String) {
    fun getSpanned(): Spanned {
        return HtmlCompat.fromHtml(input, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}
