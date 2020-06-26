package com.damir.android.myscore.ui.competitions.data.repository

import android.util.Log
import com.damir.android.myscore.Result
import com.damir.android.myscore.ui.competitions.data.model.toDomainModel
import com.damir.android.myscore.ui.competitions.data.retrofit.CompetitionMatchesService
import com.damir.android.myscore.ui.competitions.domain.model.CompetitionMatchDomainModel
import com.damir.android.myscore.ui.competitions.domain.repository.CompetitionMatchesRepository
import retrofit2.HttpException
import java.lang.Exception

class CompetitionMatchesRepositoryImpl(
    private val competitionMatchesService: CompetitionMatchesService
) : CompetitionMatchesRepository {

    override suspend fun getCompetitionMatches(competitionId: Int, matchday: Int)
            : Result<List<CompetitionMatchDomainModel>> {
        return try {
            val matches = competitionMatchesService
                .getCompetitionMatches(competitionId, matchday)
                .matches
                .map {
                    it.toDomainModel()
                }
            Result.Success(matches)
        } catch (e: HttpException) {
            Log.e("MyScoreApp", "getAllCompetitions: ${e.message}")
            Result.Error.HttpError(e)
        } catch (e: Exception) {
            Log.e("MyScoreApp", "getAllCompetitions: ${e.message}")
            Result.Error.NetworkError(e)
        }
    }
}