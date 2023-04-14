package com.cupcake.todo.ui.fragment.home

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cupcake.todo.R
import com.cupcake.todo.databinding.ItemHomeHeaderBinding
import com.cupcake.todo.databinding.ItemNestedTeamTaskBinding
import com.cupcake.todo.databinding.ItemPersonalTaskBinding
import com.cupcake.todo.databinding.ItemTitleSctionBinding
import com.cupcake.todo.model.network.response.PersonalTask
import com.cupcake.todo.model.network.response.TeamTask
import com.cupcake.todo.ui.util.setupPieChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter

class HomeAdapter(
    private var items: List<HomeItem<Any>>,
//    private val onClickViewMore: (planType: String) -> Unit,
) : RecyclerView.Adapter<HomeAdapter.BasicViewHolder>() {

    sealed class BasicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

//
//    @SuppressLint("NotifyDataSetChanged")
//    fun setItems(items: List<HomeItem<Any>>) {
//        this.items = items
//        notifyDataSetChanged()
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasicViewHolder {
        return when (viewType) {

            ITEM_TYPE_HEADER_DETAILS -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_home_header, parent, false)
                HeaderDetailsViewHolder(view)
            }

            ITEM_TYPE_TITLE_SECTION -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_title_sction, parent, false)
                TitleSectionViewHolder(view)
            }
            ITEM_TYPE_TEAM_TASK -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_nested_team_task, parent, false)
                TeamTaskViewHolder(view)
            }

            ITEM_TYPE_PERSONAL_TASK -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_personal_task, parent, false)
                PersonalTaskViewHolder(view)
            }
            else -> throw Exception("UNKNOWN VIEW TYPE")
        }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return when (items[position].type) {
            HomeItemType.ITEM_TYPE_HEADER_DETAILS -> ITEM_TYPE_HEADER_DETAILS
            HomeItemType.ITEM_TYPE_TITLE_SECTION -> ITEM_TYPE_TITLE_SECTION
            HomeItemType.ITEM_TYPE_PERSONAL_TASK -> ITEM_TYPE_PERSONAL_TASK
            HomeItemType.ITEM_TYPE_TEAM_TASK -> ITEM_TYPE_TEAM_TASK
        }
    }

    override fun onBindViewHolder(holder: BasicViewHolder, position: Int) {
        when (holder) {
            is HeaderDetailsViewHolder -> bindDetails(holder, position)
            is TitleSectionViewHolder -> bindTitleSection(holder, position)
            is PersonalTaskViewHolder -> bindPersonalTask(holder, position)
            is TeamTaskViewHolder -> bindTeamTask(holder, position)
        }
    }


    private fun bindDetails(holder: HeaderDetailsViewHolder, position: Int) {
        val currentItem = items[position].item as Map<String, *>
        holder.binding.apply {
            textViewUserName.text = currentItem["username"].toString()

            include.textViewToDoPercent.text = "${currentItem["toDo"]} %"
            include.textViewInProgressPercent.text = "${currentItem["inProgress"]} %"
            include.textViewDonePercent.text = "${currentItem["done"]} %"
            include.pieChart.setupPieChart(include.pieChart, taskDataValue(currentItem["toDo"] as Float, currentItem["inProgress"] as Float, currentItem["done"] as Float))
        }
    }

    private fun bindTitleSection(holder: TitleSectionViewHolder, position: Int) {
        val currentItem = items[position].item as String
        holder.binding.apply {
            textViewRecentTask.text = currentItem

        }
    }

    private fun bindTeamTask(holder: TeamTaskViewHolder, position: Int) {
        val currentItem = items[position].item as List<TeamTask>
        Log.d("HomeAdapter", "bindTeamTask: $currentItem")
        val adapter = TeamTaskAdapter(currentItem)
        holder.binding.recyclerViewTeamTasks.adapter = adapter

    }

    private fun bindPersonalTask(holder: PersonalTaskViewHolder, position: Int) {
        val currentItem = items[position].item as PersonalTask
        holder.binding.apply {
            Log.d("HomeAdapter", "bindPersonalTask: $currentItem")

            textViewTaskTitle.text = currentItem.title
            textViewDescription.text = currentItem.description
            textViewDate.text = currentItem.creationTime
        }
    }

    class HeaderDetailsViewHolder(itemView: View) : BasicViewHolder(itemView) {
        val binding = ItemHomeHeaderBinding.bind(itemView)
    }

    class TitleSectionViewHolder(itemView: View) : BasicViewHolder(itemView) {
        val binding = ItemTitleSctionBinding.bind(itemView)
    }

    class PersonalTaskViewHolder(itemView: View) : BasicViewHolder(itemView) {
        val binding = ItemPersonalTaskBinding.bind(itemView)
    }

    class TeamTaskViewHolder(itemView: View) : BasicViewHolder(itemView) {
        val binding = ItemNestedTeamTaskBinding.bind(itemView)
    }




    companion object {
        private const val ITEM_TYPE_HEADER_DETAILS = 0
        private const val ITEM_TYPE_TITLE_SECTION = 1
        private const val ITEM_TYPE_TEAM_TASK = 2
        private const val ITEM_TYPE_PERSONAL_TASK = 3
    }


//    private fun taskDataValue(map: Map<String, Any>): MutableList<PieEntry> {
//        val dataValue = mutableMapOf<String, PieEntry>()
//        dataValue["toDo"] = PieEntry(todo,"ToDo")
//        dataValue["inProgress"] = PieEntry(inProgress,"InProgress")
//        dataValue["done"] = PieEntry(done,"Done")
//        return dataValue
//    }

    private fun taskDataValue(toDo: Float, inProgress: Float, done: Float): MutableList<PieEntry> {
        val dataValue = mutableListOf<PieEntry>()
        dataValue.add(PieEntry(toDo,"ToDo"))
        dataValue.add(PieEntry(inProgress,"InProgress"))
        dataValue.add(PieEntry(done,"Done"))
        return dataValue
    }
}

