package com.cupcake.todo.ui.fragment.team_tasks

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cupcake.todo.R
import com.cupcake.todo.databinding.FragmentTeamTasksBinding
import com.cupcake.todo.presenter.model.TeamTaskData
import com.cupcake.todo.presenter.teamtasks.TeamTasksPresenter
import com.cupcake.todo.ui.base.BaseFragment
import com.cupcake.todo.ui.fragment.details.DetailsFragment
import com.cupcake.todo.ui.fragment.team_tasks.adapter.TeamTasksAdapter
import com.cupcake.todo.ui.fragment.team_tasks.adapter.TeamTasksInteractionListener

class TeamTasksFragment : BaseFragment<FragmentTeamTasksBinding>(), ITeamTasksView,
    TeamTasksInteractionListener {
    override val LOG_TAG: String = this::class.java.name
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> FragmentTeamTasksBinding =
        FragmentTeamTasksBinding::inflate
    private var presenter = TeamTasksPresenter(this)
    private var teamTasks = listOf<TeamTaskData>()
    private var adapter = TeamTasksAdapter(teamTasks, this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.teamTasks()
        filterTasksUsingStates()
    }


    private fun filterTasksUsingStates() {
        val chips = listOf(
            binding.chipStates.chipToDo, binding.chipStates.chipInProgress,
            binding.chipStates.chipDone, binding.chipStates.chipAll
        )
        var filteredTasks: List<TeamTaskData>

        chips.forEachIndexed { index, chip ->
            chip.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    filteredTasks = when (index) {
                        0 -> filterTasksByStatus(0)
                        1 -> filterTasksByStatus(1)
                        2 -> filterTasksByStatus(2)
                        else -> teamTasks
                    }
                    adapter.updateTasks(filteredTasks)
                    chips.forEach { it.isChecked = false }
                    chip.isChecked = true
                }
            }
        }
    }


    private fun filterTasksByStatus(status: Int): List<TeamTaskData> {
        return teamTasks.filter { it.status == status }
    }

    private fun displayTeamTasks(teamTasks: List<TeamTaskData>) {
        activity?.runOnUiThread {
            this.teamTasks = teamTasks
            adapter = TeamTasksAdapter(teamTasks, this)
            binding.recyclerViewTeamTasks.adapter = adapter
            Log.i("TeamTasksFragment", teamTasks.toString())
        }
    }

    private fun navigateToFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction().apply {
            add(R.id.fragmentContainer, fragment)
            addToBackStack(fragment.javaClass.simpleName)
            commit()
        }
    }

    override fun onClickTeamTask(id: String) {
        navigateToFragment(DetailsFragment())
    }
    override fun showLoading() {
        Log.v(LOG_TAG, "showLoading")
    }
    override fun hideLoading() {
        Log.v(LOG_TAG, "showLoading")    }
    override fun onTeamTasksSuccess(teamTasks: List<TeamTaskData>) {
        Log.v(LOG_TAG, "TeamTasksSuccess")
        displayTeamTasks(teamTasks)
    }

    override fun onTeamTasksFailure(error: String) {
        Log.v(LOG_TAG, "onTeamTasksFailure $error")
    }
}

