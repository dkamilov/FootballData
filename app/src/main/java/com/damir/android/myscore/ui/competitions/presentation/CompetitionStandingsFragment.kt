package com.damir.android.myscore.ui.competitions.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.damir.android.myscore.databinding.FragmentCompetitionStandingsBinding
import com.damir.android.myscore.model.TableTeam
import com.damir.android.myscore.utils.view.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class CompetitionStandingsFragment : BaseFragment<FragmentCompetitionStandingsBinding>() {

    private var competitionStandingsAdapter: CompetitionStandingsAdapter? = null
    private val competitionStandingsViewModel: CompetitionStandingsViewModel by viewModel()

    override fun setBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCompetitionStandingsBinding =
        FragmentCompetitionStandingsBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val competitionId = arguments?.getInt("competitionId")!!
        competitionStandingsViewModel.getCompetitionStandings(competitionId)
            .observe(viewLifecycleOwner, Observer { standings ->
                standings?.let {
                    val teams = it.standings[0].table
                    updateTeams(teams)
                }
        })
        competitionStandingsViewModel.dataLoading
            .observe(viewLifecycleOwner, Observer { isLoading ->
                showProgress(isLoading)
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        competitionStandingsAdapter = null
    }

    private fun setupRecyclerView() {
        setupAdapter()
        binding.recyclerStandings.adapter = competitionStandingsAdapter
    }

    private fun setupAdapter() {
        competitionStandingsAdapter = CompetitionStandingsAdapter()
    }

    private fun updateTeams(teams: List<TableTeam>) {
        competitionStandingsAdapter?.updateTeams(teams)
    }

    private fun showProgress(isLoading: Boolean) {
        binding.progressBar.isVisible = isLoading
        binding.recyclerStandings.isVisible = !isLoading
    }
}