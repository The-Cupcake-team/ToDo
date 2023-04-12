package com.cupcake.todo.presenter.teamtasks

import com.cupcake.todo.model.network.ApiServiceImpl
import com.cupcake.todo.model.network.response.BaseResponse
import com.cupcake.todo.model.network.response.TeamTasksResponse
import com.cupcake.todo.model.network.util.ApiCallback
import com.cupcake.todo.ui.fragment.team_tasks.ITeamTasksView

class TeamTasksPresenter(
    private val view:ITeamTasksView,
):ApiCallback<BaseResponse<List<TeamTasksResponse>>> {

    private val service = ApiServiceImpl()

    fun teamTasks(){
        view.showLoading()
        service.getTeamTasks(this)
        view.hideLoading()
    }

    override fun onSuccess(response: BaseResponse<List<TeamTasksResponse>>) {
        view.onTeamTasksSuccess()
    }

    override fun onFailure(throwable: Throwable, statusCode: Int?, message: String?) {
        view.onTeamTasksFailure(throwable.toString())
    }
}