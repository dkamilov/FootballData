package com.damir.android.myscore.ui.competitions.data.model

import com.damir.android.myscore.model.Standing
import com.damir.android.myscore.ui.competitions.domain.model.CompetitionStandingDomainModel
import com.google.gson.annotations.SerializedName

data class CompetitionStandingDataModel(
    @SerializedName("standings")
    val standings: List<Standing>
)

fun CompetitionStandingDataModel.toDomainModel()
        : CompetitionStandingDomainModel {
    return CompetitionStandingDomainModel(
        standings = this.standings
    )
}