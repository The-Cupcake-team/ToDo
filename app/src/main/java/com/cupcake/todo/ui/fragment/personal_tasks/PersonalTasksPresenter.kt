package com.cupcake.todo.ui.fragment.personal_tasks

import com.cupcake.todo.data.network.ApiServiceImpl
import com.cupcake.todo.data.network.response.BaseResponse
import com.cupcake.todo.data.network.response.PersonalTask

class PersonalTasksPresenter(private val view: IPersonalTasksView) {

    private val apiService = ApiServiceImpl()

    fun personalTasks() {
        view.showLoading()
        apiService.getPersonalTasks(::onSuccess, ::onFailure)
    }

    private fun onSuccess(response: BaseResponse<List<PersonalTask>>) {
        val personalTask = response.result!!
        view.bindPersonalTasksToAdapter(personalTask)
        view.hideLoading()
    }

    private fun onFailure(throwable: Throwable, statusCode: Int?, message: String?) {
        view.handlePersonalTasksFetchError(throwable.toString())
    }
}