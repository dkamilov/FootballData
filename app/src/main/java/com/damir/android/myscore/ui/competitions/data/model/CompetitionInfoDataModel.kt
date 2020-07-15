package com.damir.android.myscore.ui.competitions.data.model

import com.damir.android.myscore.model.Area
import com.damir.android.myscore.model.Season
import com.damir.android.myscore.ui.competitions.domain.model.CompetitionInfoDomainModel
import com.google.gson.annotations.SerializedName

data class CompetitionInfoDataModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("area")
    val area: Area,
    @SerializedName("name")
    val name: String,
    @SerializedName("currentSeason")
    val season: Season,
    @SerializedName("seasons")
    val seasons: List<Season>
)

fun CompetitionInfoDataModel.toDomainModel(): CompetitionInfoDomainModel {
    return CompetitionInfoDomainModel(
        id = this.id,
        name = this.name,
        area = this.area.name,
        startDate = this.season.startDate,
        endDate = this.season.endDate,
        currentMatchday = this.season.currentMatchday,
        seasons = this.seasons
    )
}