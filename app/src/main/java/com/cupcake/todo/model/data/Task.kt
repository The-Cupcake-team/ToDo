package com.cupcake.todo.model.data
import java.io.Serializable
open class Task(val id : String,
                val title : String,
                val description : String,
                val status : Int,
                val createTime: String):Serializable {
}