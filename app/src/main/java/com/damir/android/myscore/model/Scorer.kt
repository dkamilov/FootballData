package com.damir.android.myscore.model

import com.google.gson.annotations.SerializedName

data class Scorer (
    @SerializedName("player")
    val player: Player,
    @SerializedName("team")
    val team: Team,
    @SerializedName("numberOfGoals")
    val numberOfGoals: Byte
)