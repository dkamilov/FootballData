package com.damir.android.myscore.ui.competitions.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.damir.android.myscore.R
import com.damir.android.myscore.databinding.FragmentCompetitionMatchesBinding
import com.damir.android.myscore.ui.competitions.domain.model.CompetitionMatchDomainModel
import com.damir.android.myscore.utils.view.BaseFragment
import org.koin.android.viewmodel.ext.android.sharedViewModel

class CompetitionMatchesFragment : BaseFragment<FragmentCompetitionMatchesBinding>() {

    private var competitionMatchesAdapter: CompetitionMatchesAdapter? = null
    private val args: CompetitionMatchesFragmentArgs by navArgs()
    private val competitionMatchesViewModel: CompetitionMatchesViewModel by sharedViewModel()

    override fun setBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCompetitionMatchesBinding =
        FragmentCompetitionMatchesBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupRecyclerView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val competitionId = args.competitionId
        val matchday = args.matchday
        competitionMatchesViewModel.getCompetitionMatches(competitionId, matchday)
            .observe(viewLifecycleOwner, Observer { matches ->
                updateMatches(matches)
            })
        competitionMatchesViewModel.dataLoading
            .observe(viewLifecycleOwner, Observer { isLoading ->
            showProgress(isLoading)
        })
        competitionMatchesViewModel.errorMessage.observe(viewLifecycleOwner, Observer { error ->
            showErrorMessage(error)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        competitionMatchesAdapter = null
    }

    private fun setupToolbar() {
        binding.toolbar.title = args.competition
        binding.toolbar.subtitle = getString(R.string.formatted_matchday, args.matchday)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId) {
                R.id.menu_statistics -> {
                    navigateToStatistics()
                    true
                }
                R.id.menu_info -> {
                    showDialogInfo()
                    true
                }
                //TODO: create matchday chooser
                R.id.menu_matchday -> {
                    true
                }
                else -> false
            }
        }
    }

    private fun setupRecyclerView() {
        setupAdapter()
        binding.recyclerCompetitionMatches.adapter = competitionMatchesAdapter
    }

    private fun setupAdapter() {
        competitionMatchesAdapter = CompetitionMatchesAdapter { matchId, teams ->
            //TODO: navigate to match details
        }
    }

    private fun updateMatches(matches: List<CompetitionMatchDomainModel>) {
        competitionMatchesAdapter?.updateMatches(matches)
    }

    private fun showProgress(isLoading: Boolean) {
        binding.progressBar.isVisible = isLoading
        binding.recyclerCompetitionMatches.isVisible = !isLoading
    }

    private fun navigateToStatistics() {
        val action = CompetitionMatchesFragmentDirections.actionCompetitionMatchesFragmentToCompetitionStatisticsFragment(
                args.competitionId,
                args.competition)
        findNavController().navigate(action)
    }

    private fun showDialogInfo() {
        val infoDialog = CompetitionMatchesInfoDialog()
        infoDialog.show(childFragmentManager, "info")
    }
}