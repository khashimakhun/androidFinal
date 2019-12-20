package com.larapin.footballappkotlin.main.event.tab


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.larapin.footballappkotlin.R
import org.jetbrains.anko.find

/**
 * EventTabFragment
 */
class EventTabFragment : Fragment() {
    private lateinit var viewPager: ViewPager
    private lateinit var tabs: TabLayout
    private lateinit var toolbar: Toolbar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_event_tab, container, false)
        viewPager = view.find(R.id.viewpager_event)
        tabs = view.find(R.id.tabs_event)
        toolbar = view.find(R.id.toolbar_event)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        val fragmentAdapter = EventPagerAdapter(childFragmentManager)
        viewPager.adapter = fragmentAdapter
        tabs.setupWithViewPager(viewPager)

        return view
    }

    companion object {
        fun newInstance(): EventTabFragment{
            val fragment = EventTabFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

}// Required empty public constructor
