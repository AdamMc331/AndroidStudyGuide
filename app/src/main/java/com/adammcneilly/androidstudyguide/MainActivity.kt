package com.adammcneilly.androidstudyguide

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
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

        setupBottomNavigationMenu()
    }

    private fun setupBottomNavigationMenu() {
        val bottomNavigationMenu: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationMenu.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.all_articles -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.ArticleListFragment)
                    true
                }
                R.id.bookmarks -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.BookmarkListFragment)
                    true
                }
                else -> {
                    false
                }
            }
        }
    }
}
