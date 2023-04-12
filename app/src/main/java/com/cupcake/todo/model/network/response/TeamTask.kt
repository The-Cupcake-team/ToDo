package com.cupcake.todo.model.network.response

data class TeamTask(
    val id: String,
    val title: String,
    val description: String,
    val assignee: String,
    val status: Int,
    val creationTime: String
)
