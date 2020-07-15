package com.damir.android.myscore.ui.competitions.domain.repository

import com.damir.android.myscore.Result
import com.damir.android.myscore.ui.competitions.domain.model.CompetitionScorersDomainModel
import com.damir.android.myscore.ui.competitions.domain.model.CompetitionStandingDomainModel

interface CompetitionStandingsRepository {
    suspend fun getCompetitionStandings(competitionId: Int)
            : Result<CompetitionStandingDomainModel>
}