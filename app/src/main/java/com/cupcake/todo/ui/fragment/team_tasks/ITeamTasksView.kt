package com.cupcake.todo.ui.fragment.team_tasks

import com.cupcake.todo.presenter.model.TeamTaskData

interface ITeamTasksView {

    fun showInternetErrorDialog()

    fun showLoading()

    fun hideLoading()

    fun onTeamTasksSuccess(teamTasks: List<TeamTaskData>)

    fun onTeamTasksFailure(error: String)
}