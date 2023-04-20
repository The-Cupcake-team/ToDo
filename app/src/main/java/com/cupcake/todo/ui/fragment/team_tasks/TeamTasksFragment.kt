package com.cupcake.todo.ui.fragment.team_tasks

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cupcake.todo.R
import com.cupcake.todo.databinding.FragmentTeamTasksBinding
import com.cupcake.todo.databinding.ItemDialogueNoInternetBinding
import com.cupcake.todo.presenter.teamtasks.TeamTasksPresenter
import com.cupcake.todo.ui.base.BaseFragment
import com.cupcake.todo.ui.fragment.details.DetailsFragment
import com.cupcake.todo.ui.fragment.personal_tasks.model.TeamTask
import com.cupcake.todo.ui.fragment.team_tasks.adapter.TeamTasksAdapter
import com.cupcake.todo.ui.fragment.team_tasks.adapter.TeamTasksInteractionListener

class TeamTasksFragment : BaseFragment<FragmentTeamTasksBinding>(), ITeamTasksView,
    TeamTasksInteractionListener {
    override val LOG_TAG: String = this::class.java.name
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> FragmentTeamTasksBinding =
        FragmentTeamTasksBinding::inflate
    private var presenter = TeamTasksPresenter(this)
    private var teamTasks = listOf<TeamTask>()
    private var adapter = TeamTasksAdapter(teamTasks, this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.teamTasks()
        filterTasksUsingStates()
    }


    private fun filterTasksUsingStates() {
        val binding = binding.chipStates
        val chips = listOf(
            binding.chipToDo, binding.chipInProgress,
            binding.chipDone, binding.chipAll
        )
        var filteredTasks: List<TeamTask>
        chips.forEachIndexed { index, chip ->
            chip.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    val status = when (index) {
                        TaskStatus.ToDo.state -> TaskStatus.ToDo
                        TaskStatus.InProgress.state -> TaskStatus.InProgress
                        TaskStatus.Done.state -> TaskStatus.Done
                        TaskStatus.All.state -> TaskStatus.All
                        else -> null
                    }
                    status?.let { taskStatus ->
                        filteredTasks = filterTasksByStatus(taskStatus.state)
                        adapter.updateTasks(filteredTasks)
                        chips.forEach { it.isChecked = false; it.isClickable = true }
                        chip.isChecked = true
                        chip.isClickable = false
                    }
                }
            }
        }
    }


    private fun filterTasksByStatus(status: Int): List<TeamTask> {
        if (status == 3) {
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

    private fun navigateToFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, fragment)
            addToBackStack(fragment.javaClass.simpleName)
            commit()
        }
    }

    override fun onClickTeamTask(teamTask: TeamTask) {
        navigateToFragment(DetailsFragment.newInstance(teamTask))
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


    sealed class TaskStatus(val state: Int) {
        object ToDo : TaskStatus(0)
        object InProgress : TaskStatus(1)
        object Done : TaskStatus(2)
        object All : TaskStatus(3)
    }

}

