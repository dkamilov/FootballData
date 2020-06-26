package com.damir.android.myscore.ui.competitions.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.damir.android.myscore.Result
import com.damir.android.myscore.ui.competitions.domain.model.CompetitionMatchDomainModel
import com.damir.android.myscore.ui.competitions.domain.repository.CompetitionMatchesRepository
import com.damir.android.myscore.utils.view.BaseViewModel
import kotlinx.coroutines.launch
import java.lang.Exception

class CompetitionMatchesViewModel(
    private val competitionMatchesRepository: CompetitionMatchesRepository
) : BaseViewModel() {

    private val competitionMatches = MutableLiveData<List<CompetitionMatchDomainModel>>()

    fun getCompetitionMatches(competitionId: Int, matchday: Int)
            : LiveData<List<CompetitionMatchDomainModel>> {
        viewModelScope.launch {
            _dataLoading.value = true
            val matches = competitionMatchesRepository
                .getCompetitionMatches(competitionId, matchday)
            when(matches) {
                is Result.Success -> handleMatchesSuccess(matches.data)
                is Result.Error -> handleMatchesError(matches)
            }
        }
        return competitionMatches
    }

    private fun handleMatchesSuccess(matches: List<CompetitionMatchDomainModel>) {
        _dataLoading.value = false
        competitionMatches.value = matches
    }

    private fun handleMatchesError(error: Result.Error) {
        competitionMatches.value = emptyList()
        _errorMessage.value = error
    }
}