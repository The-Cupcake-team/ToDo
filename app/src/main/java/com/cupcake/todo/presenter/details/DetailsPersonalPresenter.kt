package com.cupcake.todo.presenter.details

import com.cupcake.todo.model.data.Task
import com.cupcake.todo.model.network.ApiServiceImpl
import com.cupcake.todo.model.network.response.BaseResponse
import com.cupcake.todo.model.network.util.ApiCallback
import com.cupcake.todo.ui.fragment.details.IDetailsView


class DetailsPresenter(private val view: IDetailsView) : ApiCallback<BaseResponse<String>> {

    private val apiService = ApiServiceImpl()

    fun DetailsupDate(task: Task) {
        view.showLoading()
        apiService.updateStates(task,this)
        view.hideLoading()
    }
    val assignee = listOf<String>("Mustafa", "Andrew", "Ameer", "Ethaar", "tarek", "Asia", "Hassan",
        "Ali","David", "Bilal", "Yousef", "Ali EG")

    override fun onSuccess(response: BaseResponse<String>) {
        view.onUpDateSuccess()
    }

    override fun onFailure(throwable: Throwable, statusCode: Int?, message: String?) {
        view.onUpDateFailure(throwable.toString())
    }
}