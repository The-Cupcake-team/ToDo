package com.cupcake.todo.util

import android.content.Context
import android.content.SharedPreferences

object PrefsUtil {
    private var todoSharedPreferences: SharedPreferences? = null
    private var todoSharedPreferencesEditor: SharedPreferences.Editor? = null
    private const val SHARED_PREFERENCES = "TodoSharedPreferences"
    private const val TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJodHRwczovL3RoZS1jaGFuY2Uub3JnLyIsInN1YiI6ImFjZTIyY2RkLTk5YjAtNDQxYS1hMGJjLWMwMGE4NTVmNTZmMyIsInRlYW1JZCI6IjkwOTJlZDcwLTMxNTMtNDEwNi1iYTE4LTYxYTU3Yjk0NmI2NCIsImlzcyI6Imh0dHBzOi8vdGhlLWNoYW5jZS5vcmcvIiwiZXhwIjoxNjgyMTMwODQyfQ.5n30PjeWyER38hYpYtkEf03iu7IHi6GzUhMSjo5bTjw"

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