package com.damir.android.myscore.model

import com.google.gson.annotations.SerializedName

data class Score(
    @SerializedName("winner")
    val winner: String?,
    @SerializedName("fullTime")
    val fullTime: FullTime
)