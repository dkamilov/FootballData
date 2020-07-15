package com.damir.android.myscore.ui.competitions.data.repository

import com.damir.android.myscore.Result
import com.damir.android.myscore.safeResult
import com.damir.android.myscore.ui.competitions.data.model.toDomainModel
import com.damir.android.myscore.ui.competitions.data.retrofit.CompetitionMatchesService
import com.damir.android.myscore.ui.competitions.domain.model.CompetitionInfoDomainModel
import com.damir.android.myscore.ui.competitions.domain.model.CompetitionMatchDomainModel
import com.damir.android.myscore.ui.competitions.domain.repository.CompetitionMatchesRepository

class CompetitionMatchesRepositoryImpl(
    private val competitionMatchesService: CompetitionMatchesService
) : CompetitionMatchesRepository {

    override suspend fun getCompetitionMatches(competitionId: Int, matchday: Int)
            : Result<List<CompetitionMatchDomainModel>> {
        return safeResult {
            competitionMatchesService
                .getCompetitionMatches(competitionId, matchday)
                .matches
                .map {
                    it.toDomainModel()
                }
        }
    }

    override suspend fun getCompetitionInfo(competitionId: Int)
            : Result<CompetitionInfoDomainModel> {
        return safeResult {
            competitionMatchesService
                .getCompetitionInfo(competitionId)
                .toDomainModel()
        }
    }

}