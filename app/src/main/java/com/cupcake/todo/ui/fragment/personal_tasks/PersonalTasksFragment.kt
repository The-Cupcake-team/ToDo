package com.cupcake.todo.ui.fragment.personal_tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cupcake.todo.databinding.FragmentPersonalTasksBinding
import com.cupcake.todo.data.network.response.PersonalTask
import com.cupcake.todo.ui.base.BaseFragment
import com.cupcake.todo.ui.fragment.details.DetailsFragment
import com.cupcake.todo.ui.fragment.personal_tasks.adapter.PersonalTasksAdapter
import com.cupcake.todo.ui.fragment.personal_tasks.adapter.PersonalTasksInteraction
import com.cupcake.todo.ui.util.TaskStatus
import com.cupcake.todo.ui.util.extension.hidden
import com.cupcake.todo.ui.util.extension.navigateTo
import com.cupcake.todo.ui.util.extension.show
import com.cupcake.todo.ui.util.extension.stateTasks

class PersonalTasksFragment : BaseFragment<FragmentPersonalTasksBinding>(), IPersonalTasksView,
    PersonalTasksInteraction {
    override val LOG_TAG: String = this::class.java.name
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPersonalTasksBinding
        get() = FragmentPersonalTasksBinding::inflate

    private var personalTasks = listOf<PersonalTask>()
    private val presenter by lazy { PersonalTasksPresenter(this) }
    private val adapter by lazy { PersonalTasksAdapter(personalTasks, this) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.personalTasks()
        filterTasksUsingStates()
    }

    private fun filterTasksUsingStates() {
        binding.chipsStates.stateTasks(
            { status ->
                filterTasksByStatus(status)
            }, { tasks ->
                adapter.updateTasks(tasks)
            }
        )
    }

    private fun filterTasksByStatus(status: Int): List<PersonalTask> {
        if (status == TaskStatus.All.state) {
            return personalTasks
        }
        return personalTasks.filter { it.status == status }
    }

    override fun showLoading() {
        binding.loading.root.show()
    }

    override fun hideLoading() {
        binding.loading.root.hidden()
    }

    override fun bindPersonalTasksToAdapter(personalTasks: List<PersonalTask>) {
        displayPersonalTasks(personalTasks)
    }

    private fun displayPersonalTasks(personalTasks: List<PersonalTask>) {
        activity?.runOnUiThread {
            this.personalTasks = personalTasks
            adapter.updateTasks(personalTasks)
            binding.recyclerViewPersonalTasks.adapter = adapter
        }
    }

    override fun handlePersonalTasksFetchError(error: String) {
        // show snackbar
    }

    override fun onClickPersonalTask(personalTask: PersonalTask) {
        navigateTo(DetailsFragment.newInstance(personalTask = personalTask))
    }


}