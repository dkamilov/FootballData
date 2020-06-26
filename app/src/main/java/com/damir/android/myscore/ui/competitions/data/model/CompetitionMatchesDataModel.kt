package com.damir.android.myscore.ui.competitions.data.model

import com.google.gson.annotations.SerializedName

data class CompetitionMatchesDataModel(
    @SerializedName("matches")
    val matches: List<CompetitionMatchDataModel>
)