package com.cupcake.todo.model.data

import android.os.Parcel
import android.os.Parcelable
class Task(
    val id: String?,
    val title: String?,
    val description: String?,
    val status: Int,
    val createTime: String? ,
    val assigne : String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()

    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(id)
        dest.writeString(title)
        dest.writeString(description)
        dest.writeInt(status)
        dest.writeString(createTime)
        dest.writeString(assigne)
    }

    companion object CREATOR : Parcelable.Creator<Task> {
        override fun createFromParcel(parcel: Parcel): Task {
            return Task(parcel)
        }

        override fun newArray(size: Int): Array<Task?> {
            return arrayOfNulls(size)
        }
    }
}