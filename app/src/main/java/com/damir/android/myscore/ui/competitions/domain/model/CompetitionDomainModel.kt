package com.damir.android.myscore.ui.competitions.domain.model

import com.damir.android.myscore.model.Area
import com.damir.android.myscore.model.CurrentSeason

data class CompetitionDomainModel(
    val id: Int,
    val area: Area,
    val name: String,
    val emblemUrl: String?,
    val currentSeason: CurrentSeason
)