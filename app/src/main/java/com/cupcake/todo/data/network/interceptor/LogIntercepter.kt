package com.cupcake.todo.data.network.interceptor

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

class LoginInterceptor(private val username: String, private val password: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val credentials = Credentials.basic(username, password)
        val request = chain.request()
            .newBuilder()
            .header(AUTHORIZATION, credentials)
            .build()
        return chain.proceed(request)
    }

    private companion object {
        const val AUTHORIZATION = "Authorization"
    }

}