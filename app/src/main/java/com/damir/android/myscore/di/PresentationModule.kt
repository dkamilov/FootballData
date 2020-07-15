package com.damir.android.myscore.di

import com.damir.android.myscore.ui.competitions.domain.repository.CompetitionMatchesRepository
import com.damir.android.myscore.ui.competitions.domain.repository.CompetitionScorersRepository
import com.damir.android.myscore.ui.competitions.domain.repository.CompetitionStandingsRepository
import com.damir.android.myscore.ui.competitions.domain.repository.CompetitionsRepository
import com.damir.android.myscore.ui.competitions.presentation.CompetitionMatchesViewModel
import com.damir.android.myscore.ui.competitions.presentation.CompetitionScorersViewModel
import com.damir.android.myscore.ui.competitions.presentation.CompetitionStandingsViewModel
import com.damir.android.myscore.ui.competitions.presentation.CompetitionsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { competitionsViewModel(get()) }
    viewModel { competitionMatchesViewModel(get()) }
    viewModel { competitionStatisticsViewModel(get()) }
    viewModel { competitionScorersViewModel(get()) }
}

private fun competitionsViewModel(competitionsRepository: CompetitionsRepository) =
    CompetitionsViewModel(
        competitionsRepository
    )

private fun competitionMatchesViewModel(competitionMatchesRepository: CompetitionMatchesRepository) =
    CompetitionMatchesViewModel(
        competitionMatchesRepository
    )

private fun competitionStatisticsViewModel(competitionStandingsRepository: CompetitionStandingsRepository) =
    CompetitionStandingsViewModel(
        competitionStandingsRepository
    )

private fun competitionScorersViewModel(competitionScorersRepository: CompetitionScorersRepository) =
    CompetitionScorersViewModel(
        competitionScorersRepository
    )