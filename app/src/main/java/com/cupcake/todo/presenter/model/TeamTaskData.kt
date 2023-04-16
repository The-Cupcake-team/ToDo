package com.cupcake.todo.presenter.model

data class TeamTaskData(
    val id: String,
    val title: String,
    val description: String,
    val assignee: String,
    val status: Int,
    val creationTime: String
)