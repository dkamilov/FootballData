package com.damir.android.myscore.ui.competitions.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.damir.android.myscore.databinding.FragmentCompetitionScorerBinding
import com.damir.android.myscore.model.Scorer
import com.damir.android.myscore.utils.view.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class CompetitionScorersFragment : BaseFragment<FragmentCompetitionScorerBinding>() {

    private var competitionScorersAdapter: CompetitionScorersAdapter? = null
    private val competitionScorersViewModel : CompetitionScorersViewModel by viewModel()

    override fun setBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCompetitionScorerBinding =
        FragmentCompetitionScorerBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val competitionId = arguments?.getInt("competitionId", 0)!!
        competitionScorersViewModel.getCompetitionScorers(competitionId)
            .observe(viewLifecycleOwner, Observer {
                it?.let {
                    updateScorers(it.scorers)
                }
            })
        competitionScorersViewModel.dataLoading
            .observe(viewLifecycleOwner, Observer { isLoading ->
                showLoading(isLoading)
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        competitionScorersAdapter = null
    }

    private fun setupRecyclerView() {
        competitionScorersAdapter = CompetitionScorersAdapter()
        binding.recyclerScorer.adapter = competitionScorersAdapter
    }

    private fun updateScorers(scorers: List<Scorer>) {
        competitionScorersAdapter?.updateScorers(scorers)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.recyclerScorer.isVisible = !isLoading
        binding.progressBar.isVisible = isLoading
    }
}