package com.cupcake.todo.ui.fragment.personal_tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cupcake.todo.databinding.FragmentPersonalTasksBinding
import com.cupcake.todo.model.network.response.PersonalTask
import com.cupcake.todo.ui.base.BaseFragment

class PersonalTasksFragment : BaseFragment<FragmentPersonalTasksBinding>(), IPersonalTasksView {
    override val LOG_TAG: String = this::class.java.name
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPersonalTasksBinding
        get() = FragmentPersonalTasksBinding::inflate

    val presenter by lazy { PersonalTasksPresenter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.personalTasks()
    }

    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun hideLoading() {
        TODO("Not yet implemented")
    }

    override fun bindPersonalTasksToAdapter(personalTasks: List<PersonalTask>) {
        TODO("Not yet implemented")
    }

    override fun handlePersonalTasksFetchError(error: String) {
        TODO("Not yet implemented")
    }


}