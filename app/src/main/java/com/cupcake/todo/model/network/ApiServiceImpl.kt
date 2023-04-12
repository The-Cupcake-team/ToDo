package com.cupcake.todo.model.network


import com.cupcake.todo.model.network.response.AddPersonalTaskResponse
import com.cupcake.todo.model.network.response.BaseResponse
import com.cupcake.todo.model.network.response.RegisterResponse
import com.cupcake.todo.model.network.util.ApiCallback
import com.cupcake.todo.model.network.util.ApiEndPoint
import com.cupcake.todo.model.network.util.enqueueCall
import okhttp3.FormBody

class ApiServiceImpl : ApiService {

    private val client = ApiClient()


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

    override fun register(
        username: String,
        password: String,
        callback: ApiCallback<BaseResponse<RegisterResponse>>,
    ) {
        TODO("Not yet implemented")
    }

    override fun addPersonalTask(
        title: String,
        description: String,
        callback: ApiCallback<BaseResponse<AddPersonalTaskResponse>>,
    ) {
        val body=FormBody.Builder()
            .add(TITLE,title)
            .add(DESCRIPTION,description)
            .build()
        client.postRequest(ApiEndPoint.toDoPersonal,body).enqueueCall(
            object :ApiCallback<BaseResponse<AddPersonalTaskResponse>>{
                override fun onSuccess(response: BaseResponse<AddPersonalTaskResponse>) {
                    callback.onSuccess(response)
                }

                override fun onFailure(throwable: Throwable, statusCode: Int?, message: String?) {
                    callback.onFailure(throwable, statusCode, message)
                }

            }
        )
    }

}