package com.cupcake.todo.ui.fragment.team_tasks

import com.cupcake.todo.presenter.model.TeamTask

interface ITeamTasksView {

    fun showInternetErrorDialog()

    fun showLoading()

    fun hideLoading()

    fun onTeamTasksSuccess(teamTasks: List<TeamTask>)

    fun onTeamTasksFailure(error: String)
}