package com.cupcake.todo.ui.fragment.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cupcake.todo.R
import com.cupcake.todo.databinding.FragmentRegisterBinding
import com.cupcake.todo.presenter.register.RegisterPresenter
import com.cupcake.todo.ui.base.BaseFragment
import com.cupcake.todo.ui.fragment.team_tasks.TeamTasksFragment

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(), IRegisterView {
    override val LOG_TAG: String = this::class.java.name
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> FragmentRegisterBinding =
        FragmentRegisterBinding::inflate
    private lateinit var presenter: RegisterPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = RegisterPresenter(this)
        binding.buttonSignUp.setOnClickListener {
            val username = binding.editTextUserName.text.toString()
            val password = binding.editTextPassword.text.toString()
            Log.v(LOG_TAG, " $username ,$password")
            presenter.register(username, password)
        }
    }

    override fun showLoading() {
        Log.v(LOG_TAG, "showLoading")
    }

    override fun hideLoading() {
        Log.v(LOG_TAG, "hideLoading")
    }

    override fun onRegisterSuccess() {
//        navigateToFragment(TeamTasksFragment())
        Log.v(LOG_TAG, "onRegisterSuccess")
    }

    override fun onRegisterFailure(error: String) {
        Log.v(LOG_TAG, "onRegisterFailure $error")
    }

}