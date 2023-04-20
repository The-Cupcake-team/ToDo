package com.cupcake.todo.ui.fragment.personal_tasks.adapter

import androidx.recyclerview.widget.DiffUtil
import com.cupcake.todo.data.network.response.PersonalTask

class PersonalDiffUtil(
    private val oldTasks: List<PersonalTask>,
    private val newTasks: List<PersonalTask>
) :
    DiffUtil.Callback() {
    override fun getOldListSize() = oldTasks.size

    override fun getNewListSize() = newTasks.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldTasks[oldItemPosition].id == newTasks[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldTasks[oldItemPosition] == newTasks[newItemPosition]
}

