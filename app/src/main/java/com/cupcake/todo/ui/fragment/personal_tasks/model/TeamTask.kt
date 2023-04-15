package com.cupcake.todo.ui.fragment.personal_tasks.model

import android.os.Parcel
import android.os.Parcelable

data class TeamTask(
    override val id: String?,
    override val title: String?,
    override val description: String?,
    val assignee: String?,
    override val status: Int,
    override val creationTime: String?
): Task(id, title, description, status, creationTime) , Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(assignee)
        parcel.writeInt(status)
        parcel.writeString(creationTime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TeamTask> {
        override fun createFromParcel(parcel: Parcel): TeamTask {
            return TeamTask(parcel)
        }

        override fun newArray(size: Int): Array<TeamTask?> {
            return arrayOfNulls(size)
        }
    }

}