package com.cupcake.todo.model.network.interceptor

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
        const val TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJodHRwczovL3RoZS1jaGFuY2Uub3JnLyIsInN1YiI6ImFjZTIyY2RkLTk5YjAtNDQxYS1hMGJjLWMwMGE4NTVmNTZmMyIsInRlYW1JZCI6IjkwOTJlZDcwLTMxNTMtNDEwNi1iYTE4LTYxYTU3Yjk0NmI2NCIsImlzcyI6Imh0dHBzOi8vdGhlLWNoYW5jZS5vcmcvIiwiZXhwIjoxNjgxNjM2MzA5fQ.KfHAWPgNDYYinEhszYLsIZ52bIO-jiGUcIHZq2FkefI"
    }


}