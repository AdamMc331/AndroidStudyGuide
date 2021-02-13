package com.adammcneilly.studyguide.lint

import com.android.tools.lint.checks.infrastructure.LintDetectorTest
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Issue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@Suppress("UnstableApiUsage")
@RunWith(JUnit4::class)
class UnusedStudyGuideViewDetectorTest : LintDetectorTest() {
    override fun getDetector(): Detector {
        return UnusedStudyGuideViewDetector()
    }

    override fun getIssues(): MutableList<Issue> {
        return mutableListOf(
            UnusedStudyGuideViewDetector.ISSUE_UNUSED_STUDY_GUIDE_VIEW
        )
    }

    @Test
    fun passesWithStudyGuideBottomNavigationView() {
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
    fun failsWithMaterialBottomNavigationView() {
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
res/layout/layout.xml:2: Error: This view must be replaced by a custom Study Guide implementation. [UnusedStudyGuideView]
<com.google.android.material.bottomnavigation.BottomNavigationView xmlns:android="http://schemas.android.com/apk/res/android"
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
1 errors, 0 warnings
            """
            )
    }

    @Test
    fun canFixMaterialBottomNavigationView() {
        lint()
            .files(
                xml(
                    "res/layout/layout.xml",
                    """
<com.google.android.material.bottomnavigation.BottomNavigationView xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    />
"""
                )
            )
            .allowMissingSdk()
            .run()
            .verifyFixes()
            .expectFixDiffs(
                """
Fix for res/layout/layout.xml line 2: Replace with com.adammcneilly.androidstudyguide.ui.StudyGuideBottomNavigationView:
@@ -2 +2
- <com.google.android.material.bottomnavigation.BottomNavigationView xmlns:android="http://schemas.android.com/apk/res/android"
+ <com.adammcneilly.androidstudyguide.ui.StudyGuideBottomNavigationView xmlns:android="http://schemas.android.com/apk/res/android"
                """.trimIndent()
            )
    }
}
