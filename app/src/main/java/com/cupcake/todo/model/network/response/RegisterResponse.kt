package com.cupcake.todo.model.network.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("userId")
    val userId: String,
    @SerializedName("username")
    val username: String,
)