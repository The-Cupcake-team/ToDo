package com.cupcake.todo.ui.fragment.home

import com.cupcake.todo.ui.fragment.register.IView

interface IHomeView : IView {
    fun showLoading()
    fun hideLoading()
    fun onRegisterSuccess()
    fun onRegisterFailure(error: String)
}