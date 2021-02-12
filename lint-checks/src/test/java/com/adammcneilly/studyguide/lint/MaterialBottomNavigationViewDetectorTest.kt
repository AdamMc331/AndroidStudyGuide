package com.adammcneilly.studyguide.lint

import com.android.tools.lint.checks.infrastructure.LintDetectorTest
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Issue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@Suppress("UnstableApiUsage")
@RunWith(JUnit4::class)
class MaterialBottomNavigationViewDetectorTest : LintDetectorTest() {
    override fun getDetector(): Detector {
        return MaterialBottomNavigationViewDetector()
    }

    override fun getIssues(): MutableList<Issue> {
        return mutableListOf(
            MaterialBottomNavigationViewDetector.ISSUE_USING_MATERIAL_BOTTOM_NAVIGATION_VIEW
        )
    }

    @Test
    fun expectPass() {
        lint()
            .files(
                xml(
                    "res/layout/layout.xml", """
<com.adammcneilly.androidstudyguide.ui.StudyGuideBottomNavigationView xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    />
"""
                )
            )
            .allowMissingSdk()
            .run()
            .expectClean()
    }

    @Test
    fun expectFail() {
        lint()
            .files(
                xml(
                    "res/layout/layout.xml", """
<com.google.android.material.bottomnavigation.BottomNavigationView xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    />
"""
                )
            )
            .allowMissingSdk()
            .run()
            .expect(
                """
res/layout/layout.xml:2: Error: We override the material BottomNavigationView with our own behaviors and so we should rely on our own implementation. [UsingMaterialBottomNavigationView]
<com.google.android.material.bottomnavigation.BottomNavigationView xmlns:android="http://schemas.android.com/apk/res/android"
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
1 errors, 0 warnings
            """
            )
    }
}
