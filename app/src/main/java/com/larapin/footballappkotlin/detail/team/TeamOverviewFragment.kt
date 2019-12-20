package com.larapin.footballappkotlin.detail.team


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.larapin.footballappkotlin.R
import org.jetbrains.anko.find


/**
 * TeamOverviewFragment
 */
class TeamOverviewFragment : Fragment() {

    private lateinit var tvTeamOverview: TextView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_team_overview, container, false)

        tvTeamOverview = view.find(R.id.tv_team_overview)
        tvTeamOverview.text = arguments?.getString("teamoverview")

        return view
    }
}
