package com.cupcake.todo.model.network

import com.cupcake.todo.model.network.response.AddPersonalTaskResponse
import com.cupcake.todo.model.network.response.BaseResponse
import com.cupcake.todo.model.network.response.RegisterResponse
import com.cupcake.todo.model.network.util.ApiCallback

interface ApiService {
    fun register(
        username: String,
        password: String,
        callback: ApiCallback<BaseResponse<RegisterResponse>>,
    )
fun addPersonalTask(
    title:String,
    description:String,
    callback: ApiCallback<BaseResponse<AddPersonalTaskResponse>>
)
}