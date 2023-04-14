package com.cupcake.todo.model.network

import com.cupcake.todo.BuildConfig
import com.cupcake.todo.model.data.Task
import com.cupcake.todo.model.network.response.BaseResponse
import com.cupcake.todo.model.network.response.RegisterResponse
import com.cupcake.todo.model.network.util.ApiCallback
import com.cupcake.todo.model.network.util.ApiEndPoint
import com.cupcake.todo.model.network.util.enqueueCall
import okhttp3.FormBody

class ApiServiceImpl : ApiService {

    private val client = ApiClient()

    override fun register(
        username: String,
        password: String,
        callback: ApiCallback<BaseResponse<RegisterResponse>>,
    ) {
        val registerBody = FormBody.Builder()
            .add(USERNAME, username)
            .add(PASSWORD, password)
            .add(TEAM_ID, BuildConfig.TEAM_ID)
            .build()

        client.postRequest(ApiEndPoint.register, registerBody)
            .enqueueCall(
                object : ApiCallback<BaseResponse<RegisterResponse>> {
                    override fun onSuccess(response: BaseResponse<RegisterResponse>) {
                        callback.onSuccess(response)
                    }

                    override fun onFailure(
                        throwable: Throwable,
                        statusCode: Int?,
                        message: String?
                    ) {
                        callback.onFailure(throwable, statusCode, message)
                    }
                })
    }

    override fun updateStates(task: Task, callBack: ApiCallback<BaseResponse<String>>) {
        val updatestatusBody = task?.let {
            FormBody.Builder()
                .add(ID, it.id.toString())
                .add(STATUS, it.status.toString())
                .build()
        }
        var endPoint = ApiEndPoint.toDoPersonal
        if (!task.assigne.isNullOrEmpty())
         endPoint = ApiEndPoint.toDoTeam

        updatestatusBody?.let {
            client.putRequest(endPoint, it).enqueueCall(
                object : ApiCallback<BaseResponse<String>> {
                    override fun onSuccess(response: BaseResponse<String>) {
                        callBack.onSuccess(response)
                    }

                    override fun onFailure(throwable: Throwable, statusCode: Int?, message: String?) {
                        callBack.onFailure(throwable, statusCode, message)
                    }
                }
            )
        }
    }


    private companion object {
        const val USERNAME = "username"
        const val PASSWORD = "password"
        const val TEAM_ID = "teamId"

        const val TITLE = "title"
        const val DESCRIPTION = "description"
        const val ASSIGNEE = "assignee"

        const val ID = "id"
        const val STATUS = "status"
    }

}