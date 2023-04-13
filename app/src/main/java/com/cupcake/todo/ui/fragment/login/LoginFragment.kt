package com.cupcake.todo.ui.fragment.login

import android.app.AlertDialog
import android.content.res.ColorStateList
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.UiThread
import androidx.core.content.ContextCompat
import com.cupcake.todo.R
import com.cupcake.todo.databinding.FragmentLoginBinding
import com.cupcake.todo.presenter.login.LoginPresenter
import com.cupcake.todo.ui.base.BaseFragment
import com.cupcake.todo.ui.fragment.add_task.AddTaskFragment
import com.cupcake.todo.ui.fragment.register.RegisterFragment


class LoginFragment() : BaseFragment<FragmentLoginBinding>(), ILoginView {
    override val LOG_TAG: String = this::class.java.name
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> FragmentLoginBinding =
        FragmentLoginBinding::inflate
    private lateinit var presenter: LoginPresenter

    private lateinit var username: String
    private lateinit var password: String
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
        addCallbacks()
    }

    private fun init() {
        presenter = LoginPresenter(this)
    }

    private fun addCallbacks() {
        onLoginButtonPressed()
        changeEndIconOfPasswordEditText()
        onSignupButtonPressed()
    }

    private fun onLoginButtonPressed() {
        binding.buttonLogin.setOnClickListener {
            assignUsernameAndPassword()
            if (validateForm()) {
                presenter.login(username, password)
            } else {
                alertEmptyForm()
            }
        }
    }

    private fun alertEmptyForm() {
        if (username.isBlank()) {
            binding.textInputLayoutUsername.helperText = "Must not be empty "
        }
        if (password.isBlank()) {
            binding.textInputLayoutPassword.helperText = "Must not be empty "
        }
        clearHelperText()
    }

    private fun validateForm(): Boolean {
        if (username.isNotBlank() && password.isNotBlank()) {
            clearHelperText()
            return true
        }
        return false
    }

    private fun clearHelperText() {
        if (username.isNotBlank()) {
            binding.textInputLayoutUsername.helperText = ""
        }
        if (password.isNotBlank()) {
            binding.textInputLayoutPassword.helperText = ""
        }
    }

    private fun onSignupButtonPressed() {
        binding.textViewSignUp.setOnClickListener {
            navigateToFragment(RegisterFragment())
        }
    }

    private fun assignUsernameAndPassword() {
        username = binding.textInputEditUsername.text.toString().trimEnd()
        password = binding.textInputEditPassword.text.toString().trimEnd()
    }

    private fun changeEndIconOfPasswordEditText() {
        binding.textInputLayoutPassword.setEndIconTintList(
            ColorStateList.valueOf(
                ContextCompat.getColor(
                    requireContext(), R.color.color_password_drawable
                )
            )
        )
    }

    override fun showLoading() {
        requireActivity().runOnUiThread {
            Handler(Looper.getMainLooper()).postDelayed({
                binding.progressLoading.visibility = View.VISIBLE
            }, 2000)
        }
    }

    override fun hideLoading() {
        binding.progressLoading.visibility = View.GONE
    }

    override fun onLoginSuccess() {
        navigateToFragment(AddTaskFragment())
    }

    override fun onLoginFailure(throwable: Throwable, statusCode: Int?, error: String?) {
        if (statusCode == 401) {
            requireActivity().runOnUiThread {
                AlertDialog.Builder(requireContext())
                    .setTitle(getString(R.string.invalid_account))
                    .setMessage(error)
                    .setPositiveButton(getString(R.string.try_again)) { _, _ ->
                        binding.textInputEditPassword.text!!.clear()
                        binding.textInputEditUsername.text!!.clear()
                    }.create().show()
            }
        } else {
            requireActivity().runOnUiThread {
                AlertDialog.Builder(requireContext())
                    .setTitle(getString(R.string.unable_to_login))
                    .setMessage(getString(R.string.network_error_message))
                    .setPositiveButton(getString(R.string.try_again)) { _, _ ->

                    }.create().show()
            }
        }
    }
}