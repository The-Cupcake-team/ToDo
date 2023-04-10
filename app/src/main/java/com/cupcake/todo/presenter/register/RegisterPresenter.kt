package com.cupcake.todo.presenter.register

import com.cupcake.todo.model.network.ApiServiceImpl
import com.cupcake.todo.ui.fragment.register.IRegisterView
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class RegisterPresenter(
    private val view: IRegisterView,
) {

    private val apiService = ApiServiceImpl()
    fun register(username: String, password: String) {
        view.showLoading()

        apiService.register(username, password).enqueue(
            object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    view.onRegisterFailure(e.toString())
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.code == 201) {
                        view.onRegisterSuccess()
                    } else {
                        view.onRegisterFailure(response.code.toString())
                    }

                }

            }
        )
        view.hideLoading()
    }
}