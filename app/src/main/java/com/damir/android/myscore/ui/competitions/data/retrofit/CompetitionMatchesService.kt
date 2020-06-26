package com.damir.android.myscore.ui.competitions.data.retrofit

import com.damir.android.myscore.ui.competitions.data.model.CompetitionMatchesDataModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CompetitionMatchesService {

    @GET("competitions/{competitionId}/matches")
    suspend fun getCompetitionMatches(
        @Path("competitionId") competitionId: Int,
        @Query("matchday") matchday: Int
    ): CompetitionMatchesDataModel
}