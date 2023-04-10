package com.cupcake.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cupcake.todo.databinding.FragmentHomeBinding


class HomeFragment():BaseFragment<FragmentHomeBinding>(){
    override val LOG_TAG: String ="HomeFragment"
    override val bindingInflater: (LayoutInflater) -> FragmentHomeBinding =FragmentHomeBinding::inflate
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        bindingInflater(layoutInflater)
        return binding.root
    }

}