package com.cupcake.todo.model.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PersonalTask(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("creationTime")
    val createTime: String,
    @SerializedName("description")
    val description: String?
) : Parcelable
