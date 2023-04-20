package com.cupcake.todo.ui.fragment.home

import com.cupcake.todo.model.network.response.PersonalTask
import com.cupcake.todo.ui.fragment.personal_tasks.model.TeamTask
import com.cupcake.todo.ui.fragment.register.IView

interface IHomeView : IView {
    fun showLoading()
    fun hideLoading()

    fun onGetLatestTeamTaskSuccess(teamTasks: List<TeamTask>)
    fun onRecentPersonalTaskSuccess(personalTasks: List<PersonalTask>)

    fun getTaskStatusCounts(
        personalTasks: Triple<Float, Float, Float>
    )

    fun onGetDataFailure(error: String)

}