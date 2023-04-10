package com.cupcake.todo.ui.fragment.login

import android.view.LayoutInflater
import android.view.ViewGroup
import com.cupcake.todo.databinding.FragmentLoginBinding
import com.cupcake.todo.ui.base.BaseFragment


class LoginFragment() : BaseFragment<FragmentLoginBinding>() {
    override val LOG_TAG: String = this::class.java.name
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> FragmentLoginBinding =
        FragmentLoginBinding::inflate

}