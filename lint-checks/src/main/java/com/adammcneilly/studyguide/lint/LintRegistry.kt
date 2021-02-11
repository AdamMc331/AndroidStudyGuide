package com.adammcneilly.studyguide.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue

@Suppress("UnstableApiUsage")
class LintRegistry : IssueRegistry() {
    override val api: Int
        get() = CURRENT_API

    override val issues: List<Issue>
        get() = listOf(
            MaterialBottomNavigationViewDetector.ISSUE_USING_MATERIAL_BOTTOM_NAVIGATION_VIEW
        )
}
