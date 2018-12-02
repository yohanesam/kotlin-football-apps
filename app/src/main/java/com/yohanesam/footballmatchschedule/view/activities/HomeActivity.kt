package com.yohanesam.footballmatchschedule.view.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yohanesam.footballmatchschedule.R
import com.yohanesam.footballmatchschedule.R.id.*
import com.yohanesam.footballmatchschedule.view.fragments.FavoriteMatchFragment
import com.yohanesam.footballmatchschedule.view.fragments.MatchesFragment
import com.yohanesam.footballmatchschedule.view.fragments.TeamsFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        setFrameLayout(savedInstanceState)
    }

    private fun setFrameLayout(savedInstanceState: Bundle?) {

        bnvBottomNavigation.setOnNavigationItemSelectedListener { item ->

            when (item.itemId) {

                matches -> {
                    loadMatchesFragment(savedInstanceState)
                }

                teams -> {
                    loadTeamsFragment(savedInstanceState)
                }

                favorites -> {
                    loadFavoritesFragment(savedInstanceState)
                }

            }

            true

        }

        bnvBottomNavigation.selectedItemId = matches

    }

    private fun loadTeamsFragment(savedInstanceState: Bundle?) {

        if (savedInstanceState == null) {

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.flMainContainer, TeamsFragment(), MatchesFragment::class.java.simpleName)
                .commit()

        }

    }

    private fun loadMatchesFragment(savedInstanceState: Bundle?) {

        if (savedInstanceState == null) {

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.flMainContainer, MatchesFragment(), MatchesFragment::class.java.simpleName)
                .commit()

        }
    }

    private fun loadFavoritesFragment(savedInstanceState: Bundle?) {

        if (savedInstanceState == null) {

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.flMainContainer, FavoriteMatchFragment(), FavoriteMatchFragment::class.java.simpleName)
                .commit()

        }
    }

}
