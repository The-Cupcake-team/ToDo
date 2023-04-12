package com.cupcake.todo.presenter.home

import android.util.Log
import com.cupcake.todo.model.network.ApiServiceImpl
import com.cupcake.todo.model.network.response.BaseResponse
import com.cupcake.todo.model.network.response.PersonalTask
import com.cupcake.todo.model.network.util.ApiCallback
import com.cupcake.todo.ui.fragment.home.IHomeView

class HomePresenter(
    private val view: IHomeView
) : ApiCallback<BaseResponse<List<PersonalTask>>> {

    private val service = ApiServiceImpl()

    fun getAllPersonal() {
        view.showLoading()
        val personalTask = service.getAllPersonalTask(this)
        view.hideLoading()
    }

    override fun onSuccess(response: BaseResponse<List<PersonalTask>>) {
        Log.e("result", "personalTask: ${response.result}")
        getRecentTask(response.result!!)
        view.onGetDataSuccess()

    }

    override fun onFailure(throwable: Throwable, statusCode: Int?, message: String?) {
        view.onGetDataFailure(throwable.toString())
    }

    private fun getRecentTask(personalTask: List<PersonalTask>): List<PersonalTask> {
        return personalTask.filter {
            it.creationTime == personalTask.maxByOrNull { it.creationTime }?.creationTime
        }
    }

}