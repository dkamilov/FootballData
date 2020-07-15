package com.damir.android.myscore.ui.competitions.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.damir.android.myscore.Result
import com.damir.android.myscore.ui.competitions.domain.model.CompetitionScorersDomainModel
import com.damir.android.myscore.ui.competitions.domain.repository.CompetitionScorersRepository
import com.damir.android.myscore.utils.view.BaseViewModel
import kotlinx.coroutines.launch

class CompetitionScorersViewModel(
    private val competitionScorersRepository: CompetitionScorersRepository
) : BaseViewModel() {

    private val competitionScorers = MutableLiveData<CompetitionScorersDomainModel?>()

    fun getCompetitionScorers(competitionId: Int): LiveData<CompetitionScorersDomainModel?> {
        viewModelScope.launch {
            _dataLoading.value = true
            val scorers = competitionScorersRepository
                .getCompetitionScorers(competitionId)
            when(scorers) {
                is Result.Success -> { handleScorersSuccess(scorers.data) }
                is Result.Error -> { handleScorersError() }
            }
        }
        return competitionScorers
    }

    private fun handleScorersSuccess(scorers: CompetitionScorersDomainModel) {
        _dataLoading.value = false
        competitionScorers.value = scorers
    }

    private fun handleScorersError() {
        _errorMessage.value
    }

}