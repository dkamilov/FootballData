package com.damir.android.myscore.ui.competitions.data.repository

import com.damir.android.myscore.Result
import com.damir.android.myscore.safeResult
import com.damir.android.myscore.ui.competitions.data.model.toDomainModel
import com.damir.android.myscore.ui.competitions.data.retrofit.CompetitionsService
import com.damir.android.myscore.ui.competitions.domain.model.CompetitionDomainModel
import com.damir.android.myscore.ui.competitions.domain.repository.CompetitionsRepository

class CompetitionsRepositoryImpl(
    private val competitionsService: CompetitionsService
) : CompetitionsRepository {

    override suspend fun getAllCompetitions(): Result<List<CompetitionDomainModel>> {
        return safeResult {
            competitionsService
                .getAllCompetitions()
                .competitionsList
                .map {
                    it.toDomainModel()
                }
        }
    }
}