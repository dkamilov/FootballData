package com.damir.android.myscore.ui.competitions.data.model

import com.damir.android.myscore.model.Score
import com.damir.android.myscore.model.Team
import com.damir.android.myscore.ui.competitions.domain.model.CompetitionMatchDomainModel
import com.google.gson.annotations.SerializedName

data class CompetitionMatchDataModel(
    @SerializedName("id")
    val id: Int,

    @SerializedName("utcDate")
    val utcDate: String,

    @SerializedName("status")
    val status: String,

    @SerializedName("score")
    val score: Score,

    @SerializedName("homeTeam")
    val homeTeam: Team,

    @SerializedName("awayTeam")
    val awayTeam: Team
)

fun CompetitionMatchDataModel.toDomainModel(): CompetitionMatchDomainModel {
    return CompetitionMatchDomainModel(
        id = this.id,
        utcDate = this.utcDate,
        status = this.status,
        winner = this.score.winner,
        homeTeamScore = this.score.fullTime.homeTeam,
        awayTeamScore = this.score.fullTime.awayTeam,
        homeTeamId = this.homeTeam.id,
        awayTeamId = this.awayTeam.id,
        homeTeamName = this.homeTeam.name,
        awayTeamName = this.awayTeam.name
    )
}
