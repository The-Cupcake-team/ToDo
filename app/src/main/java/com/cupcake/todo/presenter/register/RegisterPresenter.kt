package com.cupcake.todo.presenter.register

import com.cupcake.todo.model.network.ApiServiceImpl
import com.cupcake.todo.model.network.response.BaseResponse
import com.cupcake.todo.model.network.response.RegisterResponse
import com.cupcake.todo.model.network.util.ApiCallback
import com.cupcake.todo.ui.fragment.register.IRegisterView

class RegisterPresenter(
    private val view: IRegisterView,
) : ApiCallback<BaseResponse<RegisterResponse>> {

    private val service = ApiServiceImpl()

    fun register(username: String, password: String) {
        view.showLoading()
        service.register(username, password, this)
        view.hideLoading()
    }

    override fun onSuccess(response: BaseResponse<RegisterResponse>) {
        view.onRegisterSuccess()
    }

    override fun onFailure(throwable: Throwable, statusCode: Int?, message: String?) {
        view.onRegisterFailure(throwable.toString())
    }
}