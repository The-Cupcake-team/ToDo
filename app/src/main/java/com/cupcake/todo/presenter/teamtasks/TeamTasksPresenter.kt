package com.cupcake.todo.presenter.teamtasks

import com.cupcake.todo.model.network.ApiServiceImpl
import com.cupcake.todo.model.network.response.BaseResponse
import com.cupcake.todo.model.network.response.TeamTaskResponse
import com.cupcake.todo.model.network.util.ApiCallback
import com.cupcake.todo.presenter.mapper.toTeamTask
import com.cupcake.todo.ui.fragment.team_tasks.ITeamTasksView
import java.io.IOException


class TeamTasksPresenter(
    private val view:ITeamTasksView,
):ApiCallback<BaseResponse<List<TeamTaskResponse>>> {

    private val service = ApiServiceImpl()

    fun teamTasks(){
        view.showLoading()
        service.getTeamTasks(this)
    }

    override fun onSuccess(response: BaseResponse<List<TeamTaskResponse>>) {
        val teamTasksDataList = response.result?.map {  it.toTeamTask() }
        view.onTeamTasksSuccess(teamTasksDataList!!)
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