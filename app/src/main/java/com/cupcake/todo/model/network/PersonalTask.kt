package com.cupcake.todo.model.network

data class PersonalTask(
    val id: String,
    val title: String,
    val description: String,
    val status: Boolean,
    val creationTime: String
)
