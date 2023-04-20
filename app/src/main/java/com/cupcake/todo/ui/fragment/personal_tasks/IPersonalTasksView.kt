package com.cupcake.todo.ui.fragment.personal_tasks

import com.cupcake.todo.data.network.response.PersonalTask

interface IPersonalTasksView {

    fun showLoading()

    fun hideLoading()

    fun bindPersonalTasksToAdapter(personalTasks: List<PersonalTask>)

    fun handlePersonalTasksFetchError(error: String)
}