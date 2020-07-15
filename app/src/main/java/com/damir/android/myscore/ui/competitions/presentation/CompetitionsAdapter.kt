package com.damir.android.myscore.ui.competitions.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.damir.android.myscore.databinding.ItemCompetitionBinding
import com.damir.android.myscore.ui.competitions.domain.model.CompetitionDomainModel
import com.damir.android.myscore.utils.Constants
import com.damir.android.myscore.utils.extensions.*

class CompetitionsAdapter(
    private val onCompetitionItemClicked: (id: Int, matchday: Int, competition: String) -> Unit
) : RecyclerView.Adapter<CompetitionViewHolder>() {

    private val competitions = mutableListOf<CompetitionDomainModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompetitionViewHolder {
        val view = ItemCompetitionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CompetitionViewHolder(
            view,
            onCompetitionItemClicked
        )
    }

    override fun onBindViewHolder(holder: CompetitionViewHolder, position: Int) {
        val competition = competitions[position]
        holder.bind(competition)
    }

    override fun getItemCount(): Int = competitions.size

    fun updateList(items: List<CompetitionDomainModel>) {
        competitions.clear()
        competitions.addAll(items)
        notifyDataSetChanged()
    }
}

class CompetitionViewHolder(
    itemView: ItemCompetitionBinding,
    private val onCompetitionItemClicked: (id: Int, matchday: Int, competition: String) -> Unit
) : RecyclerView.ViewHolder(itemView.root) {

    private val imgCountryFlag = itemView.imgCountryFlag
    private val textCountryName = itemView.textCountryName
    private val textCompetitionName = itemView.textCompetitionName

    fun bind(competition: CompetitionDomainModel) {
        textCountryName.text = competition.area.name
        textCompetitionName.text = competition.name
        setCountryFlag(competition)
        itemView.setOnClickListener {
            onCompetitionItemClicked(
                competition.id,
                competition.season.currentMatchday,
                competition.name
            )
        }
    }

    private fun setCountryFlag(competition: CompetitionDomainModel) {
        when(competition.area.name) {
            "Brazil" -> loadFlag(Constants.FLAG_BRAZIL)
            "Europe" -> loadFlag(Constants.FLAG_EUROPE)
            "World" -> loadFlag(Constants.FLAG_WORLD)
            else -> {
                competition.area.ensignUrl?.let { url ->
                    loadFlag(url)
                }
            }
        }
    }

    private fun loadFlag(url: String) {
        imgCountryFlag.loadSvg(url)
    }
}

