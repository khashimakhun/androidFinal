package com.larapin.footballappkotlin.favorite

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.larapin.footballappkotlin.favorite.event.FavoriteEventFragment
import com.larapin.footballappkotlin.favorite.team.FavoriteTeamFragment

/**
 * Created by Avin on 14/10/2018.
 * class FavoritePagerAdapter
 */
class FavoritePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                FavoriteEventFragment()
            }
            else -> {
                return FavoriteTeamFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Matches"
            else -> {
                return "Teams"
            }
        }
    }
}