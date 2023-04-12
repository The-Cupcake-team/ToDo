package com.cupcake.todo.model.network.response

data class PersonalTask(
    val id: String,
    val title: String,
    val description: String,
    val status: Int,
    val creationTime: String
)
