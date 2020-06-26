package com.damir.android.myscore.ui.competitions.data.retrofit

import com.damir.android.myscore.ui.competitions.data.model.CompetitionsDataModel
import retrofit2.http.GET

interface CompetitionsService {
    @GET("competitions?plan=TIER_ONE")
    suspend fun getAllCompetitions(): CompetitionsDataModel
}