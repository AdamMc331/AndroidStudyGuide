package com.adammcneilly.androidstudyguide

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class TestObserver<T> : Observer<T> {
    var observedValue: T? = null

    override fun onChanged(t: T) {
        observedValue = t
    }
}

fun <T> LiveData<T>.testObserver() = TestObserver<T>().also {
    observeForever(it)
}

@ExperimentalCoroutinesApi
class CoroutinesTestRule(
    private val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
) : TestWatcher() {

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}
