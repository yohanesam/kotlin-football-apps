package com.yohanesam.footballmatchschedule.view.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mancj.materialsearchbar.MaterialSearchBar
import com.yohanesam.footballmatchschedule.R
import com.yohanesam.footballmatchschedule.view.adapters.MatchesFragmentAdapter
import kotlinx.android.synthetic.main.activity_matches.*

class MatchesFragment : Fragment(), MaterialSearchBar.OnSearchActionListener {

    private lateinit var pagerAdapterMatches: MatchesFragmentAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.activity_matches, container, false)

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

    override fun onButtonClicked(buttonCode: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSearchStateChanged(enabled: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSearchConfirmed(text: CharSequence?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}