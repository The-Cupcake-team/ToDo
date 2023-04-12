package com.cupcake.todo.ui.fragment.team_tasks

interface ITeamTasksView {

    fun showLoading()

    fun hideLoading()

    fun onTeamTasksSuccess()

    fun onTeamTasksFailure(error: String)
}