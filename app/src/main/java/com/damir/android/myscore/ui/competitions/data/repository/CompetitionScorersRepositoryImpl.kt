package com.damir.android.myscore.ui.competitions.data.repository

import com.damir.android.myscore.Result
import com.damir.android.myscore.safeResult
import com.damir.android.myscore.ui.competitions.data.model.toDomainModel
import com.damir.android.myscore.ui.competitions.data.retrofit.CompetitionScorersService
import com.damir.android.myscore.ui.competitions.domain.model.CompetitionScorersDomainModel
import com.damir.android.myscore.ui.competitions.domain.repository.CompetitionScorersRepository

class CompetitionScorersRepositoryImpl(
    private val competitionScorersService: CompetitionScorersService
) : CompetitionScorersRepository {

    override suspend fun getCompetitionScorers(competitionId: Int)
            : Result<CompetitionScorersDomainModel> {
        return safeResult {
            competitionScorersService
                .getCompetitionScorers(competitionId)
                .toDomainModel()
        }
    }
}