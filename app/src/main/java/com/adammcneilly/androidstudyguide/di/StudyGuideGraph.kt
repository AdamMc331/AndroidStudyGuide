package com.adammcneilly.androidstudyguide.di

/**
 * Defines the application wide dependencies and sub graphs.
 */
interface StudyGuideGraph {
    val dataGraph: DataGraph
    val viewModelFactoryGraph: ViewModelFactoryGraph
}

/**
 * This implementation is the production version of our dependency graph.
 */
class BaseStudyGuideGraph : StudyGuideGraph {
    override val dataGraph: DataGraph = NetworkDataGraph()

    override val viewModelFactoryGraph: ViewModelFactoryGraph = BaseViewModelFactoryGraph(dataGraph)
}
