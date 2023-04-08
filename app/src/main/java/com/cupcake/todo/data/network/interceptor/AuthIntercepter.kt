package com.cupcake.todo.data.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .header(AUTHORIZATION, "$BEARER $TOKEN")
            .build()
        return chain.proceed(request)
    }


    private companion object {
        const val AUTHORIZATION = "Authorization"
        const val BEARER = "bearer"
        const val TOKEN = ""
    }


}