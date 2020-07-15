package com.damir.android.myscore.di

import com.damir.android.myscore.ui.competitions.data.repository.CompetitionMatchesRepositoryImpl
import com.damir.android.myscore.ui.competitions.data.repository.CompetitionScorersRepositoryImpl
import com.damir.android.myscore.ui.competitions.data.repository.CompetitionStandingsRepositoryImpl
import com.damir.android.myscore.ui.competitions.data.repository.CompetitionsRepositoryImpl
import com.damir.android.myscore.ui.competitions.data.retrofit.CompetitionMatchesService
import com.damir.android.myscore.ui.competitions.data.retrofit.CompetitionScorersService
import com.damir.android.myscore.ui.competitions.data.retrofit.CompetitionStandingsService
import com.damir.android.myscore.ui.competitions.data.retrofit.CompetitionsService
import com.damir.android.myscore.ui.competitions.domain.repository.CompetitionMatchesRepository
import com.damir.android.myscore.ui.competitions.domain.repository.CompetitionScorersRepository
import com.damir.android.myscore.ui.competitions.domain.repository.CompetitionStandingsRepository
import com.damir.android.myscore.ui.competitions.domain.repository.CompetitionsRepository
import org.koin.dsl.module

val domainModule = module {
    single<CompetitionsRepository> {
        competitionsRepository(get())
    }
    single<CompetitionMatchesRepository> {
        competitionMatchesRepository(get())
    }
    single<CompetitionStandingsRepository> {
        competitionStandingsRepository(get())
    }
    single<CompetitionScorersRepository> {
        competitionScorersRepository(get())
    }
}

private fun competitionsRepository(competitionsService: CompetitionsService) =
    CompetitionsRepositoryImpl(competitionsService)

private fun competitionMatchesRepository(competitionMatchesService: CompetitionMatchesService) =
    CompetitionMatchesRepositoryImpl(competitionMatchesService)

private fun competitionStandingsRepository(competitionStandingsService: CompetitionStandingsService) =
    CompetitionStandingsRepositoryImpl(competitionStandingsService)

private fun competitionScorersRepository(competitionScorersService: CompetitionScorersService) =
    CompetitionScorersRepositoryImpl(competitionScorersService)