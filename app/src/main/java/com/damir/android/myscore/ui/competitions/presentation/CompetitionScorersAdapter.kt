package com.damir.android.myscore.ui.competitions.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.damir.android.myscore.databinding.ItemCompetitionScorerBinding
import com.damir.android.myscore.model.Scorer

class CompetitionScorersAdapter : RecyclerView.Adapter<ScorerViewHolder>() {

    private val scorers = mutableListOf<Scorer>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScorerViewHolder {
        val binding = ItemCompetitionScorerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ScorerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScorerViewHolder, position: Int) {
        val scorer = scorers[position]
        holder.bind(scorer)
    }

    override fun getItemCount(): Int = scorers.size

    fun updateScorers(scorers: List<Scorer>) {
        this.scorers.clear()
        this.scorers.addAll(scorers)
        notifyDataSetChanged()
    }

}

class ScorerViewHolder(itemView: ItemCompetitionScorerBinding)
    : RecyclerView.ViewHolder(itemView.root) {

    private val textScorerName = itemView.textScorerName
    private val textScorerTeam = itemView.textScorerTeam
    private val textScorerGoals = itemView.textScorerGoals

    fun bind(scorer: Scorer) {
        textScorerName.text = scorer.player.name
        textScorerTeam.text = scorer.team.name
        textScorerGoals.text = scorer.numberOfGoals.toString()
    }
}