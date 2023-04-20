package com.cupcake.todo.presenter.add_personal_task

import com.cupcake.todo.model.network.ApiServiceImpl
import com.cupcake.todo.model.network.response.BaseResponse
import com.cupcake.todo.model.network.response.PersonalTask
import com.cupcake.todo.model.network.util.ApiCallback
import com.cupcake.todo.ui.fragment.add_task.IAddPersonalTask

class AddPersonalTaskPresenter(
    private val view: IAddPersonalTask,

    ) : ApiCallback<BaseResponse<PersonalTask>> {

    private val service = ApiServiceImpl()

    fun addPersonalTask(title: String, description: String) {
        view.showLoading()
        service.addPersonalTask(title, description, ::onSuccess, ::onFailure)

    }

    override fun onSuccess(response: BaseResponse<PersonalTask>) {
        view.onSuccessAdded()
        view.hideLoading()
    }

    override fun onFailure(throwable: Throwable, statusCode: Int?, message: String?) {
        view.onFailureAdded(throwable.toString())
        view.hideLoading()
    }


}