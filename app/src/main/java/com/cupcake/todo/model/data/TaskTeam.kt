package com.cupcake.todo.model.data

class TaskTeam(
     id : String,
    title : String,
     description : String,
     status : Int,
     createTime: String,
     teamId: String ): Task(id, title, description, status, createTime){
}