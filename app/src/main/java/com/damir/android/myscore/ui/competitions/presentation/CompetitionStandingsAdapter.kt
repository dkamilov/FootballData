package com.damir.android.myscore.ui.competitions.presentation

import android.provider.Settings.Global.getString
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.damir.android.myscore.R
import com.damir.android.myscore.databinding.ItemCompetitionStandingTeamBinding
import com.damir.android.myscore.model.TableTeam
import com.damir.android.myscore.utils.extensions.loadSvg
import kotlinx.android.synthetic.main.item_competition_standing_team.view.*

class CompetitionStandingsAdapter(

) : RecyclerView.Adapter<StandingTeamViewHolder>() {

    private val standingTeams = mutableListOf<TableTeam>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StandingTeamViewHolder {
        val view = ItemCompetitionStandingTeamBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return StandingTeamViewHolder(view)
    }

    override fun onBindViewHolder(holder: StandingTeamViewHolder, position: Int) {
        val team = standingTeams[position]
        holder.bind(team)
    }

    override fun getItemCount(): Int = standingTeams.size

    fun updateTeams(teams: List<TableTeam>) {
        standingTeams.clear()
        standingTeams.addAll(teams)
        notifyDataSetChanged()
    }

}

class StandingTeamViewHolder(itemView: ItemCompetitionStandingTeamBinding)
    : RecyclerView.ViewHolder(itemView.root) {

    private val textTeamPosition = itemView.textTeamPosition
    private val imgTeamLogo = itemView.imgTeamLogo
    private val textTeamName = itemView.textTeamName
    private val textPlayedGames = itemView.textPlayedGames
    private val textGoals = itemView.textGoals
    private val textGoalsDifference = itemView.textGoalsDifference
    private val textPoints = itemView.textPoints

    fun bind(team: TableTeam) {
        textTeamPosition.text = team.position.toString()
        textTeamName.text = team.teamInfo.name
        textPlayedGames.text = team.playedGames.toString()
        textGoals.text = itemView.context.getString(
            R.string.formatted_total_goals,
            team.goalsFor,
            team.goalsAgainst
        )
        textGoalsDifference.text = team.goadDifference.toString()
        textPoints.text = team.points.toString()
        team.teamInfo.crestUrl?.let { url ->
            imgTeamLogo.loadSvg(url)
        }
    }
}