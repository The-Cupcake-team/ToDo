package com.cupcake.todo.ui.fragment.add_personal_task

import com.cupcake.todo.ui.fragment.register.IView


interface IAddPersonalTask : IView {
    fun showLoading()
    fun hideLoading()
    fun onSuccessAdded()
    fun onFailureAdded(error: String)
}
