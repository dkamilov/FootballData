package com.damir.android.myscore.model

import com.google.gson.annotations.SerializedName

data class FullTime(
    @SerializedName("homeTeam")
    val homeTeam: Byte?,
    @SerializedName("awayTeam")
    val awayTeam: Byte?
)