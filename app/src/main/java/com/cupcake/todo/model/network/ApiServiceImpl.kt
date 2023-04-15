package com.cupcake.todo.model.network

import com.cupcake.todo.BuildConfig
import com.cupcake.todo.model.network.response.*
import com.cupcake.todo.model.network.response.PersonalTask
import com.cupcake.todo.model.network.response.RegisterResponse
import com.cupcake.todo.model.network.response.TeamTask


import com.cupcake.todo.model.network.util.ApiCallback
import com.cupcake.todo.model.network.util.ApiEndPoint
import com.cupcake.todo.model.network.util.enqueueCall
import okhttp3.Callback
import okhttp3.FormBody

class ApiServiceImpl : ApiService {

    private val client = ApiClient()


    override fun register(
        username: String,
        password: String,
        onSuccess: (response: BaseResponse<Register>) -> Unit,
        onFailure: (throwable: Throwable, statusCode: Int?, message: String?) -> Unit
    ) {
        val registerBody = FormBody.Builder()
            .add(USERNAME, username)
            .add(PASSWORD, password)
            .add(TEAM_ID, BuildConfig.TEAM_ID)
            .build()

        client.postRequest(ApiEndPoint.register, registerBody)
            .enqueueCall(
                object : ApiCallback<BaseResponse<Register>> {
                    override fun onSuccess(response: BaseResponse<Register>) {
                        onSuccess(response)
                    }

                    override fun onFailure(
                        throwable: Throwable,
                        statusCode: Int?,
                        message: String?
                    ) {
                        onFailure(throwable, statusCode, message)
                    }
                })
    }

    override fun getAllPersonalTask(callback: ApiCallback<BaseResponse<List<PersonalTask>>>) {
        client.getRequest(ApiEndPoint.toDoPersonal)
            .enqueueCall(object : ApiCallback<BaseResponse<List<PersonalTask>>> {
                override fun onSuccess(response: BaseResponse<List<PersonalTask>>) {
                    callback.onSuccess(response)
                }

                override fun onFailure(throwable: Throwable, statusCode: Int?, message: String?) {
                    callback.onFailure(throwable, statusCode, message)

                }
            })
    }


    override fun getAllTeamTask(callback: ApiCallback<BaseResponse<List<TeamTask>>>) {
        client.getRequest(ApiEndPoint.toDoTeam)
            .enqueueCall(object : ApiCallback<BaseResponse<List<TeamTask>>> {
                override fun onSuccess(response: BaseResponse<List<TeamTask>>) {
                    callback.onSuccess(response)
                }

                override fun onFailure(throwable: Throwable, statusCode: Int?, message: String?) {
                    callback.onFailure(throwable, statusCode, message)
                }

            })
    }


    override fun addTeamTask(
        title: String,
        description: String,
        assignee: String,
        callback: ApiCallback<BaseResponse<AddTeamTaskResponse>>
    ) {
        val teamTask = FormBody.Builder()
            .add(TITLE, title)
            .add(DESCRIPTION, description)
            .add(ASSIGNEE, assignee)
            .build()

        client.postRequest(ApiEndPoint.toDoTeam, teamTask)
            .enqueueCall(
                object : ApiCallback<BaseResponse<AddTeamTaskResponse>> {
                    override fun onSuccess(response: BaseResponse<AddTeamTaskResponse>) {
                        callback.onSuccess(response)
                    }

                    override fun onFailure(
                        throwable: Throwable,
                        statusCode: Int?,
                        message: String?
                    ) {
                        callback.onFailure(throwable, statusCode, message)
                    }

                }
            )
    }

    override fun getTeamTasks(callback: ApiCallback<BaseResponse<List<TeamTaskResponse>>>) {
        client.getRequest(ApiEndPoint.toDoTeam).enqueueCall(
            object : ApiCallback<BaseResponse<List<TeamTaskResponse>>> {
                override fun onSuccess(response: BaseResponse<List<TeamTaskResponse>>) {
                    callback.onSuccess(response)
                }

                override fun onFailure(throwable: Throwable, statusCode: Int?, message: String?) {
                    callback.onFailure(throwable, statusCode, message)
                }

            }
        )
    }

    override fun addPersonalTask(
        title: String,
        description: String,
        callback: ApiCallback<BaseResponse<AddPersonalTaskResponse>>,
    ) {
        val body = FormBody.Builder()
            .add(TITLE, title)
            .add(DESCRIPTION, description)
            .build()
        client.postRequest(ApiEndPoint.toDoPersonal, body).enqueueCall(
            object : ApiCallback<BaseResponse<AddPersonalTaskResponse>> {
                override fun onSuccess(response: BaseResponse<AddPersonalTaskResponse>) {
                    callback.onSuccess(response)
                }

                override fun onFailure(throwable: Throwable, statusCode: Int?, message: String?) {
                    callback.onFailure(throwable, statusCode, message)
                }

            }
        )
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