package com.adammcneilly.studyguide.lint

import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.LayoutDetector
import com.android.tools.lint.detector.api.LintFix
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.TextFormat
import com.android.tools.lint.detector.api.XmlContext
import org.w3c.dom.Element

@Suppress("UnstableApiUsage")
class UnusedStudyGuideViewDetector : LayoutDetector() {

    override fun getApplicableElements(): Collection<String> {
        return VIEW_REPLACEMENT_MAP.keys
    }

    override fun visitElement(context: XmlContext, element: Element) {
        val foundViewName = element.nodeName
        val suggestedName = VIEW_REPLACEMENT_MAP[foundViewName]

        context.report(
            issue = ISSUE_UNUSED_STUDY_GUIDE_VIEW,
            location = context.getNameLocation(element),
            message = ISSUE_UNUSED_STUDY_GUIDE_VIEW.getExplanation(TextFormat.TEXT),
            quickfixData = LintFix.create()
                .replace()
                .text(foundViewName)
                .with(suggestedName)
                .build()
        )
    }

    companion object {
        private const val MATERIAL_BOTTOM_NAVIGATION_VIEW =
            "com.google.android.material.bottomnavigation.BottomNavigationView"

        private const val STUDY_GUIDE_BOTTOM_NAVIGATION_VIEW =
            "com.adammcneilly.androidstudyguide.ui.StudyGuideBottomNavigationView"

        private val VIEW_REPLACEMENT_MAP = mapOf(
            MATERIAL_BOTTOM_NAVIGATION_VIEW to STUDY_GUIDE_BOTTOM_NAVIGATION_VIEW
        )

        @JvmStatic
        internal val ISSUE_UNUSED_STUDY_GUIDE_VIEW = Issue.create(
            id = "UnusedStudyGuideView",
            briefDescription = "Replace Material Design Components With Study Guide Custom Views",
            explanation = "This view must be replaced by a custom Study Guide implementation.",
            category = Category.CUSTOM_LINT_CHECKS,
            priority = 10,
            severity = Severity.ERROR,
            implementation = Implementation(
                UnusedStudyGuideViewDetector::class.java,
                Scope.RESOURCE_FILE_SCOPE
            )
        )
    }
}
