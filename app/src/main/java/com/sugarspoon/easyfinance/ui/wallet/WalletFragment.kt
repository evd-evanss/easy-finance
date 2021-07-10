package com.sugarspoon.easyfinance.ui.wallet

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.sugarspoon.easyfinance.R
import com.sugarspoon.easyfinance.base.BaseFragment
import com.sugarspoon.easyfinance.databinding.FragmentWalletBinding
import com.sugarspoon.easyfinance.utils.CurrencyMask
import com.sugarspoon.easyfinance.utils.extensions.addCurrencyMask
import com.sugarspoon.easyfinance.utils.extensions.formatCurrencyBRL
import com.sugarspoon.easyfinance.utils.extensions.onDrawableClick
import com.sugarspoon.easyfinance.utils.extensions.setHtmlText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WalletFragment: BaseFragment<FragmentWalletBinding>(
    FragmentWalletBinding::inflate
) {

    private val viewModel : WalletViewModel by viewModels()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setObservers()
        setViews()
    }

    private fun setViews() = binding.run {
        viewModel.handle(WalletIntent.ResumeData)
    }

    private fun setListeners() = binding.run {
        walletAdd10Tv.setOnClickListener {
            viewModel.handle(WalletIntent.OnValueAdd(value = TEN))
        }
        walletAdd50Tv.setOnClickListener {
            viewModel.handle(WalletIntent.OnValueAdd(value = FIFTY))
        }
        walletAdd100Tv.setOnClickListener {
            viewModel.handle(WalletIntent.OnValueAdd(value = HUNDRED))
        }
        walletAdd1000Tv.setOnClickListener {
            viewModel.handle(WalletIntent.OnValueAdd(value = THOUSAND))
        }
        walletAddMoneyBt.setOnClickListener {
            viewModel.handle(WalletIntent.AddMoneyClicked(value = getValue()))
        }
        walletClearMoneyBt.setOnClickListener {
            viewModel.handle(WalletIntent.ClearMoneyClicked)
        }
        walletAddBalanceTil.editText?.addCurrencyMask(displayCurrency = true)
        walletAddBalanceTil.editText?.onDrawableClick(
            onClickListener = {
                viewModel.handle(WalletIntent.ClearMoneyClicked)
            }
        )
    }

    private fun getValue() = CurrencyMask.parseValue(
        CurrencyMask.unmask(
            binding.walletAddBalanceTil.editText?.text.toString()
        )
    )

    private fun setObservers() = viewModel.state.observe(viewLifecycleOwner) { state ->
        when(state) {
            is WalletStates.SetValue -> setValue(value = state.value)
        }
    }

    private fun setValue(value: Float) = binding.run {
        walletAddBalanceTil.editText?.setText(value.formatCurrencyBRL())
        walletBalanceTv.setHtmlText(getString(R.string.balance, value.formatCurrencyBRL()))
    }

    companion object {
        private const val TEN = 10f
        private const val FIFTY = 50f
        private const val HUNDRED = 100f
        private const val THOUSAND = 1000f
        private const val DEFAULT = 0f
    }
}