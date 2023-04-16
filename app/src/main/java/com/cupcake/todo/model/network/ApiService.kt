package com.cupcake.todo.model.network

import com.cupcake.todo.model.network.response.AddPersonalTaskResponse
import com.cupcake.todo.model.network.response.AddTeamTaskResponse
import com.cupcake.todo.model.network.response.BaseResponse
import com.cupcake.todo.model.network.response.Register
import com.cupcake.todo.model.network.response.TeamTask
import com.cupcake.todo.model.network.util.ApiCallback

interface ApiService {
    fun register(
        username: String,
        password: String,
        onSuccess: (response: BaseResponse<Register>) -> Unit,
        onFailure: (throwable: Throwable, statusCode: Int?, message: String?) -> Unit,
    )

    fun addTeamTask(
        title: String,
        description: String,
        assignee: String,
        onSuccess: (response: BaseResponse<AddTeamTaskResponse>) -> Unit,
        onFailure: (throwable: Throwable, statusCode: Int?, message: String?) -> Unit,
    )

    fun getTeamTasks(
        onSuccess: (response: BaseResponse<List<TeamTask>>) -> Unit,
        onFailure: (throwable: Throwable, statusCode: Int?, message: String?) -> Unit
    )

    fun addPersonalTask(
        title: String,
        description: String,
        callback: ApiCallback<BaseResponse<AddPersonalTaskResponse>>
    )

}