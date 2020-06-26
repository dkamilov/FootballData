package com.damir.android.myscore.ui.competitions.domain.repository

import com.damir.android.myscore.Result
import com.damir.android.myscore.ui.competitions.domain.model.CompetitionDomainModel

interface CompetitionsRepository {
    suspend fun getAllCompetitions(): Result<List<CompetitionDomainModel>>
}