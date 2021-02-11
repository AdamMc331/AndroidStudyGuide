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
class MaterialBottomNavigationViewDetector : LayoutDetector() {

    override fun getApplicableElements(): Collection<String> {
        return listOf(
            "com.google.android.material.bottomnavigation.BottomNavigationView"
        )
    }

    override fun visitElement(context: XmlContext, element: Element) {
        context.report(
            issue = ISSUE_USING_MATERIAL_BOTTOM_NAVIGATION_VIEW,
            location = context.getLocation(element),
            message = ISSUE_USING_MATERIAL_BOTTOM_NAVIGATION_VIEW.getExplanation(TextFormat.TEXT),
            quickfixData = LintFix.create()
                .replace()
                .text("com.google.android.material.bottomnavigation.BottomNavigationView")
                .with("com.adammcneilly.androidstudyguide.ui.StudyGuideBottomNavigationView")
                .build()
        )
    }

    companion object {
        @JvmStatic
        internal val ISSUE_USING_MATERIAL_BOTTOM_NAVIGATION_VIEW = Issue.create(
            id = "UsingMaterialBottomNavigationView",
            briefDescription = "Use StudyGuideBottomNavigationView In Layouts",
            explanation = "We override the material BottomNavigationView with our own behaviors and so we should rely on our own implementation.",
            category = Category.CUSTOM_LINT_CHECKS,
            priority = 8,
            severity = Severity.ERROR,
            implementation = Implementation(
                MaterialBottomNavigationViewDetector::class.java,
                Scope.RESOURCE_FILE_SCOPE
            )
        )
    }
}
