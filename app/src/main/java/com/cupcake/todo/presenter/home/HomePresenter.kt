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
                    view.onToDoPersonalTaskSuccess(getToDoPersonalTask(response.result!!))
                    view.onInProgressPersonalTaskSuccess(getInProgressPersonalTask(response.result))
                    view.onDonePersonalTaskSuccess(getDonePersonalTask(response.result))
                    view.onRecentPersonalTaskSuccess(getRecentTask(response.result))
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

    private fun getToDoPersonalTask(personalTask: List<PersonalTask>): List<PersonalTask> {
        return personalTask.filter {
            it.status == TODO
        }
    }

    private fun getInProgressPersonalTask(personalTask: List<PersonalTask>): List<PersonalTask> {
        return personalTask.filter {
            it.status == INPROGRESS
        }
    }

    private fun getDonePersonalTask(personalTask: List<PersonalTask>): List<PersonalTask> {
        return personalTask.filter {
            it.status == DONE
        }
    }




    private fun getAllTeamTask() {
        service.getAllTeamTask(object : ApiCallback<BaseResponse<List<TeamTask>>> {

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
    }

    private fun getLatestTeamTasks(teamTasks: List<TeamTask>): List<TeamTask> {
        return teamTasks.take(4)
    }

    private fun getToDoTeamTasks(teamTasks: List<TeamTask>): List<TeamTask> {
        return teamTasks.filter { it.status ==TODO }
    }

    private fun getInProgressTeamTasks(teamTasks: List<TeamTask>): List<TeamTask> {
        return teamTasks.filter { it.status == INPROGRESS }
    }

    private fun getDoneTeamTasks(teamTasks: List<TeamTask>): List<TeamTask> {
        return teamTasks.filter { it.status == DONE }
    }

    private fun getInProgressTasksNumberForTeam(teamTasks: List<TeamTask>): Int {
        return teamTasks.size
    }

    private companion object {
        private const val TODO = 0
        private const val INPROGRESS = 1
        private const val DONE = 2
    }


}