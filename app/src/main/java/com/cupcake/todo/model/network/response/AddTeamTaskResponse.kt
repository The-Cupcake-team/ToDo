package com.cupcake.todo.model.network.response

import com.google.gson.annotations.SerializedName

data class AddTeamTaskResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("assignee")
    val assignee: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("creationTime")
    val creationTime: String,
)

