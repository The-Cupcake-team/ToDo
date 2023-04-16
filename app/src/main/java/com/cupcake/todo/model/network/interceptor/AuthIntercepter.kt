package com.cupcake.todo.model.network.interceptor

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .cacheControl(cacheControl)
            .header(AUTHORIZATION, "$BEARER $TOKEN")
            .build()
        return chain.proceed(request)
    }

    private val cacheControl = CacheControl.Builder()
        .maxAge(1, TimeUnit.HOURS)
        .build()


    private companion object {
        const val AUTHORIZATION = "Authorization"
        const val BEARER = "bearer"
        const val TOKEN =
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJodHRwczovL3RoZS1jaGFuY2Uub3JnLyIsInN1YiI6ImYzYjA2MmE4LTZhOGUtNDJlNC1iNjU5LTc3NDM3NDNjMGJiYyIsInRlYW1JZCI6IjkwOTJlZDcwLTMxNTMtNDEwNi1iYTE4LTYxYTU3Yjk0NmI2NCIsImlzcyI6Imh0dHBzOi8vdGhlLWNoYW5jZS5vcmcvIiwiZXhwIjoxNjgxODE4OTgzfQ.JOWxoRlAbMhPw1rIk1oaNv-9ZnBzN-YfAhPDpEXk0JI"
    }


}