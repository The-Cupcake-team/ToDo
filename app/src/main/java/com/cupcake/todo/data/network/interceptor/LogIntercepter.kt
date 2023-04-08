package com.cupcake.todo.data.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class LoginInterceptor(private val username: String, private val password: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .header(USERNAME, username)
            .header(PASSWORD, password)
            .build()
        return chain.proceed(request)
    }

    private companion object {
        const val USERNAME = "username"
        const val PASSWORD = "password"
    }

}