package com.cupcake.todo.ui.fragment.personal_tasks.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
open class Task(
    open val id: String?,
    open val title: String?,
    open val description: String?,
    open val status: Int,
    open val creationTime: String?
) : Parcelable