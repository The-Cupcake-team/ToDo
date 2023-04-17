package com.cupcake.todo.ui.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.cupcake.todo.util.PrefsUtil

abstract class BaseFragment<VB : ViewBinding> : Fragment() {
    abstract val LOG_TAG: String
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB
    private var _binding: ViewBinding? = null
    protected val binding get() = _binding as VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.v("ameer", "int ${LOG_TAG}")
        _binding = bindingInflater(layoutInflater, container,false)
        PrefsUtil.initPrefsUtil(requireContext())
        return binding.root
    }

    protected fun log(value: String) {
        Log.v(LOG_TAG, value)
    }

}