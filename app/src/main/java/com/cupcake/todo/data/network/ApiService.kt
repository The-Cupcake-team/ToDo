package com.cupcake.todo.data.network

import okhttp3.Call

interface ApiService {
    fun register(username: String, password: String): Call
}