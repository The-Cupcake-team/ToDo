package com.cupcake.todo.presenter.details

import com.cupcake.todo.model.network.ApiServiceImpl
import com.cupcake.todo.model.network.response.BaseResponse
import com.cupcake.todo.model.network.util.ApiCallback
import com.cupcake.todo.presenter.model.Task
import com.cupcake.todo.ui.fragment.details.IDetailsView


class DetailsPresenter(private val view: IDetailsView) : ApiCallback<BaseResponse<String>> {

    private val apiService = ApiServiceImpl()

    fun detailsUpDate(
        id: String,
        status: Int,
        isPersonalTask: Boolean
    ) {
        view.showLoading()
        apiService.updateTaskStatus(id, status, isPersonalTask, this)
        view.hideLoading()
    }

    val team = listOf(
        "Mustafa", "Andrew", "Ameer", "Ethaar", "Tarek", "Asia", "Hassan",
        "Ali", "David", "Bilal", "Yousef", "Ali EG"
    )

    override fun onSuccess(response: BaseResponse<String>) {
        view.onUpDateSuccess()
    }

    override fun onFailure(throwable: Throwable, statusCode: Int?, message: String?) {
        view.onUpDateFailure(throwable.toString())
    }
}