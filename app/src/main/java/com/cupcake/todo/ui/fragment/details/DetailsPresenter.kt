package com.cupcake.todo.ui.fragment.details

import com.cupcake.todo.model.network.ApiServiceImpl
import com.cupcake.todo.model.network.response.BaseResponse


class DetailsPresenter(private val view: IDetailsView) {

    private val apiService = ApiServiceImpl()

    fun detailsUpDate(
        id: String,
        status: Int,
        isPersonalTask: Boolean
    ) {
        view.showLoading()
        apiService.updateTaskStatus(id, status, isPersonalTask, ::onSuccess, ::onFailure)
    }

    val team = listOf(
        "Mustafa", "Andrew", "Ameer", "Ethaar", "Tarek", "Asia", "Hassan",
        "Ali", "David", "Bilal", "Yousef", "Ali EG"
    )

    private fun onSuccess(response: BaseResponse<String>) {
        view.onUpDateSuccess()
        view.hideLoading()
    }

    private fun onFailure(throwable: Throwable, statusCode: Int?, message: String?) {
        view.onUpDateFailure(throwable.toString())
        view.hideLoading()
    }
}