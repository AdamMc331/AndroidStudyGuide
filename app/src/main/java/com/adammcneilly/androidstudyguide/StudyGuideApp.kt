package com.adammcneilly.androidstudyguide

import android.app.Application
import android.content.Context
import com.adammcneilly.androidstudyguide.di.BaseStudyGuideGraph
import com.adammcneilly.androidstudyguide.di.DataGraph
import com.adammcneilly.androidstudyguide.di.StudyGuideGraph
import com.adammcneilly.androidstudyguide.di.ViewModelFactoryGraph

class StudyGuideApp : Application() {

    val dependencyGraph: StudyGuideGraph = BaseStudyGuideGraph()
}

fun Context.dependencyGraph(): StudyGuideGraph {
    return (this.applicationContext as StudyGuideApp).dependencyGraph
}

fun Context.dataGraph(): DataGraph {
    return this.dependencyGraph().dataGraph
}

fun Context.viewModelFactoryGraph(): ViewModelFactoryGraph {
    return this.dependencyGraph().viewModelFactoryGraph
}
