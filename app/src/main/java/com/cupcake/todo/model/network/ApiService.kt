package com.cupcake.todo.model.network

import com.cupcake.todo.model.network.response.BaseResponse
import com.cupcake.todo.model.network.response.Login
import com.cupcake.todo.model.network.response.PersonalTask
import com.cupcake.todo.model.network.response.Register
import com.cupcake.todo.model.network.response.TeamTask


import com.cupcake.todo.model.network.response.*
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

    fun login(
        username: String,
        password: String,
        onSuccess: (response: BaseResponse<Login>) -> Unit,
        onFailure: (throwable: Throwable, statusCode: Int?, message: String?) -> Unit
    )

    fun addTeamTask(
        title: String,
        description: String,
        assignee: String,
        onSuccess: (response: BaseResponse<TeamTask>) -> Unit,
        onFailure: (throwable: Throwable, statusCode: Int?, message: String?) -> Unit,
    )

    fun getTeamTasks(
        onSuccess: (response: BaseResponse<List<TeamTask>>) -> Unit,
        onFailure: (throwable: Throwable, statusCode: Int?, message: String?) -> Unit
    )

    fun getPersonalTasks(
        onSuccess: (response: BaseResponse<List<PersonalTask>>) -> Unit,
        onFailure: (throwable: Throwable, statusCode: Int?, message: String?) -> Unit
    )

    fun addPersonalTask(
        title: String,
        description: String,
        onSuccess: (response: BaseResponse<PersonalTask>) -> Unit,
        onFailure: (throwable: Throwable, statusCode: Int?, message: String?) -> Unit,
    )

    fun updateTaskStatus(
        id : String,
        status : Int,
        isPersonalTask: Boolean,
        onSuccess: (response: BaseResponse<String>) -> Unit,
        onFailure: (throwable: Throwable, statusCode: Int?, message: String?) -> Unit
    )
}