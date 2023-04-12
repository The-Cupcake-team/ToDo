package com.cupcake.todo.presenter.home

import android.util.Log
import com.cupcake.todo.model.network.ApiServiceImpl
import com.cupcake.todo.model.network.response.BaseResponse
import com.cupcake.todo.model.network.response.PersonalTask
import com.cupcake.todo.model.network.response.TeamTask
import com.cupcake.todo.model.network.util.ApiCallback
import com.cupcake.todo.ui.fragment.home.IHomeView

class HomePresenter(
    private val view: IHomeView
) : ApiCallback<BaseResponse<List<PersonalTask>>>{

    private val service = ApiServiceImpl()

    fun getAllPersonal() {
        view.showLoading()
        val personalTask = service.getAllPersonalTask(this)
        view.hideLoading()
    }

    override fun onSuccess(response: BaseResponse<List<PersonalTask>>) {
        Log.e("result", "personalTask: ${response.result}")
        getRecentTask(response.result!!)
        view.onGetDataSuccess()
    }

    override fun onFailure(throwable: Throwable, statusCode: Int?, message: String?) {
        view.onGetDataFailure(throwable.toString())
    }

    private fun getRecentTask(personalTask: List<PersonalTask>): List<PersonalTask> {
        return personalTask.filter {
            it.creationTime == personalTask.maxByOrNull { it.creationTime }?.creationTime
        }
    }

    fun getAllTeamTask() {
        view.showLoading()
        val teamTasks = service.getAllTeamTask(object : ApiCallback<BaseResponse<List<TeamTask>>>{
            override fun onSuccess(response: BaseResponse<List<TeamTask>>) {
                view.onGetLatestTeamTaskSuccess(getLatestTeamTasks(response.result!!))
                view.onToDoTeamTasksSuccess(getToDoTeamTasks(response.result))
                view.onInProgressTeamTasksSuccess(getInProgressTeamTasks(response.result))
                view.onDoneTeamTasksSuccess(getDoneTeamTasks(response.result))
            }

            override fun onFailure(throwable: Throwable, statusCode: Int?, message: String?) {
                view.onGetDataFailure(throwable.toString())
            }

        })
        view.hideLoading()
    }

    private fun getLatestTeamTasks(teamTasks: List<TeamTask>): List<TeamTask> {
        return teamTasks.take(2)
    }

    private fun getToDoTeamTasks(teamTasks: List<TeamTask>): List<TeamTask> {
        return teamTasks.filter { it.status == 0 }
    }

    private fun getInProgressTeamTasks(teamTasks: List<TeamTask>): List<TeamTask> {
        return teamTasks.filter { it.status == 1 }
    }

    private fun getDoneTeamTasks(teamTasks: List<TeamTask>): List<TeamTask> {
        return teamTasks.filter { it.status == 2 }
    }
    private fun getInProgressTasksNumberForTeam(teamTasks: List<TeamTask>): Int{
        return teamTasks.size
    }

}
