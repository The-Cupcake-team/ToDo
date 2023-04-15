package com.cupcake.todo.model.network

import com.cupcake.todo.model.network.response.BaseResponse
import com.cupcake.todo.model.network.response.Register
import com.cupcake.todo.model.network.response.TeamTaskResponse
import com.cupcake.todo.model.network.util.ApiCallback

interface ApiService {
    fun register(
        username: String,
        password: String,
        onSuccess: (response: BaseResponse<Register>) -> Unit,
        onFailure: (throwable: Throwable, statusCode: Int?, message: String?) -> Unit,
    )

    fun getTeamTasks(callback: ApiCallback<BaseResponse<List<TeamTaskResponse>>>)

    fun updateTaskStatus(
        id : String,
        status : Int,
        isPersonalTask: Boolean,
        callback: ApiCallback<BaseResponse<String>>
    )
}