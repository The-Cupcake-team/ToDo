package com.cupcake.todo.data.network.response

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("value")
    val result: T?,
    @SerializedName("message")
    val message: String,
    @SerializedName("isSuccess")
    val isSuccess: Boolean,
)