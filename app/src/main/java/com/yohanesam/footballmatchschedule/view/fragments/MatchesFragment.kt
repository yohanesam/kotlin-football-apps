package com.yohanesam.footballmatchschedule.view.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yohanesam.footballmatchschedule.R
import com.yohanesam.footballmatchschedule.view.Adapter.MatchesFragmentAdapter
import kotlinx.android.synthetic.main.activity_matches.*

class MatchesFragment: Fragment() {

    private lateinit var pagerAdapterMatches: MatchesFragmentAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.activity_matches, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)

        pagerAdapterMatches = MatchesFragmentAdapter(childFragmentManager)
        vpMainViewPager.adapter = pagerAdapterMatches
        tlMainTabLayout.setupWithViewPager(vpMainViewPager)

    }
}