package com.yohanesam.footballmatchschedule.view.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.SearchView
import android.view.*
import com.yohanesam.footballmatchschedule.R
import com.yohanesam.footballmatchschedule.view.activities.SearchMatchActivity
import com.yohanesam.footballmatchschedule.view.adapters.MatchesFragmentAdapter
import kotlinx.android.synthetic.main.activity_matches.*
import org.jetbrains.anko.support.v4.startActivity

class MatchesFragment : Fragment() {

    private lateinit var pagerAdapterMatches: MatchesFragmentAdapter

    private var searchKey: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.activity_matches, container, false)

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater) {

        inflater.inflate(R.menu.search_menu, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView

        searchView.queryHint = "Search The team"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(searchedText: String?): Boolean {

                startActivity<SearchMatchActivity>(
                    "SEARCH_QUERY" to searchedText
                )

                return false
            }

            override fun onQueryTextChange(searchedText: String?): Boolean {return false}

        })

        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)

        setFragmentView()

    }

    private fun setFragmentView() {
        pagerAdapterMatches = MatchesFragmentAdapter(childFragmentManager)
        vpMatchViewPager.adapter = pagerAdapterMatches
        tlMainTabLayout.setupWithViewPager(vpMatchViewPager)
    }

}