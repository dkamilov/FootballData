package com.damir.android.myscore.ui.competitions.data.repository

import com.damir.android.myscore.Result
import com.damir.android.myscore.safeResult
import com.damir.android.myscore.ui.competitions.data.model.toDomainModel
import com.damir.android.myscore.ui.competitions.data.retrofit.CompetitionStandingsService
import com.damir.android.myscore.ui.competitions.domain.model.CompetitionStandingDomainModel
import com.damir.android.myscore.ui.competitions.domain.repository.CompetitionStandingsRepository

class CompetitionStandingsRepositoryImpl(
    private val competitionStandingsService: CompetitionStandingsService
) : CompetitionStandingsRepository {

    override suspend fun getCompetitionStandings(competitionId: Int)
            : Result<CompetitionStandingDomainModel> {
        return safeResult {
            competitionStandingsService
                .getCompetitionStandings(competitionId)
                .toDomainModel()
        }
    }
}