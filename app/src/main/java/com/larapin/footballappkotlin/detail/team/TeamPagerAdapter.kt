package com.larapin.footballappkotlin.detail.team

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.larapin.footballappkotlin.main.player.PlayerFragment

/**
 * Created by Avin on 18/10/2018.
 * class TeamPagerAdapter
 */
class TeamPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                TeamOverviewFragment()
            }
            else -> {
                PlayerFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Overview"
            else -> {
                return "Players"
            }
        }
    }
}