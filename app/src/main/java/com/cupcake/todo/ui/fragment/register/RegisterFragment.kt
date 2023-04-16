package com.cupcake.todo.ui.fragment.register


import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.cupcake.todo.R
import com.cupcake.todo.databinding.FragmentRegisterBinding
import com.cupcake.todo.presenter.register.RegisterPresenter
import com.cupcake.todo.ui.base.BaseFragment
import com.cupcake.todo.ui.fragment.login.LoginFragment
import com.google.android.material.snackbar.Snackbar
import com.cupcake.todo.ui.fragment.team_tasks.TeamTasksFragment

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
        validateForm()
    }

    private fun onSignUpButtonPressed() {
        binding.buttonSignUp.setOnClickListener {
            username = binding.textInputUsername.text.toString().trimEnd()
            password = binding.textInputPassword.text.toString().trimEnd()

            if (isValidInput(username, password)) {
                presenter.register(username, password)
                onRegisterSuccess()
            } else {
                showInputErrorMessage(username, password)
            }
        }
    }

    private fun validateForm() {
        binding.textInputUsername.doOnTextChanged { text, _, _, _ ->
            if (text.isNullOrBlank()) {
                binding.containerUserName.helperText = getString(R.string.enter_user_name)
            } else if (!isValidUsername(text.toString())) {
                binding.containerUserName.helperText = getString(R.string.user_name_requirment)
            } else {
                binding.containerUserName.helperText = null
            }
        }

        binding.textInputPassword.doOnTextChanged { text, _, _, _ ->
            if (text.isNullOrBlank()) {
                binding.containerPassword.helperText = getString(R.string.enter_password)
            } else if (!isValidPassword(text.toString())) {
                binding.containerPassword.helperText = getString(R.string.password_requirment)
            } else {
                binding.containerPassword.helperText = null
            }
        }
    }

    private fun onLoginTextPressed() {
        binding.textViewLogin.setOnClickListener {
            this.navigateWithAddFragment(LoginFragment())
        }
    }
    private fun navigateToFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction().apply {
            add(R.id.fragmentContainer, fragment)
            addToBackStack(fragment.javaClass.simpleName)
            commit()
        }
    }

    private fun setupBackButton() {
        binding.toolBar.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
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
        val isValidUsername = username.isNotBlank() && isValidUsername(username)
        val isValidPassword = password.isNotBlank() && isValidPassword(password)
        return isValidUsername && isValidPassword
    }

    private fun isValidUsername(username: String): Boolean {
        val pattern = "^[a-zA-Z\\d_-]{4,}$".toRegex()
        return pattern.matches(username)
    }

    private fun isValidPassword(password: String): Boolean {
        return password.length >= 8
    }


    private fun showSuccessMessage() {
        val snackBar = Snackbar.make(
            requireView(),
            getString(R.string.register_successful),
            Snackbar.LENGTH_SHORT
        )
        val snackBarView = snackBar.view
        snackBarView.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.green))
        snackBar.show()
    }

    override fun showLoading() {
        binding.progressLoading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progressLoading.visibility = View.INVISIBLE
    }

    override fun onRegisterSuccess() {
        showSuccessMessage()
        this.navigateWithReplaceFragment(LoginFragment())
        Log.v(LOG_TAG, "onRegisterSuccess")
    }

    override fun onRegisterFailure(error: String) {

    }


    private fun Fragment.navigateWithReplaceFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, fragment)
            commit()
        }
    }

    private fun Fragment.navigateWithAddFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction().apply {
            add(R.id.fragmentContainer, fragment)
            addToBackStack(fragment::class.java.name)
            commit()
        }
    }


}