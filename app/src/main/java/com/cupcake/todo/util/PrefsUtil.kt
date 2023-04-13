package com.cupcake.todo.util

import android.content.Context
import android.content.SharedPreferences

object PrefsUtil {
    private var todoSharedPreferences: SharedPreferences? = null
    private var todoSharedPreferencesEditor: SharedPreferences.Editor? = null
    private const val SHARED_PREFERENCES = "TodoSharedPreferences"
    private const val TOKEN = "token"

    fun initPrefsUtil(context: Context){
        todoSharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES,Context.MODE_PRIVATE)
        todoSharedPreferencesEditor = todoSharedPreferences?.edit()
    }

    var token: String?
        get() = todoSharedPreferences?.getString(TOKEN,null)
        set(value){
            todoSharedPreferencesEditor?.putString(TOKEN,value)?.apply()
        }

}