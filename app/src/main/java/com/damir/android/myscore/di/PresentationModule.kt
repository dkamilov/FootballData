package com.damir.android.myscore.di

import com.damir.android.myscore.ui.competitions.domain.repository.CompetitionMatchesRepository
import com.damir.android.myscore.ui.competitions.domain.repository.CompetitionsRepository
import com.damir.android.myscore.ui.competitions.presentation.CompetitionMatchesViewModel
import com.damir.android.myscore.ui.competitions.presentation.CompetitionsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { competitionsViewModel(get()) }
    viewModel { competitionMatchesViewModel(get()) }
}

private fun competitionsViewModel(competitionsRepository: CompetitionsRepository) =
    CompetitionsViewModel(
        competitionsRepository
    )

private fun competitionMatchesViewModel(competitionMatchesRepository: CompetitionMatchesRepository) =
    CompetitionMatchesViewModel(
        competitionMatchesRepository
    )