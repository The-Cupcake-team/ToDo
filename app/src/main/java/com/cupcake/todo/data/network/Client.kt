package com.cupcake.todo.data.network

import com.cupcake.todo.BuildConfig
import com.cupcake.todo.data.network.interceptor.AuthInterceptor
import com.cupcake.todo.data.network.interceptor.LoginInterceptor
import com.cupcake.todo.data.network.util.TypeRequest
import okhttp3.Call
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor

class Client {
    private val okHttpClient = OkHttpClient()

    private val logInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }


    fun postRequest(path: String, body: RequestBody): Call {
        return makeHttpRequest(TypeRequest.POST, path, body)
    }

    fun putRequest(path: String, body: RequestBody): Call {
        return makeHttpRequest(TypeRequest.PUT, path, body)
    }

    fun getRequest(path: String): Call {
        return makeHttpRequest(TypeRequest.GET, path, null)
    }

    private fun makeHttpRequest(method: TypeRequest, path: String, body: RequestBody?): Call {
        val request = Request.Builder().url(getHttpUrl(path)).method(method.name, body).build()
        return authOkHttpClient().newCall(request)
    }

    fun getRequest(path: String, username: String, password: String): Call {
        val request = Request.Builder().url(getHttpUrl(path)).build()
        return loginOkHttpClient(username, password).newCall(request)
    }


    private fun authOkHttpClient(): OkHttpClient {
        return okHttpClient
            .newBuilder()
            .addInterceptor(AuthInterceptor())
            .addInterceptor(logInterceptor)
            .build()
    }

    private fun loginOkHttpClient(username: String, password: String): OkHttpClient {
        return okHttpClient
            .newBuilder()
            .addInterceptor(LoginInterceptor(username, password))
            .addInterceptor(logInterceptor)
            .build()
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