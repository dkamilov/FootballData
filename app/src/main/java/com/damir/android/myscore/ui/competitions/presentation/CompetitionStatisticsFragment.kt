package com.damir.android.myscore.ui.competitions.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.damir.android.myscore.R
import com.damir.android.myscore.databinding.FragmentCompetitionStatisticsBinding
import com.damir.android.myscore.utils.view.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.viewmodel.ext.android.sharedViewModel

class CompetitionStatisticsFragment : BaseFragment<FragmentCompetitionStatisticsBinding>() {

    private val args: CompetitionStatisticsFragmentArgs by navArgs()
    private lateinit var competitionStatisticsPagerAdapter: CompetitionStatisticsPagerAdapter

    override fun setBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCompetitionStatisticsBinding =
        FragmentCompetitionStatisticsBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupViewPager()
    }

    private fun setupToolbar() {
        binding.toolbar.title = args.competitionName
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupViewPager() {
        competitionStatisticsPagerAdapter = CompetitionStatisticsPagerAdapter(this, args.competitionId)
        binding.viewPager.adapter = competitionStatisticsPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = when(position) {
                0 -> getString(R.string.standings)
                else -> getString(R.string.players)
            }
        }.attach()
    }
}