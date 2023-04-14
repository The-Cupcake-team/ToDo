package com.cupcake.todo.ui.fragment.team_tasks.adapter

import androidx.recyclerview.widget.DiffUtil
import com.cupcake.todo.presenter.model.TeamTaskData

class TasksDiffUtil(private val oldTasks: List<TeamTaskData>, private val newTasks: List<TeamTaskData>) :
    DiffUtil.Callback()
{
    override fun getOldListSize() = oldTasks.size

    override fun getNewListSize() = newTasks.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldTasks[oldItemPosition].id == newTasks[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldTasks[oldItemPosition] == newTasks[newItemPosition]
}