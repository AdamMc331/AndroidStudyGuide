package com.adammcneilly.androidstudyguide.utils

import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.test.espresso.IdlingResource
import java.lang.ref.WeakReference

/**
 * TL;DR -> Given some viewId, idle until it reaches the [expectedVisibility] state.
 *
 * Inspiration: https://gist.github.com/vaughandroid/e2fda716c7cf6853fa79
 */
class ViewVisibilityIdlingResource(
    activity: AppCompatActivity,
    viewId: Int,
    private val expectedVisibility: Int
) : IdlingResource {

    private val view: WeakReference<View> = WeakReference(activity.findViewById(viewId))
    private val name: String =
        "View Visibility for view " + view.get()?.id + "(@" + System.identityHashCode(view) + ")"

    private var resourceCallback: IdlingResource.ResourceCallback? = null

    override fun getName(): String {
        return this.name
    }

    override fun isIdleNow(): Boolean {
        val view = view.get()

        val isIdle = (view == null) || view.visibility == expectedVisibility

        @Suppress("UsePropertyAccessSyntax")
        if (isIdle) {
            resourceCallback?.onTransitionToIdle()
        } else {
            Handler().postDelayed(
                {
                    isIdleNow()
                },
                IDLE_POLL_DELAY_MILLIS
            )
        }

        return isIdle
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        this.resourceCallback = callback
    }

    companion object {
        private const val IDLE_POLL_DELAY_MILLIS = 100L
    }
}
