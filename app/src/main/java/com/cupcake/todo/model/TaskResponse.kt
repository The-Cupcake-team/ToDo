package com.cupcake.todo.model

data class TaskResponse<T>(
    val value: List<T>,
    val message: String,
    val isSuccess: Boolean
)
