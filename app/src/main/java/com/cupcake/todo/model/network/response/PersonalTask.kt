package com.cupcake.todo.model.network.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PersonalTask(
    val id: String,
    val title: String,
    val description: String,
    val status: Int,
    val creationTime: String
) : Parcelable
