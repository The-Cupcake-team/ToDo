package com.cupcake.todo.util

import android.content.Context
import android.content.SharedPreferences

object PrefsUtil {
    private var todoSharedPreferences: SharedPreferences? = null
    private var todoSharedPreferencesEditor: SharedPreferences.Editor? = null
    private const val SHARED_PREFERENCES = "TodoSharedPreferences"
    private const val TOKEN = "token"
    private const val USER_NAME = "userName"

    fun initPrefsUtil(context: Context) {
        todoSharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
        todoSharedPreferencesEditor = todoSharedPreferences?.edit()
    }

    var token: String?
        get() = todoSharedPreferences?.getString(TOKEN, null)
        set(value) {
            todoSharedPreferencesEditor?.putString(TOKEN, value)?.apply()
        }

    var userName: String?
        get() = todoSharedPreferences?.getString(USER_NAME, null)
        set(value) {
            todoSharedPreferencesEditor?.putString(USER_NAME, value)?.apply()
        }

}