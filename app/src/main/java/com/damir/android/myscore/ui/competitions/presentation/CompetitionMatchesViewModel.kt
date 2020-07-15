package com.damir.android.myscore.ui.competitions.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.damir.android.myscore.Result
import com.damir.android.myscore.ui.competitions.domain.model.CompetitionInfoDomainModel
import com.damir.android.myscore.ui.competitions.domain.model.CompetitionMatchDomainModel
import com.damir.android.myscore.ui.competitions.domain.repository.CompetitionMatchesRepository
import com.damir.android.myscore.utils.view.BaseViewModel
import kotlinx.coroutines.launch
import java.lang.Exception

class CompetitionMatchesViewModel(
    private val competitionMatchesRepository: CompetitionMatchesRepository
) : BaseViewModel() {

    private val competitionMatches = MutableLiveData<List<CompetitionMatchDomainModel>>()

    private val _competitionInfo = MutableLiveData<Result<CompetitionInfoDomainModel>>()
    val competitionInfo: LiveData<Result<CompetitionInfoDomainModel>> = _competitionInfo

    fun getCompetitionMatches(competitionId: Int, matchday: Int)
            : LiveData<List<CompetitionMatchDomainModel>> {
        getCompetitionInfo(competitionId)
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

    private fun getCompetitionInfo(competitionId: Int) {
        viewModelScope.launch {
            val info = competitionMatchesRepository
                .getCompetitionInfo(competitionId)
            _competitionInfo.value = info
        }
    }

    private fun handleMatchesSuccess(matches: List<CompetitionMatchDomainModel>) {
        _dataLoading.value = false
        _errorMessage.value = null
        competitionMatches.value = matches
    }

    private fun handleMatchesError(error: Result.Error) {
        competitionMatches.value = emptyList()
        _errorMessage.value = error
    }
}