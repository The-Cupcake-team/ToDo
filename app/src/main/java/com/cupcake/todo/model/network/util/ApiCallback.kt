package com.cupcake.todo.model.network.util

interface ApiCallback<T> {
    fun onSuccess(response: T)
    fun onFailure(
        throwable: Throwable,
        statusCode: Int? = null,
        message: String? = null
    )
}
