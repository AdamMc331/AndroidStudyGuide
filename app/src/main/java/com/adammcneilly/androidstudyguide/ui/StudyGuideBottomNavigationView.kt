package com.adammcneilly.androidstudyguide.ui

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * In our project, we're not actually doing anything with a subclass of [BottomNavigationView],
 * but to use this as a learning example for writing a custom lint check, to ensure
 * that we use this class instead of the one from the material library.
 */
class StudyGuideBottomNavigationView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BottomNavigationView(context, attrs, defStyleAttr)
