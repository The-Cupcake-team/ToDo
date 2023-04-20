package com.cupcake.todo.ui.fragment.add_personal_task

import com.cupcake.todo.data.network.ApiServiceImpl
import com.cupcake.todo.data.network.response.BaseResponse
import com.cupcake.todo.data.network.response.PersonalTask
import com.cupcake.todo.data.network.util.ApiCallback

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