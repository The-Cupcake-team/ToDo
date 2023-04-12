package com.cupcake.todo.ui.fragment.home

import com.cupcake.todo.ui.fragment.register.IView

interface IHomeView : IView {
    fun showLoading()
    fun hideLoading()
    fun onGetDataSuccess()
    fun onGetDataFailure(error: String)
}