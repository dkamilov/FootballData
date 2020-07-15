package com.damir.android.myscore.model

import com.google.gson.annotations.SerializedName

data class TableTeam(
    @SerializedName("position")
    val position: Int,
    @SerializedName("team")
    val teamInfo: TeamWithLogo,
    @SerializedName("playedGames")
    val playedGames: Int,
    @SerializedName("won")
    val won: Int,
    @SerializedName("draw")
    val draw: Int,
    @SerializedName("lost")
    val lost: Int,
    @SerializedName("points")
    val points: Int,
    @SerializedName("goalsFor")
    val goalsFor: Int,
    @SerializedName("goalsAgainst")
    val goalsAgainst: Int,
    @SerializedName("goalDifference")
    val goadDifference: Int
)