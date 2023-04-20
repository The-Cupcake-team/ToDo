package com.cupcake.todo.data.network

import com.cupcake.todo.BuildConfig
import com.cupcake.todo.data.network.interceptor.AuthInterceptor
import com.cupcake.todo.data.network.util.MethodRequest
import okhttp3.Call
import okhttp3.Credentials
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor

class NetworkManager {


    private val authInterceptor = AuthInterceptor()
    private val logInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient().newBuilder()
        .addInterceptor(logInterceptor)
        .build()
    private val authOkHttpClient = okHttpClient
        .newBuilder()
        .addInterceptor(authInterceptor)
        .addInterceptor(logInterceptor)
        .build()

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

        if (!username.isNullOrEmpty() && !password.isNullOrEmpty()) {
            return loginOkHttpClient(request, username, password)
        }

        return authOkHttpClient.newCall(request)
    }

    private fun loginOkHttpClient(
        request: Request,
        username: String,
        password: String
    ): Call {
        val basicAuthentication = Credentials.basic(username, password)
        val loginRequest = request.newBuilder()
            .addHeader(AUTHORIZATION, basicAuthentication)
            .build()
        return okHttpClient.newCall(loginRequest)
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
        const val AUTHORIZATION = "Authorization"
    }

}