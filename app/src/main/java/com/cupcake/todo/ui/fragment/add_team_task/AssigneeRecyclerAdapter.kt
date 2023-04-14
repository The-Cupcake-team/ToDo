package com.cupcake.todo.ui.fragment.add_team_task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cupcake.todo.R
import com.cupcake.todo.databinding.ItemProfileImageBinding

class AssigneeRecyclerAdapter(
    private val listener: IAssigneeClickListener,
    private val assignees : List<String>
): RecyclerView.Adapter<AssigneeRecyclerAdapter.ViewHolder>() {

    private var selectedPosition = -1
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_profile_image
            , parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val assignee = assignees[position]
        val assigneeChars = assignee.take(2).uppercase()
            holder.binding.apply {
                name.text = assignee
                nameChars.text = assigneeChars

                visibilityAvatarMemberSelected(holder.binding, position)
                root.setOnClickListener {
                    if (selectedPosition != -1) {
                        notifyItemChanged(selectedPosition)
                    }
                    selectedPosition = position
                    avatarMemberSelected.visibility = View.VISIBLE
                    listener.onAssigneeItemClicked(assignee)
                }
            }
    }
    private fun visibilityAvatarMemberSelected(
        binding: ItemProfileImageBinding,
        currentPosition: Int
    ){
        if (selectedPosition == currentPosition){
            binding.avatarMemberSelected.visibility = View.VISIBLE
        }else{
            binding.avatarMemberSelected.visibility = View.GONE
        }
    }

    override fun getItemCount() = assignees.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemProfileImageBinding.bind(itemView)
    }

    interface IAssigneeClickListener {
        fun onAssigneeItemClicked(assignee: String)
    }
}