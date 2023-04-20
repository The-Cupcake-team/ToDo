package com.cupcake.todo.ui.fragment.team_tasks

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cupcake.todo.databinding.FragmentTeamTasksBinding
import com.cupcake.todo.databinding.ItemDialogueNoInternetBinding
import com.cupcake.todo.model.network.response.TeamTask
import com.cupcake.todo.presenter.teamtasks.TeamTasksPresenter
import com.cupcake.todo.ui.base.BaseFragment
import com.cupcake.todo.ui.fragment.team_tasks.adapter.TeamTasksAdapter
import com.cupcake.todo.ui.fragment.team_tasks.adapter.TeamTasksInteractionListener
import com.cupcake.todo.ui.util.TaskStatus
import com.cupcake.todo.ui.util.stateTasks
import com.cupcake.todo.ui.util.navigateTo

class TeamTasksFragment : BaseFragment<FragmentTeamTasksBinding>(), ITeamTasksView,
    TeamTasksInteractionListener {
    override val LOG_TAG: String = this::class.java.name
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTeamTasksBinding =
        FragmentTeamTasksBinding::inflate

    private var presenter = TeamTasksPresenter(this)
    private var teamTasks = listOf<TeamTask>()
    private var adapter = TeamTasksAdapter(teamTasks, this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.teamTasks()
        filterTasksUsingStates()
    }


    private fun filterTasksUsingStates() {
        binding.chipStates.stateTasks(
            { status ->
                filterTasksByStatus(status)
            }, { tasks ->
                adapter.updateTasks(tasks)
            }
        )
    }


    private fun filterTasksByStatus(status: Int): List<TeamTask> {
        if (status == TaskStatus.All.state) {
            return teamTasks
        }
        return teamTasks.filter { it.status == status }
    }

    private fun displayTeamTasks(teamTasks: List<TeamTask>) {
        activity?.runOnUiThread {
            this.teamTasks = teamTasks
            adapter = TeamTasksAdapter(teamTasks, this)
            binding.recyclerViewTeamTasks.adapter = adapter
            Log.i("TeamTasksFragment", teamTasks.toString())
        }
    }

    override fun onClickTeamTask(teamTask: TeamTask) {
        navigateTo(DetailsFragment.newInstance(teamTask))
    }

    override fun showInternetErrorDialog() {
        activity?.runOnUiThread {
            val binding = ItemDialogueNoInternetBinding.inflate(layoutInflater)
            val builder = AlertDialog.Builder(requireContext())
                .setView(binding.root)
            val dialog = builder.create()
            binding.buttonPositive.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }
    }

    override fun showLoading() {
        Log.v(LOG_TAG, "showLoading")
    }

    override fun hideLoading() {
        Log.v(LOG_TAG, "showLoading")
    }

    override fun onTeamTasksSuccess(teamTasks: List<TeamTask>) {
        Log.v(LOG_TAG, "TeamTasksSuccess")
        displayTeamTasks(teamTasks)
    }

    override fun onTeamTasksFailure(error: String) {
        Log.v(LOG_TAG, "onTeamTasksFailure $error")
    }


}

