package com.cupcake.todo.ui.fragment.home

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.cupcake.todo.databinding.ItemTeamTaskBinding
import com.cupcake.todo.model.network.response.TeamTask
import com.cupcake.todo.ui.util.formatDate
import java.util.*

class TeamTaskAdapter(
    private val items: List<TeamTask>,
    private val onClickTeamTaskItem: (teamTask: TeamTask) -> Unit
): RecyclerView.Adapter<TeamTaskAdapter.TeamTaskViewHolder>() {

    class TeamTaskViewHolder(val binding: ItemTeamTaskBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamTaskViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val view = ItemTeamTaskBinding.inflate(inflate, parent, false)
        return TeamTaskViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: TeamTaskViewHolder, position: Int) {
        val item = items[position]
        holder.binding.apply {
            textViewTaskTitle.text = item.title
            textViewDate.text = formatDate(item.createTime)
            textViewAssignee.text = item.assignee.take(2)
            textViewDescription.text = item.description

            root.setOnClickListener {
                onClickTeamTaskItem(item)
            }
        }

    }
}