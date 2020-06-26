package com.damir.android.myscore.ui.competitions.data.repository

import android.util.Log
import com.damir.android.myscore.Result
import com.damir.android.myscore.ui.competitions.data.model.toDomainModel
import com.damir.android.myscore.ui.competitions.data.retrofit.CompetitionsService
import com.damir.android.myscore.ui.competitions.domain.model.CompetitionDomainModel
import com.damir.android.myscore.ui.competitions.domain.repository.CompetitionsRepository
import retrofit2.HttpException

class CompetitionsRepositoryImpl(
    private val competitionsService: CompetitionsService
) : CompetitionsRepository {

    override suspend fun getAllCompetitions(): Result<List<CompetitionDomainModel>> {
        return try {
            val competitions = competitionsService
                .getAllCompetitions()
                .competitionsList
                .map {
                    it.toDomainModel()
                }
            Result.Success(competitions)
        } catch (e: HttpException) {
            Log.e("MyScoreApp", "getAllCompetitions: ${e.message}")
            Result.Error.HttpError(e)
        } catch (e: Exception) {
            Log.e("MyScoreApp", "getAllCompetitions: ${e.message}")
            Result.Error.NetworkError(e)
        }
    }
}