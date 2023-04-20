package com.cupcake.todo.ui.fragment.login

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.cupcake.todo.R
import com.cupcake.todo.databinding.FragmentLoginBinding
import com.cupcake.todo.presenter.login.LoginPresenter
import com.cupcake.todo.ui.base.BaseFragment
import com.cupcake.todo.ui.fragment.home.HomeFragment
import com.cupcake.todo.ui.fragment.register.RegisterFragment
import com.cupcake.todo.util.PrefsUtil
import com.google.android.material.snackbar.Snackbar


class LoginFragment : BaseFragment<FragmentLoginBinding>(), ILoginView {
    override val LOG_TAG: String = this::class.java.name
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding =
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
        onSignupButtonPressed()
        onFormTextChange()
    }

    private fun onFormTextChange() {
        binding.textInputEditUsername.doOnTextChanged { _, _, _, _ ->
            binding.textInputLayoutUsername.helperText = null
        }
        binding.textInputEditPassword.doOnTextChanged { _, _, _, _ ->
            binding.textInputLayoutPassword.helperText = null
        }
    }

    private fun onLoginButtonPressed() {
        binding.buttonLogin.setOnClickListener {
            assignUsernameAndPassword()
            if (checkFormsNotEmpty()) {
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
    }

    private fun checkFormsNotEmpty() = username.isNotBlank() && password.isNotBlank()

    private fun onSignupButtonPressed() {
        binding.textViewSignUp.setOnClickListener {
            this.navigateWithAddFragment(RegisterFragment())
        }
    }

    private fun assignUsernameAndPassword() {
        username = binding.textInputEditUsername.text.toString().trimEnd()
        password = binding.textInputEditPassword.text.toString().trimEnd()
    }


    override fun showLoading() {
        binding.progressLoading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progressLoading.visibility = View.INVISIBLE
    }

    override fun onLoginSuccess() {
        activity?.runOnUiThread {
            saveUserNameToSharedPref()
            this.navigateWithReplaceFragment(HomeFragment())
        }
    }

    private fun saveUserNameToSharedPref(){
        var  userName = username.split("_").first()
        userName = userName[0].uppercase() + userName.substring(1)
        PrefsUtil.userName = userName
    }

    override fun onLoginFailure(throwable: Throwable, statusCode: Int?, error: String?) {
        if (statusCode == 401) {
            requireActivity().runOnUiThread {
                Snackbar.make(
                    binding.loginScreen,
                    getString(R.string.invalid_username_password),
                    Snackbar.LENGTH_LONG
                ).show()
            }
        } else {
            dialogBuilder = AlertDialog.Builder(requireContext())
                .setTitle(getString(R.string.unable_to_login))
                .setMessage(getString(R.string.network_error_message))
                .setPositiveButton(getString(R.string.network_error_message)) { _, _ -> }

            requireActivity().runOnUiThread {
                Handler(Looper.getMainLooper()).post { dialogBuilder.create().show() }
            }
        }
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