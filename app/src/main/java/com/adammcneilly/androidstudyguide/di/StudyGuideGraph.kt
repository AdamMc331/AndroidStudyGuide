package com.adammcneilly.androidstudyguide.di

/**
 * Defines the application wide dependencies and sub graphs.
 */
interface StudyGuideGraph {
    val dataGraph: DataGraph
}

/**
 * This implementation is the production version of our dependency graph.
 */
class BaseStudyGuideGraph : StudyGuideGraph {
    override val dataGraph: DataGraph = NetworkDataGraph()
}
