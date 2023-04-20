package com.cupcake.todo.data.network.response

import com.google.gson.annotations.SerializedName

data class Register(
    @SerializedName("userId")
    val userId: String,
    @SerializedName("username")
    val username: String,
)