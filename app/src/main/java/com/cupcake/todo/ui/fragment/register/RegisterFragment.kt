package com.cupcake.todo.ui.fragment.register

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cupcake.todo.R
import com.cupcake.todo.databinding.FragmentRegisterBinding
import com.cupcake.todo.presenter.register.RegisterPresenter
import com.cupcake.todo.ui.base.BaseFragment
import com.cupcake.todo.ui.fragment.login.LoginFragment

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(), IRegisterView {
    override val LOG_TAG: String = this::class.java.name
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> FragmentRegisterBinding =
        FragmentRegisterBinding::inflate
    private lateinit var presenter: RegisterPresenter
    private lateinit var username: String
    private lateinit var password: String


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
        addCallBacks()
    }

    private fun init() {
        presenter = RegisterPresenter(this)
    }

    private fun addCallBacks() {
        onSignUpButtonPressed()
        onLoginTextPressed()
        setupBackButton()
    }

    private fun onSignUpButtonPressed() {
        binding.buttonSignUp.setOnClickListener {
            assignUsernameAndPassword()

            if (isValidInput()) {
                presenter.register(username, password)
                navigateToFragment(LoginFragment())
            } else {
                showInputErrorMessage()
            }
        }
    }

    private fun onLoginTextPressed() {
        binding.textViewLogin.setOnClickListener {
            navigateToFragment(LoginFragment())
        }
    }

    private fun setupBackButton() {
        binding.toolBar.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun clearHelperText() {
        if (username.isNotBlank()) {
            binding.containerUserName.helperText = ""
        }
        if (password.isNotBlank()) {
            binding.containerPassword.helperText = ""
        }
    }

    private fun isValidInput(): Boolean {
        return if(username.isNotBlank() && password.isNotBlank() &&
            isValidUsername(username) && isValidPassword(password)){
            clearHelperText()
            true
        } else false
    }

    private fun showInputErrorMessage() {
        if (username.isBlank()) {
            binding.containerUserName.helperText = getString(R.string.enter_user_name)
        } else if (!isValidUsername(username)) {
            binding.containerUserName.helperText = getString(R.string.user_name_requirment)
        }

        if (password.isBlank()) {
            binding.containerPassword.helperText = getString(R.string.enter_password)
        } else if (!isValidPassword(password)) {
            binding.containerPassword.helperText = getString(R.string.password_requirment)
        }
    }

    private fun assignUsernameAndPassword() {
        username = binding.textInputUsername.text.toString().trimEnd()
        password = binding.textInputPassword.text.toString().trimEnd()
    }

    private fun isValidUsername(username: String): Boolean {
        val pattern = "^[a-zA-Z\\d_-]{4,}$".toRegex()
        return pattern.matches(username)
    }

    private fun isValidPassword(password: String): Boolean {
        return password.length >= 8
    }

    override fun showLoading() {
        binding.progressLoading.postDelayed({
            binding.progressLoading.visibility = View.VISIBLE
        }, 2000)
    }

    override fun hideLoading() {
        binding.progressLoading.visibility = View.GONE
    }

    override fun onRegisterSuccess() {
        navigateToFragment(LoginFragment())
    }

    override fun onRegisterFailure(error: String) {
        val dialogBuilder = createDialog(
            getString(R.string.registration_failed),
            error,
            getString(R.string.ok)
        )
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