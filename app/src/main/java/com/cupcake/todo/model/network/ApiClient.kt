package com.cupcake.todo.model.network

import com.cupcake.todo.BuildConfig
import com.cupcake.todo.model.network.interceptor.AuthInterceptor
import com.cupcake.todo.model.network.interceptor.LoginInterceptor
import com.cupcake.todo.model.network.util.MethodRequest
import okhttp3.Call
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor

class ApiClient {
    private val okHttpClient = OkHttpClient()

    private val logInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    fun getRequest(
        path: String,
        username: String? = null,
        password: String? = null
    ): Call {
        val request = request(path = path)
        return executeHttpRequest(request, username, password)
    }

    fun postRequest(path: String, body: RequestBody): Call {
        val request = request(MethodRequest.POST, path, body)
        return executeHttpRequest(request)
    }

    fun putRequest(path: String, body: RequestBody): Call {
        val request = request(MethodRequest.PUT, path, body)
        return executeHttpRequest(request)
    }

    private fun request(
        method: MethodRequest = MethodRequest.GET,
        path: String,
        body: RequestBody? = null
    ): Request {
        return Request.Builder()
            .url(getHttpUrl(path))
            .method(method.name, body)
            .build()
    }

    private fun executeHttpRequest(
        request: Request,
        username: String? = null,
        password: String? = null,
    ): Call {
        val httpClient = okHttpClient.newBuilder().apply {
            addInterceptor(AuthInterceptor())
            if (!username.isNullOrEmpty() && !password.isNullOrEmpty()) {
                addInterceptor(LoginInterceptor(username, password))
            }
            addInterceptor(logInterceptor)
        }.build()

        return httpClient.newCall(request)
    }

    private fun getHttpUrl(path: String): HttpUrl {
        return HttpUrl.Builder()
            .scheme(HTTPS)
            .host(BuildConfig.HOST)
            .addPathSegments(path)
            .build()
    }


    private companion object {
        const val HTTPS = "https"
    }

}