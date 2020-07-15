package com.damir.android.myscore.ui.competitions.presentation

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.damir.android.myscore.R
import com.damir.android.myscore.Result
import com.damir.android.myscore.databinding.DialogCompetitionMatchesInfoBinding
import com.damir.android.myscore.ui.competitions.domain.model.CompetitionInfoDomainModel
import com.damir.android.myscore.utils.extensions.loadSvg
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.android.viewmodel.ext.android.sharedViewModel

class CompetitionMatchesInfoDialog : DialogFragment() {

    private val competitionMatchesViewModel: CompetitionMatchesViewModel by sharedViewModel()
    private lateinit var binding: DialogCompetitionMatchesInfoBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = MaterialAlertDialogBuilder(it)
            val inflater = requireActivity().layoutInflater
            binding = DialogCompetitionMatchesInfoBinding.inflate(inflater)
            setViewContent()
            builder.setView(binding.root)
            builder.setPositiveButton(R.string.ok) { _, _ ->
                dismiss()
            }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun setViewContent() {
        competitionMatchesViewModel.competitionInfo.observe(
            requireParentFragment().viewLifecycleOwner,
            Observer { result ->
                handleResult(result)
            }
        )
    }

    private fun handleResult(result: Result<CompetitionInfoDomainModel>) {
        when(result) {
            is Result.Success -> { updateViewContent(result.data) }
            is Result.Error -> { updateViewContent(null) }
        }
    }

    private fun updateViewContent(info: CompetitionInfoDomainModel?) {
        if(info == null) {
            binding.textCompetitionName.text = getString(R.string.error_http)
        } else {
            binding.textAreaName.text = info.area
            binding.textCompetitionName.text = info.name
            binding.textMatchday.text = getString(R.string.formatted_matchday, info.currentMatchday)
            binding.textSeasonDate.text = getString(R.string.formatted_info_date, info.startDate, info.endDate)
            val lastSeason = info.seasons.getOrNull(1)
            val lastSeasonWinner = lastSeason?.winner
            binding.textLastSeasonDate.text = getString(
                R.string.formatted_info_date,
                lastSeason?.startDate,
                lastSeason?.endDate)
            binding.textLastSeasonWinner.text = lastSeasonWinner?.name
            lastSeasonWinner?.crestUrl?.let { url ->
                binding.imgLastSeasonWinner.loadSvg(url)
            }
        }
    }
}