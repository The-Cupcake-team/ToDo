package com.cupcake.todo.data.network.interceptor

import okhttp3.CacheControl
import com.cupcake.todo.util.PrefsUtil
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
        val TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJodHRwczovL3RoZS1jaGFuY2Uub3JnLyIsInN1YiI6IjkyNDQ0MWI4LWM2MTUtNDdkNC1iMmYxLWQwODkyMjRjYmMyOSIsInRlYW1JZCI6IjkwOTJlZDcwLTMxNTMtNDEwNi1iYTE4LTYxYTU3Yjk0NmI2NCIsImlzcyI6Imh0dHBzOi8vdGhlLWNoYW5jZS5vcmcvIiwiZXhwIjoxNjgyMTYxNTUyfQ.wpmmRTIbnGQXk407KI5htDRWIZtmoYmQfWceuIUsJYI"
    }


}