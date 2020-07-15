package com.damir.android.myscore.ui.competitions.domain.repository

import com.damir.android.myscore.Result
import com.damir.android.myscore.ui.competitions.domain.model.CompetitionInfoDomainModel
import com.damir.android.myscore.ui.competitions.domain.model.CompetitionMatchDomainModel

interface CompetitionMatchesRepository {

    suspend fun getCompetitionMatches(competitionId: Int, matchday: Int)
            : Result<List<CompetitionMatchDomainModel>>

    suspend fun getCompetitionInfo(competitionId: Int)
            : Result<CompetitionInfoDomainModel>
}