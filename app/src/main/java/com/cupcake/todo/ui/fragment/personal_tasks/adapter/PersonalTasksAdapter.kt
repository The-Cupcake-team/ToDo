package com.cupcake.todo.ui.fragment.personal_tasks.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cupcake.todo.R
import com.cupcake.todo.databinding.ItemPersonalTaskBinding
import com.cupcake.todo.data.network.response.PersonalTask
import com.cupcake.todo.ui.util.extension.formatDate

class PersonalTasksAdapter(
    private var personalTasks: List<PersonalTask>,
    private val listener: PersonalTasksInteraction
) : RecyclerView.Adapter<PersonalTasksAdapter.PersonalTasksViewHolder>() {

    val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> ItemPersonalTaskBinding =
        ItemPersonalTaskBinding::inflate

    class PersonalTasksViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemPersonalTaskBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonalTasksViewHolder {
        return PersonalTasksViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_personal_task, parent, false)
        )
    }

    override fun getItemCount() = personalTasks.size

    override fun onBindViewHolder(holder: PersonalTasksViewHolder, position: Int) {
        val currentItem = personalTasks[position]
        holder.binding.apply {
            textViewDate.text = formatDate(currentItem.createTime)
            textViewDescription.text = currentItem.description
            textViewTaskTitle.text = currentItem.title
            root.setOnClickListener { listener.onClickPersonalTask(currentItem) }
        }
    }

    fun updateTasks(newTask: List<PersonalTask>) {
        val diffResult = DiffUtil.calculateDiff(PersonalDiffUtil(personalTasks, newTask))
        personalTasks = newTask
        diffResult.dispatchUpdatesTo(this)
    }
}