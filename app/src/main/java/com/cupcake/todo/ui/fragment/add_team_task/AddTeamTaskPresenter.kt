package com.cupcake.todo.ui.fragment.add_team_task

import com.cupcake.todo.data.network.ApiServiceImpl
import com.cupcake.todo.data.network.response.BaseResponse
import com.cupcake.todo.data.network.response.TeamTask
import com.cupcake.todo.data.network.util.ApiCallback

class AddTeamTaskPresenter(
    private val view: IAddTeamTaskView
): ApiCallback<BaseResponse<TeamTask>> {

    private val service = ApiServiceImpl()
    val assignee = listOf<String>("Mustafa", "Andrew", "Ameer", "Ethaar", "tarek", "Asia", "Hassan",
                                    "Ali","David", "Bilal", "Yousef", "Ali EG")

    fun addTeamTask(title: String, description: String, assignee: String){
        service.addTeamTask(title, description, assignee, ::onSuccess, ::onFailure)
    }
    override fun onSuccess(response: BaseResponse<TeamTask>) {
        view.onAddedTeamTaskSuccess(response.result.toString())
    }

    override fun onFailure(throwable: Throwable, statusCode: Int?, message: String?) {
        view.onAddedTeamTaskFailed(throwable.toString())
    }

}