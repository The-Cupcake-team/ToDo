package com.cupcake.todo.ui.fragment.home

import com.cupcake.todo.model.network.response.TeamTask
import com.cupcake.todo.model.network.response.PersonalTask
import com.cupcake.todo.ui.fragment.register.IView

interface IHomeView : IView {
    fun showLoading()
    fun hideLoading()

    fun onGetLatestTeamTaskSuccess(teamTasks: List<TeamTask>)
    fun onToDoTeamTasksSuccess(teamTasks: List<TeamTask>)
    fun onInProgressTeamTasksSuccess(teamTasks: List<TeamTask>)
    fun onDoneTeamTasksSuccess(teamTasks: List<TeamTask>)

    fun onRecentPersonalTaskSuccess(personalTasks: List<PersonalTask>)
    fun onToDoPersonalTaskSuccess(personalTasks: List<PersonalTask>)
    fun onInProgressPersonalTaskSuccess(personalTasks: List<PersonalTask>)
    fun onDonePersonalTaskSuccess(personalTasks: List<PersonalTask>)


    fun onGetDataFailure(error: String)
}