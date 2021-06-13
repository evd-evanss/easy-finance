package com.sugarspoon.easyfinance.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sugarspoon.easyfinance.base.BaseFragment
import com.sugarspoon.easyfinance.databinding.FragmentCreateAccountBinding
import com.sugarspoon.easyfinance.databinding.FragmentDashboardBinding
import com.sugarspoon.easyfinance.utils.extensions.afterTextChanged
import com.sugarspoon.easyfinance.utils.extensions.displayMessageError
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashBoardFragment : BaseFragment<FragmentDashboardBinding>(
    FragmentDashboardBinding::inflate
) {

    private val viewModel: DashBoardViewModel by viewModels()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setObservers()
    }

    private fun setListeners() = binding.run {

    }

    private fun setObservers() = viewModel.state.observe(viewLifecycleOwner) { state ->
        when (state) {

        }
    }
}