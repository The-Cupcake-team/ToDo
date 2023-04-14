package com.cupcake.todo.presenter.home

import com.cupcake.todo.model.network.ApiServiceImpl
import com.cupcake.todo.model.network.response.BaseResponse
import com.cupcake.todo.model.network.response.PersonalTask
import com.cupcake.todo.model.network.response.TeamTask
import com.cupcake.todo.model.network.util.ApiCallback
import com.cupcake.todo.ui.fragment.home.IHomeView

class HomePresenter(
    private val view: IHomeView
) {

    private val service = ApiServiceImpl()

    fun getTasks() {
        view.showLoading()
        getAllPersonalTask()
        getAllTeamTask()
        view.hideLoading()
    }

    private fun getAllPersonalTask() {
        service.getAllPersonalTask(object : ApiCallback<BaseResponse<List<PersonalTask>>> {

            override fun onSuccess(response: BaseResponse<List<PersonalTask>>) {
                view.onRecentPersonalTaskSuccess(getRecentTask(response.result!!))
                //view.getTaskStatusCounts(getTaskStatusCounts(response.result))
            }

            override fun onFailure(throwable: Throwable, statusCode: Int?, message: String?) {
                view.onGetDataFailure(throwable.toString())
            }

        })
    }


    private fun getRecentTask(personalTask: List<PersonalTask>): List<PersonalTask> {
        return personalTask.filter {
            it.status == TODO || it.status == INPROGRESS
        }
    }


    private fun getAllTeamTask() {
        service.getAllTeamTask(object : ApiCallback<BaseResponse<List<TeamTask>>> {

            override fun onSuccess(response: BaseResponse<List<TeamTask>>) {
                view.onGetLatestTeamTaskSuccess(getLatestTeamTasks(response.result!!))
                view.getTaskStatusCounts(getTaskStatusCounts(response.result))
            }

            override fun onFailure(throwable: Throwable, statusCode: Int?, message: String?) {
                view.onGetDataFailure(throwable.toString())
            }

        })
    }

    private fun getLatestTeamTasks(teamTasks: List<TeamTask>): List<TeamTask> {
        return teamTasks.take(4)
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