package com.cupcake.todo.ui.fragment.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cupcake.todo.databinding.ItemTeamTaskBinding
import com.cupcake.todo.model.network.response.TeamTask

class TeamTaskAdapter(private val items: List<TeamTask>): RecyclerView.Adapter<TeamTaskAdapter.TeamTaskViewHolder>() {

    class TeamTaskViewHolder(val binding: ItemTeamTaskBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamTaskViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val view = ItemTeamTaskBinding.inflate(inflate, parent, false)
        return TeamTaskViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: TeamTaskViewHolder, position: Int) {
        val item = items[position]
        holder.binding.apply {
            textViewTaskTitle.text = item.title
            textViewRemainingPlans.text = item.description
            textViewAssignee.text = item.assignee.take(2)
            textViewGoTPlans.text = item.creationTime
        }

    }
}