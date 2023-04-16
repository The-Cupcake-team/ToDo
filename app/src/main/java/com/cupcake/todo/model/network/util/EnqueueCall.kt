package com.cupcake.todo.model.network.util

import android.util.Log
import com.cupcake.todo.model.network.response.BaseResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

inline fun <reified T : Any> Call.enqueueCall(callback: ApiCallback<T>) {
    this.enqueue(object : Callback {
        override fun onResponse(call: Call, response: Response) {
            val responseBody = response.body?.string()
            try {
                if (responseBody != null) {
                    val gson = Gson()
                    val type = object : TypeToken<T>() {}.type
                    val result = gson.fromJson<T>(responseBody, type)
                    if (response.isSuccessful) {
                        callback.onSuccess(result)
                    } else {
                        val message = (result as? BaseResponse<*>)?.message
                        callback.onFailure(
                            throwable = Exception(message),
                            statusCode = response.code,
                            message = message,
                        )
                        Log.e("result", "error in else call backs:${message}")
                    }
                } else {
                    callback.onFailure(Exception("Response Body is null"))
                    Log.e("result", "Response Body is null")
                }
            } catch (error: Exception) {
                callback.onFailure(error)
                Log.e("result", "error in catch call backs:${error.message}")

            }
        }

        override fun onFailure(call: Call, e: IOException) {
            callback.onFailure(throwable = e)
        }
    })
}