package com.cupcake.todo.data.network.response

import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("token")
    val token: String,
    @SerializedName("expireAt")
    val expireTime: String,
)