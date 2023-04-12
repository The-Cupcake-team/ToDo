package com.cupcake.todo.ui.fragment.home

import com.cupcake.todo.model.network.response.TeamTask
import com.cupcake.todo.ui.fragment.register.IView

interface IHomeView : IView {
    fun showLoading()
    fun hideLoading()
    fun onGetDataSuccess()
    fun onGetLatestTeamTaskSuccess(teamTasks: List<TeamTask>)
    fun onToDoTeamTasksSuccess(teamTasks: List<TeamTask>)
    fun onInProgressTeamTasksSuccess(teamTasks: List<TeamTask>)
    fun onDoneTeamTasksSuccess(teamTasks: List<TeamTask>)

    fun onGetDataFailure(error: String)
}