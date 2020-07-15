package com.damir.android.myscore.ui.competitions.data.retrofit

import com.damir.android.myscore.ui.competitions.data.model.CompetitionScorersDataModel
import retrofit2.http.GET
import retrofit2.http.Path

interface CompetitionScorersService {

    @GET("competitions/{competitionId}/scorers")
    suspend fun getCompetitionScorers(
        @Path("competitionId") competitionId: Int
    ): CompetitionScorersDataModel
}
