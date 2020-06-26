package com.damir.android.myscore.ui.competitions.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.damir.android.myscore.ui.competitions.domain.repository.CompetitionsRepository
import com.damir.android.myscore.Result
import com.damir.android.myscore.ui.competitions.domain.model.CompetitionDomainModel
import com.damir.android.myscore.utils.view.BaseViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class CompetitionsViewModel(
    private val competitionsRepository: CompetitionsRepository
) : BaseViewModel() {

    private val allCompetitions = MutableLiveData<List<CompetitionDomainModel>>()

    fun getAllCompetitions(): LiveData<List<CompetitionDomainModel>> {
        viewModelScope.launch {
            _dataLoading.value = true
            when(val competitions = competitionsRepository.getAllCompetitions()) {
                is Result.Success -> handleSuccessState(competitions.data)
                is Result.Error -> handleErrorState(competitions)
            }
        }
        return allCompetitions
    }

    private fun handleSuccessState(competitions: List<CompetitionDomainModel>) {
        _dataLoading.value = false
        allCompetitions.value = competitions
    }

    private fun handleErrorState(error: Result.Error) {
        allCompetitions.value = emptyList()
    }
}