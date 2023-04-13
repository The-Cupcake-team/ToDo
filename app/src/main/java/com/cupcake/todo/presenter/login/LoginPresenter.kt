package com.cupcake.todo.presenter.login


import com.cupcake.todo.model.network.ApiServiceImpl
import com.cupcake.todo.model.network.response.BaseResponse
import com.cupcake.todo.model.network.response.LoginResponse
import com.cupcake.todo.model.network.util.ApiCallback
import com.cupcake.todo.util.PrefsUtil
import com.cupcake.todo.ui.fragment.login.ILoginView

class LoginPresenter(
    private val view: ILoginView,
): ApiCallback<BaseResponse<LoginResponse>>{

    private val service = ApiServiceImpl()

    fun login(username: String, password: String){
        view.showLoading()
        service.login(username,password,this)
        view.hideLoading()
    }
    override fun onSuccess(response: BaseResponse<LoginResponse>) {
        PrefsUtil.token = response.result!!.token
        view.onLoginSuccess()
    }

    override fun onFailure(throwable: Throwable, statusCode: Int?, message: String?) {
        view.onLoginFailure(statusCode,message)
    }

}