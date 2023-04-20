package com.cupcake.todo.presenter.home

import com.cupcake.todo.model.network.ApiServiceImpl
import com.cupcake.todo.model.network.response.BaseResponse
import com.cupcake.todo.model.network.response.PersonalTask
import com.cupcake.todo.model.network.response.TeamTask
import com.cupcake.todo.ui.fragment.home.IHomeView

class HomePresenter(
    private val view: IHomeView
) {

    private val service = ApiServiceImpl()


    fun getAllTasks() {
        view.showLoading()
        service.getAllPersonalTask(::onGetPersonalTaskSuccess, ::onFailure)
        service.getAllTeamTask(::onGetTeamTaskSuccess, ::onFailure)
    }

    private fun onGetPersonalTaskSuccess(response: BaseResponse<List<PersonalTask>>) {
        response.result?.let { getRecentTask(it) }?.let { view.onRecentPersonalTaskSuccess(it) }
        view.hideLoading()
    }

    private fun onGetTeamTaskSuccess(response: BaseResponse<List<TeamTask>>) {
        response.result?.let { getLatestTeamTasks(it) }?.let { view.onGetLatestTeamTaskSuccess(it) }
        response.result?.let { getTaskStatusCounts(it) }?.let { view.getTaskStatusCounts(it) }
        view.hideLoading()
    }


    private fun onFailure(throwable: Throwable, statusCode: Int?, message: String?) {
        view.onGetDataFailure(throwable.toString())
    }


    private fun getRecentTask(personalTask: List<PersonalTask>): List<PersonalTask> {
        return personalTask.filter {
            it.status == TODO || it.status == INPROGRESS
        }
    }

    private fun getLatestTeamTasks(teamTasks: List<TeamTask>): List<TeamTask> {
        return teamTasks.reversed().take(3)
    }


    private fun getTaskStatusCounts(
        personalTasks: List<TeamTask>
    ): Triple<Float, Float, Float> {
        val totalTasks = personalTasks.size

        val todoCount = (personalTasks.filter { it.status == TODO }.size) * 100.0f / totalTasks
        val inProgressCount =
            (personalTasks.filter { it.status == INPROGRESS }.size) * 100.0f / totalTasks
        val doneCount = (personalTasks.filter { it.status == DONE }.size) * 100.0f / totalTasks

        return Triple(todoCount, inProgressCount, doneCount)
    }

    private companion object {
        private const val TODO = 0
        private const val INPROGRESS = 1
        private const val DONE = 2
    }


}