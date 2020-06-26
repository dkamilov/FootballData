package com.damir.android.myscore.model

import com.google.gson.annotations.SerializedName

data class CurrentSeason(
    @SerializedName( "id")
    val id: Int,
    @SerializedName("startDate")
    val startDate: String,
    @SerializedName("endDate")
    val endDate: String,
    @SerializedName("currentMatchday")
    val currentMatchday: Int
)