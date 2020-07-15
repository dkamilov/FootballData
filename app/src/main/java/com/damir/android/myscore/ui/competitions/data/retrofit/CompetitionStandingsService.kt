package com.damir.android.myscore.ui.competitions.data.retrofit

import com.damir.android.myscore.ui.competitions.data.model.CompetitionStandingDataModel
import retrofit2.http.GET
import retrofit2.http.Path

interface CompetitionStandingsService {

    @GET("competitions/{competitionId}/standings")
    suspend fun getCompetitionStandings(
        @Path("competitionId") competitionId: Int
    ): CompetitionStandingDataModel
}