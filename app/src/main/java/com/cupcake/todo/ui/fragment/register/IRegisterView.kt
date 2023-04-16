package com.cupcake.todo.ui.fragment.register

interface IView

interface IRegisterView : IView {
    fun showLoading()
    fun hideLoading()
    fun onRegisterSuccess()
    fun onRegisterFailure(error: String)
}