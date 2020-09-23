package com.adammcneilly.androidstudyguide

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

/**
 * This is the single activity in the application. It should be the user's main entry point
 * into using the study guide.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        setupNavController()

        setupBottomNavigationMenu()
    }

    /**
     * Here we supply a custom [KeepStateNavigator] into our NavController for this activity.
     *
     * Note that to set up this navigator, we want to supply the childFragmentManager from our navHostFragment.
     *
     * In addition, we need to supply the nav graph, which can only be done after we've customized our
     * navController.
     */
    private fun setupNavController() {
        val navController = findNavController(R.id.nav_host_fragment)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)!!

        navController.navigatorProvider.addNavigator(
            KeepStateNavigator(this, navHostFragment.childFragmentManager, R.id.nav_host_fragment)
        )

        navController.setGraph(R.navigation.nav_graph)
    }

    /**
     * Here we connect our [BottomNavigationView] with the NavController for this activity so that
     * the navigation library can handle navigation for us.
     *
     * NOTE: In order for this to work, we had to make sure the fragment ids in `bottom_navigation_menu.xml`
     * matched the fragment ids in `nav_graph.xml`.
     */
    private fun setupBottomNavigationMenu() {
        val bottomNavigationMenu: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationMenu.setupWithNavController(findNavController(R.id.nav_host_fragment))
    }

    /**
     * This was added as an attempt to handle back navigation. When the user is on the bookmarks tab
     * and presses back, we expect they will go to the all articles tab, but currently it just closes
     * the app.
     *
     * This is an open issue that still needs to be resolved.
     */
    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp()
    }
}
