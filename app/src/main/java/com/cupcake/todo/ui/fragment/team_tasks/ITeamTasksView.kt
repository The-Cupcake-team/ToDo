package com.cupcake.todo.ui.fragment.team_tasks

interface ITeamTasksView {

    fun showLoading()

    fun hideLoading()

    fun onTeamTasksSuccess(teamTasks: List<TeamTaskData>)

    fun onTeamTasksFailure(error: String)
}