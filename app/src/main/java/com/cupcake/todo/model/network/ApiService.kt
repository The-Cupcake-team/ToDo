package com.cupcake.todo.model.network

import okhttp3.Call

interface ApiService {
    fun register(username: String, password: String): Call
}