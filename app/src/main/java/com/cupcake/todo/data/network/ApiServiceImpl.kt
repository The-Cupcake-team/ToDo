package com.cupcake.todo.data.network

import com.cupcake.todo.BuildConfig
import com.cupcake.todo.data.network.util.ApiEndPoint
import okhttp3.Call
import okhttp3.FormBody

class ApiServiceImpl : ApiService {

    override fun register(username: String, password: String): Call {
        val registerBody = FormBody.Builder()
            .add(USERNAME, username)
            .add(PASSWORD, password)
            .add(TEAM_ID, BuildConfig.TEAM_ID)
            .build()
        return Client().postRequest(ApiEndPoint.register, registerBody)
    }


    private companion object {
        const val USERNAME = "username"
        const val PASSWORD = "password"
        const val TEAM_ID = "teamId"

        const val TITLE = "title"
        const val DESCRIPTION = "description"
        const val ASSIGNEE = "assignee"

        const val ID = "id"
        const val STATUS = "status"
    }

}