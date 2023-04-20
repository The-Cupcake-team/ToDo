package com.cupcake.todo.ui.fragment.login


import com.cupcake.todo.data.network.ApiServiceImpl
import com.cupcake.todo.data.network.response.BaseResponse
import com.cupcake.todo.data.network.response.Login
import com.cupcake.todo.util.PrefsUtil

class LoginPresenter(
    private val view: ILoginView,
) {

    private val service = ApiServiceImpl()

    fun login(username: String, password: String) {
        view.showLoading()
        service.login(username, password, ::onSuccess, ::onFailure)
    }

    private fun onSuccess(response: BaseResponse<Login>) {
        PrefsUtil.token = response.result!!.token
        view.onLoginSuccess()
        view.hideLoading()
    }

    private fun onFailure(throwable: Throwable, statusCode: Int?, message: String?) {
        view.onLoginFailure(throwable, statusCode, message)
        view.hideLoading()
    }

}