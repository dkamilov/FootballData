package com.damir.android.myscore.ui.competitions.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.damir.android.myscore.databinding.FragmentCompetitionsBinding
import com.damir.android.myscore.ui.competitions.domain.model.CompetitionDomainModel
import com.damir.android.myscore.utils.view.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class CompetitionsFragment : BaseFragment<FragmentCompetitionsBinding>() {

    private lateinit var competitionsAdapter: CompetitionsAdapter
    private val competitionsViewModel: CompetitionsViewModel by viewModel()

    override fun setBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentCompetitionsBinding =
        FragmentCompetitionsBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        competitionsViewModel.getAllCompetitions().observe(viewLifecycleOwner, Observer { competitions ->
            updateCompetitions(competitions)
        })
        competitionsViewModel.dataLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            showProgress(isLoading)
        })
        competitionsViewModel.errorMessage.observe(viewLifecycleOwner, Observer { error ->
            showErrorMessage(error)
        })
    }

    private fun setupRecyclerView() {
        setupAdapter()
        val recyclerView = binding.recyclerCompetitions
        recyclerView.adapter = competitionsAdapter
    }

    private fun setupAdapter() {
        competitionsAdapter =
            CompetitionsAdapter { competitionId, matchday, competition ->
                navigateToCompetitionMatches(competitionId, matchday, competition)
            }
    }

    private fun updateCompetitions(items: List<CompetitionDomainModel>) {
        competitionsAdapter.updateList(items)
    }

    private fun showProgress(isLoading: Boolean) {
        binding.progressBar.isVisible = isLoading
        binding.recyclerCompetitions.isVisible = !isLoading
    }

    private fun navigateToCompetitionMatches(competitionId: Int, matchday: Int, competition: String) {
        val action = CompetitionsFragmentDirections
            .actionCompetitionsFragmentToCompetitionMatchesFragment(competitionId, matchday, competition)
        findNavController().navigate(action)
    }
}