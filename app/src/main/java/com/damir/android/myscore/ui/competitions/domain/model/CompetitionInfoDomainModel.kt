package com.damir.android.myscore.ui.competitions.domain.model

import com.damir.android.myscore.model.Season

data class CompetitionInfoDomainModel(
    val id: Int,
    val name: String,
    val area: String,
    val startDate: String,
    val endDate: String,
    val currentMatchday: Int,
    val seasons: List<Season>
)