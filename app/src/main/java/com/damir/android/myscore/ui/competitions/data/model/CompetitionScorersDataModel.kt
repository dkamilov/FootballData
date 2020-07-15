package com.damir.android.myscore.ui.competitions.data.model

import com.damir.android.myscore.model.Scorer
import com.damir.android.myscore.ui.competitions.domain.model.CompetitionScorersDomainModel
import com.google.gson.annotations.SerializedName

data class CompetitionScorersDataModel(
    @SerializedName("scorers")
    val scorers: List<Scorer>
)

fun CompetitionScorersDataModel.toDomainModel(): CompetitionScorersDomainModel {
    return CompetitionScorersDomainModel(
        scorers = this.scorers
    )
}