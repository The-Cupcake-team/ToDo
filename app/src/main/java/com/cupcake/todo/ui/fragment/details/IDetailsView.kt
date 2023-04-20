package com.cupcake.todo.ui.fragment.details

import com.cupcake.todo.ui.fragment.register.IView

interface IDetailsView : IView {

    fun showLoading()
    fun hideLoading()
    fun onUpDateSuccess()
    fun onUpDateFailure(error: String)

}