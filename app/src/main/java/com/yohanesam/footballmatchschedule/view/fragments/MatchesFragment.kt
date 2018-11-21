package com.yohanesam.footballmatchschedule.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.yohanesam.footballmatchschedule.R
import com.yohanesam.footballmatchschedule.view.adapters.MatchesFragmentAdapter
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_matches.*

class MatchesFragment: Fragment() {

    private lateinit var pagerAdapterMatches: MatchesFragmentAdapter
//    private lateinit var spinnerArrayAdapter: ArrayAdapter<Any>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.activity_matches, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)

        setFragmentView()
//        setSpinnerView()

    }

//    @SuppressLint("ResourceType")
//    private fun setSpinnerView() {
//
//        spinnerArrayAdapter = ArrayAdapter(activity, R.id.spHomeSpinnerMenu, resources.getStringArray(R.array.homeSpinnerMenu))
//        spHomeSpinnerMenu.adapter = spinnerArrayAdapter
//        spHomeSpinnerMenu.onItemSelectedListener = object : AdapterView.OnItemClickListener,
//            AdapterView.OnItemSelectedListener {
//
//            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//
//        }
//
//    }

    private fun setFragmentView() {
        pagerAdapterMatches = MatchesFragmentAdapter(childFragmentManager)
        vpMainViewPager.adapter = pagerAdapterMatches
        tlMainTabLayout.setupWithViewPager(vpMainViewPager)
    }


}