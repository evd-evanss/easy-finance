package com.sugarspoon.easyfinance.ui.signup.start

import android.os.Bundle
import android.view.View
import com.sugarspoon.easyfinance.base.BaseFragment
import com.sugarspoon.easyfinance.databinding.FragmentStartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpStartFragment : BaseFragment<FragmentStartBinding>(
    FragmentStartBinding::inflate
) {

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
    }
}