package com.cupcake.todo.presenter.addTeamTask

import com.cupcake.todo.model.network.ApiServiceImpl
import com.cupcake.todo.model.network.response.AddTeamTaskResponse
import com.cupcake.todo.model.network.response.BaseResponse
import com.cupcake.todo.model.network.util.ApiCallback
import com.cupcake.todo.ui.fragment.add_team_task.IAddTeamTaskView

class AddTeamTaskPresenter(
    private val view: IAddTeamTaskView
): ApiCallback<BaseResponse<AddTeamTaskResponse>> {

    private val service = ApiServiceImpl()
    val assignee = listOf<String>("Mustafa", "Andrew", "Ameer", "Ethaar", "tarek", "Asia", "Hassan",
                                    "Ali","David", "Bilal", "Yousef", "Ali EG")

    fun addTeamTask(title: String, description: String, assignee: String){
        service.addTeamTask(title, description, assignee, ::onSuccess, ::onFailure)
    }
    override fun onSuccess(response: BaseResponse<AddTeamTaskResponse>) {
        view.onAddedTeamTaskSuccess(response.result.toString())
    }

    override fun onFailure(throwable: Throwable, statusCode: Int?, message: String?) {
        view.onAddedTeamTaskFailed(throwable.toString())
    }

}