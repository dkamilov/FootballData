package com.damir.android.myscore.di

import com.damir.android.myscore.ui.competitions.data.repository.CompetitionMatchesRepositoryImpl
import com.damir.android.myscore.ui.competitions.data.repository.CompetitionsRepositoryImpl
import com.damir.android.myscore.ui.competitions.data.retrofit.CompetitionMatchesService
import com.damir.android.myscore.ui.competitions.data.retrofit.CompetitionsService
import com.damir.android.myscore.ui.competitions.domain.repository.CompetitionMatchesRepository
import com.damir.android.myscore.ui.competitions.domain.repository.CompetitionsRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val domainModule = module {
    single<CompetitionsRepository> {
        competitionsRepository(get())
    }

    single<CompetitionMatchesRepository> {
        competitionMatchesRepository(get())
    }
}

private fun competitionsRepository(competitionsService: CompetitionsService) =
    CompetitionsRepositoryImpl(competitionsService)

private fun competitionMatchesRepository(competitionMatchesService: CompetitionMatchesService) =
    CompetitionMatchesRepositoryImpl(competitionMatchesService)