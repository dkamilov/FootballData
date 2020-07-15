package com.damir.android.myscore.ui.competitions.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class CompetitionStatisticsPagerAdapter(
    fragment: Fragment,
    private val competitionId: Int)
    : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment: Fragment?
        val args = Bundle().apply {
            putInt("competitionId", competitionId)
        }
        fragment = when(position) {
            0 -> CompetitionStandingsFragment()
            1 -> CompetitionScorersFragment()
            else -> CompetitionStandingsFragment()
        }
        fragment.arguments = args
        return fragment
    }

}