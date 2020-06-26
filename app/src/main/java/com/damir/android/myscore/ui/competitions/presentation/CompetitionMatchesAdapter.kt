package com.damir.android.myscore.ui.competitions.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.damir.android.myscore.R
import com.damir.android.myscore.databinding.ItemCompetitionMatchBinding
import com.damir.android.myscore.ui.competitions.domain.model.CompetitionMatchDomainModel
import com.damir.android.myscore.utils.Constants
import com.damir.android.myscore.utils.DateUtil
import com.damir.android.myscore.utils.extensions.setTextColorExt

class CompetitionMatchesAdapter(
    private val onMatchItemClicked: (matchId: Int, teams: String) -> Unit
) : RecyclerView.Adapter<CompetitionMatchViewHolder>() {

    private val competitionMatches = mutableListOf<CompetitionMatchDomainModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompetitionMatchViewHolder {
        val view = ItemCompetitionMatchBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CompetitionMatchViewHolder(view, onMatchItemClicked)
    }

    override fun onBindViewHolder(holder: CompetitionMatchViewHolder, position: Int) {
        val match = competitionMatches[position]
        holder.bind(match)
    }

    override fun getItemCount(): Int = competitionMatches.size

    fun updateMatches(matches: List<CompetitionMatchDomainModel>) {
        competitionMatches.clear()
        competitionMatches.addAll(matches)
        notifyDataSetChanged()
    }
}

class CompetitionMatchViewHolder(
    itemView : ItemCompetitionMatchBinding,
    private val onMatchItemClicked: (matchId: Int, teams: String) -> Unit
) : RecyclerView.ViewHolder(itemView.root) {

    private val textMatchStatus = itemView.textMatchStatus
    private val textMatchDate = itemView.textMatchDate
    private val textMatchScore = itemView.textMatchScore
    private val textHomeTeam = itemView.textHomeTeam
    private val textAwayTeam = itemView.textAwayTeam

    fun bind(match: CompetitionMatchDomainModel) {
        setMatchStatus(match.status)
        setMatchDate(match.utcDate)
        setMatchScoreOrTime(match)
        textHomeTeam.text = match.homeTeamName
        textAwayTeam.text = match.awayTeamName
        itemView.setOnClickListener {
            onMatchItemClicked(match.id, match.homeTeamName + " " + match.awayTeamName)
        }
    }

    private fun setMatchStatus(status: String) {
        textMatchStatus.text = status
        when(status) {
            Constants.STATUS_IN_PLAY -> {
                textMatchStatus.setTextColorExt(R.color.color_in_play)
            }
            Constants.STATUS_POSTPONED -> {
                textMatchStatus.setTextColorExt(R.color.color_postponed)
            }
        }
    }

    private fun setMatchDate(utcDate: String) {
        val date = DateUtil.getMatchDate(utcDate)
        textMatchDate.text = date
    }

    private fun setMatchScoreOrTime(match: CompetitionMatchDomainModel) {
        when(match.status) {
            Constants.STATUS_SCHEDULED,
            Constants.STATUS_POSTPONED -> {
                val text = DateUtil.getMatchTime(match.utcDate)
                updateMatchScoreText(text)
            }
            else -> {
                val text = itemView.context.getString(
                    R.string.formatted_match_score,
                    match.homeTeamScore.toString(),
                    match.awayTeamScore.toString())
                updateMatchScoreText(text)
            }
        }
    }

    private fun updateMatchScoreText(text: String) {
        textMatchScore.text = text
    }
}