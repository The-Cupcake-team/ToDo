package com.cupcake.todo.ui.fragment.register

import com.cupcake.todo.data.network.ApiServiceImpl
import com.cupcake.todo.data.network.response.BaseResponse
import com.cupcake.todo.data.network.response.Register

class RegisterPresenter(
    private val view: IRegisterView,
) {

    private val service = ApiServiceImpl()

    fun register(username: String, password: String) {
        view.showLoading()
        service.register(username, password, ::onSuccess, ::onFailure)
    }

    private fun onSuccess(response: BaseResponse<Register>) {
        view.onRegisterSuccess()
        view.hideLoading()
    }

    private fun onFailure(throwable: Throwable, statusCode: Int?, message: String?) {
        view.onRegisterFailure(throwable.toString())
        view.hideLoading()
    }
}