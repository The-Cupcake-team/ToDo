package com.cupcake.todo.ui.fragment.details.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.cupcake.todo.R
import com.cupcake.todo.databinding.ItemProfileImageBinding

class DetailsAdapter(private val list: List<String>, var assigne: String) :
    RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_profile_image, parent, false)
        return DetailsViewHolder(view)
    }


    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        val currentTeamPerson = list[position]
        val assigneeChars = currentTeamPerson.take(2).uppercase()
        holder.binding.apply {
            name.text = currentTeamPerson

            if (assigne == currentTeamPerson) {
                avatarMemberSelected.visibility = View.VISIBLE
            } else {
                nameChars.text = assigneeChars
            }
        }
    }

    override fun getItemCount() = list.size

    class DetailsViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        val binding = ItemProfileImageBinding.bind(viewItem)
    }

}