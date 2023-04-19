package com.cupcake.todo.ui.fragment.personal_tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cupcake.todo.databinding.FragmentPersonalTasksBinding
import com.cupcake.todo.model.network.response.PersonalTask
import com.cupcake.todo.ui.base.BaseFragment
import com.cupcake.todo.ui.fragment.personal_tasks.adapter.PersonalTasksAdapter
import com.cupcake.todo.ui.fragment.personal_tasks.adapter.PersonalTasksInteraction

class PersonalTasksFragment : BaseFragment<FragmentPersonalTasksBinding>(), IPersonalTasksView,
    PersonalTasksInteraction {
    override val LOG_TAG: String = this::class.java.name
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPersonalTasksBinding
        get() = FragmentPersonalTasksBinding::inflate

    val presenter by lazy { PersonalTasksPresenter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.personalTasks()
    }

    override fun showLoading() {
        // show loading
    }

    override fun hideLoading() {
        // hide loading
    }

    override fun bindPersonalTasksToAdapter(personalTasks: List<PersonalTask>) {
        activity?.runOnUiThread {
            val adapter = PersonalTasksAdapter(personalTasks,this)
            binding.recyclerViewPersonalTask.adapter = adapter
        }
    }

    override fun handlePersonalTasksFetchError(error: String) {
        // show snackbar
    }

    override fun onClickPersonalTask(personalTask: PersonalTask) {
        // nav to details screen
    }


}