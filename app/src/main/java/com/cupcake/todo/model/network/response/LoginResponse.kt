package com.cupcake.todo.model.network.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token")
    val token: String,
    @SerializedName("expireAt")
    val expireTime: String,
)