package com.cupcake.todo.model.network

import com.cupcake.todo.BuildConfig
import com.cupcake.todo.model.network.response.*
import com.cupcake.todo.model.network.util.ApiCallback
import com.cupcake.todo.model.network.util.ApiEndPoint
import com.cupcake.todo.model.network.util.enqueueCall
import com.cupcake.todo.ui.fragment.personal_tasks.model.TeamTask
import okhttp3.FormBody

class ApiServiceImpl : ApiService {

    private val client = ApiClient()

    override fun register(
        username: String,
        password: String,
        onSuccess: (response: BaseResponse<Register>) -> Unit,
        onFailure: (throwable: Throwable, statusCode: Int?, message: String?) -> Unit,
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
                        message: String?,
                    ) {
                        onFailure(throwable, statusCode, message)
                    }
                })
    }

    override fun getAllPersonalTask(
        onSuccess: (response: BaseResponse<List<PersonalTask>>) -> Unit,
        onFailure: (throwable: Throwable, statusCode: Int?, message: String?) -> Unit
    ) {
        client.getRequest(ApiEndPoint.toDoPersonal)
            .enqueueCall(object : ApiCallback<BaseResponse<List<PersonalTask>>> {
                override fun onSuccess(response: BaseResponse<List<PersonalTask>>) {
                    onSuccess(response)
                }

                override fun onFailure(throwable: Throwable, statusCode: Int?, message: String?) {
                    onFailure(throwable, statusCode, message)
                }
            })
    }


    override fun getAllTeamTask(
        onSuccess: (response: BaseResponse<List<TeamTask>>) -> Unit,
        onFailure: (throwable: Throwable, statusCode: Int?, message: String?) -> Unit
    ) {
        client.getRequest(ApiEndPoint.toDoTeam)
            .enqueueCall(object : ApiCallback<BaseResponse<List<TeamTask>>> {
                override fun onSuccess(response: BaseResponse<List<TeamTask>>) {
                    onSuccess(response)
                }

                override fun onFailure(throwable: Throwable, statusCode: Int?, message: String?) {
                    onFailure(throwable, statusCode, message)
                }
            })
    }

    override fun addTeamTask(
        title: String,
        description: String,
        assignee: String,
        onSuccess: (response: BaseResponse<AddTeamTaskResponse>) -> Unit,
        onFailure: (throwable: Throwable, statusCode: Int?, message: String?) -> Unit
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
                        onSuccess(response)
                    }

                    override fun onFailure(
                        throwable: Throwable,
                        statusCode: Int?,
                        message: String?,
                    ) {
                        onFailure(throwable, statusCode, message)
                    }
                })
    }

    override fun login(
        username: String,
        password: String,
        onSuccess: (response: BaseResponse<Login>) -> Unit,
        onFailure: (throwable: Throwable, statusCode: Int?, message: String?) -> Unit
    ) {
        client.getRequest(ApiEndPoint.login, username, password).enqueueCall(
            object : ApiCallback<BaseResponse<Login>> {
                override fun onSuccess(response: BaseResponse<Login>) {
                    onSuccess(response)
                }

                override fun onFailure(throwable: Throwable, statusCode: Int?, message: String?) {
                    onFailure(throwable, statusCode, message)
                }

            }
        )
    }


    override fun getTeamTasks(
        onSuccess: (response: BaseResponse<List<TeamTask>>) -> Unit,
        onFailure: (throwable: Throwable, statusCode: Int?, message: String?) -> Unit
    ) {
        client.getRequest(ApiEndPoint.toDoTeam).enqueueCall(
            object : ApiCallback<BaseResponse<List<TeamTask>>> {
                override fun onSuccess(response: BaseResponse<List<TeamTask>>) {
                    onSuccess(response)
                }

                override fun onFailure(throwable: Throwable, statusCode: Int?, message: String?) {
                    onFailure(throwable, statusCode, message)
                }

            }
        )
    }

    override fun addPersonalTask(
        title: String,
        description: String,
        onSuccess: (response: BaseResponse<AddPersonalTaskResponse>) -> Unit,
        onFailure: (throwable: Throwable, statusCode: Int?, message: String?) -> Unit,
    ) {
        val addPersonalTaskBody = FormBody.Builder()
            .add(TITLE, title)
            .add(DESCRIPTION, description)
            .build()
        client.postRequest(ApiEndPoint.toDoPersonal, addPersonalTaskBody)
            .enqueueCall(
                object : ApiCallback<BaseResponse<AddPersonalTaskResponse>> {
                    override fun onSuccess(response: BaseResponse<AddPersonalTaskResponse>) {
                        onSuccess(response)
                    }

                    override fun onFailure(
                        throwable: Throwable,
                        statusCode: Int?,
                        message: String?,
                    ) {
                        onFailure(throwable, statusCode, message)
                    }
                })
    }

    override fun updateTaskStatus(
        id: String,
        status: Int,
        isPersonalTask: Boolean,
        onSuccess: (response: BaseResponse<String>) -> Unit,
        onFailure: (throwable: Throwable, statusCode: Int?, message: String?) -> Unit
    ) {
        val updateStatusBody = FormBody.Builder()
            .add(ID, id)
            .add(STATUS, status.toString())
            .build()

        var endPoint = ApiEndPoint.toDoTeam
        if (isPersonalTask) {
            endPoint = ApiEndPoint.toDoPersonal
        }
        client.putRequest(endPoint, updateStatusBody)
            .enqueueCall(
                object : ApiCallback<BaseResponse<String>> {
                    override fun onSuccess(response: BaseResponse<String>) {
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