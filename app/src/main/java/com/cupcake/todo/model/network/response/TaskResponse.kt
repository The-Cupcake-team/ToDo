package com.cupcake.todo.model.network.response

data class TaskResponse<T>(
    val value: List<T>,
    val message: String,
    val isSuccess: Boolean
)
