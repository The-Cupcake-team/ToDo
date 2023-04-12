package com.cupcake.todo.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.cupcake.todo.databinding.ActivityMainBinding
import com.cupcake.todo.model.network.response.PersonalTask
import com.cupcake.todo.model.network.response.TeamTask
import com.cupcake.todo.presenter.home.HomePresenter
import com.cupcake.todo.ui.fragment.home.IHomeView

class MainActivity : AppCompatActivity(), IHomeView {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        HomePresenter(this).getAllPersonalTask()
        HomePresenter(this).getAllTeamTask()

    }



    override fun showLoading() {
        Log.e("result", "showLoading(")
    }

    override fun hideLoading() {
        Log.e("result", "hideLoading(")
    }

    override fun onRecentPersonalTaskSuccess(personalTasks: List<PersonalTask>) {
        Log.e("result", "onRecentPersonalTaskSuccess: ${personalTasks}")
    }

    override fun onToDoPersonalTaskSuccess(personalTasks: List<PersonalTask>) {
        Log.e("result", "onToDoPersonalTaskSuccess: ${personalTasks}")
    }

    override fun onInProgressPersonalTaskSuccess(personalTasks: List<PersonalTask>) {
        Log.e("result", "onInProgressPersonalTaskSuccess: ${personalTasks}")
    }


    override fun onDonePersonalTaskSuccess(personalTasks: List<PersonalTask>) {
        Log.e("result", "onDonePersonalTaskSuccess: ${personalTasks}")
    }


    override fun onGetLatestTeamTaskSuccess(teamTasks: List<TeamTask>) {
        Log.e("result", "onGetLatestTeamTaskSuccess: ${teamTasks}")
    }
    override fun onToDoTeamTasksSuccess(teamTasks: List<TeamTask>) {
        Log.e("result", "onToDoTeamTasksSuccess: ${teamTasks}")
    }

    override fun onInProgressTeamTasksSuccess(teamTasks: List<TeamTask>) {
        Log.e("result", "onInProgressTeamTasksSuccess: ${teamTasks}")
    }

    override fun onDoneTeamTasksSuccess(teamTasks: List<TeamTask>) {
        Log.e("result", "onDoneTeamTasksSuccess: ${teamTasks}")
    }

    override fun onGetDataFailure(error: String) {
        Log.e("result", "onGetDataFailure")
    }




}