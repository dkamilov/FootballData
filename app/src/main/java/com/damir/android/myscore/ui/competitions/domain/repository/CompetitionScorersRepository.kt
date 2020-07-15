package com.damir.android.myscore.ui.competitions.domain.repository

import com.damir.android.myscore.Result
import com.damir.android.myscore.ui.competitions.domain.model.CompetitionScorersDomainModel

interface CompetitionScorersRepository {
    suspend fun getCompetitionScorers(competitionId: Int)
            : Result<CompetitionScorersDomainModel>
}