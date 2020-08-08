package com.adammcneilly.androidstudyguide

import android.app.Application
import com.adammcneilly.androidstudyguide.di.dataModule
import com.adammcneilly.androidstudyguide.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class StudyGuideApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@StudyGuideApp)
            modules(dataModule)
            modules(viewModelModule)
        }
    }
}
