package com.cupcake.todo.model.network.response

import com.google.gson.annotations.SerializedName

data class AddPersonalTaskResponse(
    @SerializedName("id")
    val userId: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("creationTime")
    val creationTime: String,
)
