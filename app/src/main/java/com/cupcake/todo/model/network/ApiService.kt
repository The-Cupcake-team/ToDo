package com.cupcake.todo.model.network

import com.cupcake.todo.model.network.response.*
import com.cupcake.todo.model.network.util.ApiCallback

interface ApiService {
    fun register(
        username: String,
        password: String,
        callback: ApiCallback<BaseResponse<RegisterResponse>>,
    )

    fun addTeamTask(
        title: String,
        description: String,
        assignee: String,
        callback: ApiCallback<BaseResponse<AddTeamTaskResponse>>
    )

    fun getTeamTasks(callback: ApiCallback<BaseResponse<List<TeamTaskResponse>>>)

    fun addPersonalTask(
        title:String,
        description:String,
        callback: ApiCallback<BaseResponse<AddPersonalTaskResponse>>
    )

    fun updateStates(
        id: String,
        status: Int,
        callBack: ApiCallback<BaseResponse<String>>
    )
}