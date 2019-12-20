package com.larapin.footballappkotlin.main.event.tab

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.larapin.footballappkotlin.R
import com.larapin.footballappkotlin.main.event.EventFragment

/**
 * Created by Avin on 14/10/2018.
 * class EventPagerAdapter
 */
class EventPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                EventFragment.newInstance("eventsnextleague",
                        R.id.spinner_event_next,
                        R.id.list_event_next)
            }
            else -> {
                return EventFragment.newInstance("eventspastleague",
                        R.id.spinner_event_last,
                        R.id.list_event_last)
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Next"
            else -> {
                return "Last"
            }
        }
    }
}