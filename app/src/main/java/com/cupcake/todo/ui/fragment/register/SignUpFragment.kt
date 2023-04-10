package com.cupcake.todo.ui.fragment.register

import android.view.LayoutInflater
import android.view.ViewGroup
import com.cupcake.todo.databinding.FragmentRegisterBinding
import com.cupcake.todo.ui.base.BaseFragment

class SignUpFragment: BaseFragment<FragmentRegisterBinding>(){
    override val LOG_TAG: String = this::class.java.name
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> FragmentRegisterBinding
        = FragmentRegisterBinding::inflate

}