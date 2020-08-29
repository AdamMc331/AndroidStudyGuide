package com.adammcneilly.androidstudyguide.util

import android.text.Spanned
import androidx.core.text.HtmlCompat

/**
 * This is an inline class that is a helper to convert some [input] String to a [Spanned] that can
 * render some HTML to the UI.
 *
 * https://kotlinlang.org/docs/reference/inline-classes.html
 */
inline class HtmlString(private val input: String) {
    fun getSpanned(): Spanned {
        return HtmlCompat.fromHtml(input, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }

    fun getInput(): String {
        return input
    }
}
