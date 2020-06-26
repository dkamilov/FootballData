package com.damir.android.myscore.ui.competitions.domain.model

data class CompetitionMatchDomainModel(
    val id: Int,
    val utcDate: String,
    val status: String,
    val winner: String?,
    val homeTeamScore: Byte?,
    val awayTeamScore: Byte?,
    val homeTeamId: Int,
    val awayTeamId: Int,
    val homeTeamName: String,
    val awayTeamName: String
)