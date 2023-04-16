package com.cupcake.todo.presenter.teamtasks

import com.cupcake.todo.model.network.ApiServiceImpl
import com.cupcake.todo.model.network.response.BaseResponse
import com.cupcake.todo.model.network.response.TeamTask
import com.cupcake.todo.model.network.util.ApiCallback
import com.cupcake.todo.ui.fragment.team_tasks.ITeamTasksView
import java.io.IOException


class TeamTasksPresenter(
    private val view:ITeamTasksView,
):ApiCallback<BaseResponse<List<TeamTask>>> {

    private val service = ApiServiceImpl()

    fun teamTasks(){
        view.showLoading()
        service.getTeamTasks(::onSuccess, ::onFailure)
    }

    override fun onSuccess(response: BaseResponse<List<TeamTask>>) {
        val teamTasks = response.result!!
        view.onTeamTasksSuccess(teamTasks)
        view.hideLoading()
    }

    override fun onFailure(throwable: Throwable, statusCode: Int?, message: String?) {
        if (throwable is IOException) {
            view.showInternetErrorDialog()
        } else {
            view.onTeamTasksFailure(throwable.toString())
        }
        view.hideLoading()
    }

}