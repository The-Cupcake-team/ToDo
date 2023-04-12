package com.cupcake.todo.ui.fragment.add_task

interface IView
interface IAddPersonalTask : IView{
    fun showLoading()
    fun hideLoading()
    fun onIAddPersonalTaskSuccess()
    fun onIAddPersonalTaskFailure(error: String)
}
