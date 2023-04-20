package com.cupcake.todo.ui.fragment.login

import com.cupcake.todo.ui.fragment.register.IView

interface ILoginView: IView {
    fun showLoading()
    fun hideLoading()
    fun onLoginSuccess()
    fun onLoginFailure(throwable: Throwable,statusCode: Int?,error: String?)
}