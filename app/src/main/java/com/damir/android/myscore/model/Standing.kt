package com.damir.android.myscore.model

import com.google.gson.annotations.SerializedName

data class Standing(
    @SerializedName("type")
    val type: String,
    @SerializedName("table")
    val table: List<TableTeam>
)