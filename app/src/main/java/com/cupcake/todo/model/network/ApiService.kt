package com.cupcake.todo.model.network

import com.cupcake.todo.model.network.response.*
import com.cupcake.todo.model.network.util.ApiCallback

interface ApiService {
    fun register(
        username: String,
        password: String,
        onSuccess: (response: BaseResponse<Register>) -> Unit,
        onFailure: (throwable: Throwable, statusCode: Int?, message: String?) -> Unit,
    )


    fun getAllPersonalTask(
        onSuccess: (response: BaseResponse<List<PersonalTask>>) -> Unit,
        onFailure: (throwable: Throwable, statusCode: Int?, message: String?) -> Unit,
    )

    fun getAllTeamTask(
        onSuccess: (response: BaseResponse<List<TeamTask>>) -> Unit,
        onFailure: (throwable: Throwable, statusCode: Int?, message: String?) -> Unit
    )

    fun addTeamTask(
        title: String,
        description: String,
        assignee: String,
        callback: ApiCallback<BaseResponse<AddTeamTaskResponse>>
    )

    fun getTeamTasks(callback: ApiCallback<BaseResponse<List<TeamTaskResponse>>>)

    fun addPersonalTask(
        title: String,
        description: String,
        callback: ApiCallback<BaseResponse<AddPersonalTaskResponse>>
    )

}