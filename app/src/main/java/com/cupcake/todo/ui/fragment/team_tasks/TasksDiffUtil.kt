package com.cupcake.todo.ui.fragment.team_tasks

import androidx.recyclerview.widget.DiffUtil
import com.cupcake.todo.model.network.response.TeamTasksResponse

class TasksDiffUtil(private val oldTasks: List<TeamTasksResponse>, private val newTasks: List<TeamTasksResponse>) :
    DiffUtil.Callback()
{
    override fun getOldListSize() = oldTasks.size

    override fun getNewListSize() = newTasks.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldTasks[oldItemPosition].id == newTasks[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldTasks[oldItemPosition] == newTasks[newItemPosition]
}