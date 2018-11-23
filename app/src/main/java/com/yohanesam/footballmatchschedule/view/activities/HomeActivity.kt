package com.yohanesam.footballmatchschedule.view.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import com.yohanesam.footballmatchschedule.R
import com.yohanesam.footballmatchschedule.R.id.*
import com.yohanesam.footballmatchschedule.view.fragments.FavoriteFragment
import com.yohanesam.footballmatchschedule.view.fragments.MatchesFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private lateinit var spinnerArrayAdapter: ArrayAdapter<String>
    var value: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setLeagueList()


        setFrameLayout(savedInstanceState)
    }

    private fun setFrameLayout(savedInstanceState: Bundle?) {

        bnvBottomNavigation.setOnNavigationItemSelectedListener { item ->

            when (item.itemId) {

                matches -> { loadMatchesFragment(savedInstanceState) }

                favorites -> { loadFavoritesFragment(savedInstanceState) }

            }

            true

        }

        bnvBottomNavigation.selectedItemId = matches

    }

    fun setLeagueList()/*: String?*/ {

//        spinnerArrayAdapter = ArrayAdapter(this, R.layout.match_spinner_layout, resources.getStringArray(R.array.homeSpinnerMenu))
//        spHomeSpinner.adapter = spinnerArrayAdapter

//        spHomeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                spHomeSpinner.setSelection(spinnerArrayAdapter.getPosition("England Premier League"))
//                value = "4328"
//
//                Log.d("VALUE", "SINI BISA")
//            }
//
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//
//                when(position) {
//                    0 -> value = "4328"
//                    1 -> value = "4335"
//                    2 -> value = "4332"
//                    3 -> value = "4331"
//                }
//
//                Log.d("VALUE", "SINI BISA JUGA")
//
//            }
//
//        }
//
//        return value

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
                .replace(R.id.flMainContainer, FavoriteFragment(), FavoriteFragment::class.java.simpleName)
                .commit()

        }
    }

}
