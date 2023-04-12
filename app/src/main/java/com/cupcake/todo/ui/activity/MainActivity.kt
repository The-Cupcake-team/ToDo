package com.cupcake.todo.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.cupcake.todo.databinding.ActivityMainBinding
import com.cupcake.todo.presenter.home.HomePresenter
import com.cupcake.todo.ui.fragment.home.IHomeView

class MainActivity : AppCompatActivity() , IHomeView {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val homePresenter= HomePresenter(this)
      val result =  homePresenter.getAllPersonal()
    }

    override fun showLoading() {
        Log.e("result","showLoading(")
    }

    override fun hideLoading() {
        Log.e("result","hideLoading(")
    }

    override fun onGetDataSuccess() {
        Log.e("result","onGetDataSuccess")
    }

    override fun onGetDataFailure(error: String) {
        Log.e("result","onGetDataFailure")
    }
}