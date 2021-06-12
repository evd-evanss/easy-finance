package com.sugarspoon.easyfinance.ui.signup.create

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sugarspoon.easyfinance.base.BaseFragment
import com.sugarspoon.easyfinance.databinding.FragmentCreateAccountBinding
import com.sugarspoon.easyfinance.utils.extensions.afterTextChanged
import com.sugarspoon.easyfinance.utils.extensions.displayMessageError
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateAccountFragment : BaseFragment<FragmentCreateAccountBinding>(
    FragmentCreateAccountBinding::inflate
) {

    private val viewModel: CreateAccountViewModel by viewModels()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setObservers()
    }

    private fun setListeners() = binding.run {
        createAccountNotHasAccountTv.setOnClickListener {
            viewModel.handle(CreateAccountIntent.OpenSignupStartClicked)
        }
        createAccountEmailTil.editText?.afterTextChanged {
            viewModel.handle(CreateAccountIntent.OnEmailTextChanged(it))
        }
        createAccountPasswordTil.editText?.afterTextChanged {
            viewModel.handle(CreateAccountIntent.OnPasswordTextChanged(it))
        }
        createAccountActionCreateBt.setOnClickListener {
            viewModel.handle(CreateAccountIntent.OnCreateAccountClicked)
        }
    }

    private fun setObservers() = viewModel.state.observe(viewLifecycleOwner) { state ->
        when (state) {
            is CreateAccountStates.OpenSignupStart -> openSignUpStart()
            is CreateAccountStates.DisplayEmailError -> displayEmailError(state.error)
            is CreateAccountStates.DisplayPasswordError -> displayPasswordError(state.error)
        }
    }

    private fun displayEmailError(error: String) = binding.run {
        createAccountEmailTil.displayMessageError(
            message = error,
            lifecycleScope = lifecycleScope
        )
    }

    private fun displayPasswordError(error: String) = binding.run {
        createAccountPasswordTil.displayMessageError(
            message = error,
            lifecycleScope = lifecycleScope
        )
    }

    private fun openSignUpStart() = findNavController().run {
        navigate(CreateAccountFragmentDirections.toSignupStart())
    }
}