package com.cupcake.todo.signUpFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.cupcake.todo.BaseFragment
import com.cupcake.todo.databinding.FragmentSignUpBinding

class SignUpFragment: BaseFragment<FragmentSignUpBinding>(){
    override val LOG_TAG: String = this::class.java.name
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> FragmentSignUpBinding
        = FragmentSignUpBinding::inflate

}