package com.cupcake.todo.ui.fragment.register


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
            username = binding.textInputUsername.text.toString().trimEnd()
            password = binding.textInputPassword.text.toString().trimEnd()

            if (isValidInput(username, password)) {
                presenter.register(username, password)
                showSuccessMessage()
                navigateToFragment(LoginFragment())
            } else {
                showInputErrorMessage(username, password)
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
            binding.containerUserName.helperText = null
        }
        if (password.isNotBlank()) {
            binding.containerPassword.helperText = null
        }
    }

    private fun showInputErrorMessage(username: String, password: String) {
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


    private fun isValidInput(username: String, password: String): Boolean {
        if (username.isBlank() || !isValidUsername(username)) {
            clearHelperText()
            return false
        }
        if (password.isBlank() || !isValidPassword(password)) {
            clearHelperText()
            return false
        }
        return true
    }

    private fun isValidUsername(username: String): Boolean {
        val pattern = "^[a-zA-Z\\d_-]{4,}$".toRegex()
        return pattern.matches(username)
    }

    private fun isValidPassword(password: String): Boolean {
        return password.length >= 8
    }


    private fun showSuccessMessage() {
        Toast.makeText(context, "Registration successful! \u2714", Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        binding.progressLoading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progressLoading.visibility = View.INVISIBLE
    }

    override fun onRegisterSuccess() {
        navigateToFragment(LoginFragment())
    }

    override fun onRegisterFailure(error: String) {

    }


}