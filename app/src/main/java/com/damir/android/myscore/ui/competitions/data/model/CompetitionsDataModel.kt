package com.damir.android.myscore.ui.competitions.data.model

import com.google.gson.annotations.SerializedName


data class CompetitionsDataModel(
    @SerializedName("competitions")
    val competitionsList: List<CompetitionDataModel>
)