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

    private lateinit var dialogBuilder: AlertDialog.Builder
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
            binding.textInputLayoutUsername.helperText = getString(R.string.empty_form)
        }
        if (password.isBlank()) {
            binding.textInputLayoutPassword.helperText = getString(R.string.empty_form)
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
        binding.progressLoading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progressLoading.visibility = View.GONE
    }

    override fun onLoginSuccess() {
        navigateToFragment(AddTaskFragment())
    }

    override fun onLoginFailure(throwable: Throwable, statusCode: Int?, error: String?) {
        Log.v("Tarek","status = $statusCode , error = $error")
        dialogBuilder = if (statusCode == 401) {
            createDialog(
                getString(R.string.invalid_account),
                error!!,
                getString(R.string.try_again)
            )
        } else {
            createDialog(
                getString(R.string.unable_to_login),
                getString(R.string.network_error_message),
                getString(R.string.try_again)
            )
        }
        requireActivity().runOnUiThread {
            Handler(Looper.getMainLooper()).post { dialogBuilder.create().show() }
        }
    }

    private fun createDialog(
        title: String,
        message: String,
        positiveButton: String
    ): AlertDialog.Builder {
        return AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveButton) { _, _ -> }
    }
}