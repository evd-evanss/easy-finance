package com.sugarspoon.easyfinance.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.sugarspoon.easyfinance.utils.extensions.hideKeyboard
import com.sugarspoon.easyfinance.utils.extensions.showKeyboard
import com.sugarspoon.easyfinance.utils.extensions.showToast

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<Binding : ViewBinding>(
    private val inflate: Inflate<Binding>
) : Fragment() {

    lateinit var _binding: Binding
    val binding get() = _binding

    lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    //TOAST METHODS
    fun showToast(string: String) {
        activity?.showToast(string)
    }

    //KEYBOARD METHODS
    fun hideSoftKeyboard() {
        activity?.hideKeyboard()
    }

    fun showSoftKeyBoard() {
        activity?.showKeyboard()
    }

    fun handleOnBackPressed(
        enableHandler: Boolean,
        onBackPressedListener: (() -> Unit)? = null
    ) = requireActivity().run {
        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(enableHandler) {
                override fun handleOnBackPressed() {
                    onBackPressedListener?.invoke()
                }
            }
        )
    }
}
