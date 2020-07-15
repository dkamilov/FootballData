package com.damir.android.myscore.ui.competitions.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.damir.android.myscore.Result
import com.damir.android.myscore.ui.competitions.domain.model.CompetitionStandingDomainModel
import com.damir.android.myscore.ui.competitions.domain.repository.CompetitionStandingsRepository
import com.damir.android.myscore.utils.view.BaseViewModel
import kotlinx.coroutines.launch

class CompetitionStandingsViewModel(
    private val competitionStandingsRepository: CompetitionStandingsRepository
) : BaseViewModel() {

    private val competitionStandings = MutableLiveData<CompetitionStandingDomainModel?>()

    fun getCompetitionStandings(competitionId: Int): LiveData<CompetitionStandingDomainModel?> {
        viewModelScope.launch {
            _dataLoading.value = true
            val standings = competitionStandingsRepository
                .getCompetitionStandings(competitionId)
            when(standings) {
                is Result.Success -> { handleStandingsSuccess(standings.data) }
                is Result.Error -> { handleStandingsError(standings) }
            }
        }
        return competitionStandings
    }

    private fun handleStandingsSuccess(standings: CompetitionStandingDomainModel) {
        _dataLoading.value = false
        _errorMessage.value = null
        competitionStandings.value = standings
    }

    private fun handleStandingsError(error: Result.Error) {
        competitionStandings.value = null
        _errorMessage.value = error
    }
}