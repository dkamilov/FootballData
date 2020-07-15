package com.damir.android.myscore.ui.competitions.data.model

import com.damir.android.myscore.model.Area
import com.damir.android.myscore.model.Season
import com.damir.android.myscore.ui.competitions.domain.model.CompetitionDomainModel
import com.google.gson.annotations.SerializedName

data class CompetitionDataModel(
    @SerializedName("id")
    val id: Int,

    @SerializedName("area")
    val area: Area,

    @SerializedName("name")
    val name: String,

    @SerializedName("emblemUrl")
    val emblemUrl: String?,

    @SerializedName("currentSeason")
    val season: Season
)

fun CompetitionDataModel.toDomainModel(): CompetitionDomainModel {
    return CompetitionDomainModel(
        id = this.id,
        area = this.area,
        name =  this.name,
        emblemUrl = this.emblemUrl,
        season = this.season
    )
}