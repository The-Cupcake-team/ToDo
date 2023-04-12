package com.cupcake.todo.ui.fragment.team_tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cupcake.todo.R
import com.cupcake.todo.databinding.ItemTodoTeamTaskBinding
import com.cupcake.todo.model.network.response.TeamTasksResponse

class TeamTasksAdapter(
    private val teamTasks: List<TeamTasksResponse>,
    private val listener: TeamTasksInteractionListener
) :
    RecyclerView.Adapter<TeamTasksAdapter.TeamTasksViewHolder>() {

    val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> ItemTodoTeamTaskBinding = ItemTodoTeamTaskBinding::inflate

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamTasksViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo_team_task, parent,false)
        return TeamTasksViewHolder(view)
    }

    override fun getItemCount() = teamTasks.size

    override fun onBindViewHolder(holder: TeamTasksViewHolder, position: Int) {
        val currentTeamTask = teamTasks[position]
        holder.binding.apply {
            textViewTaskTitle.text = currentTeamTask.title
            textViewRemainingPlans.text = currentTeamTask.description
            textViewTaskCreationTime.text = currentTeamTask.creationTime
            textViewAssignee.text = currentTeamTask.assignee.take(2)
            root.setOnClickListener { listener.onClickTeamTask(currentTeamTask.id)}
        }
    }

    class TeamTasksViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        val binding = ItemTodoTeamTaskBinding.bind(viewItem)
    }

}