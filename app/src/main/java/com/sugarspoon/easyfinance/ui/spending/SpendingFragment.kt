package com.sugarspoon.easyfinance.ui.spending

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.sugarspoon.easyfinance.R
import com.sugarspoon.easyfinance.base.BaseFragment
import com.sugarspoon.easyfinance.data.local.model.Category
import com.sugarspoon.easyfinance.data.local.model.CategorySpending
import com.sugarspoon.easyfinance.databinding.FragmentSpendingBinding
import com.sugarspoon.easyfinance.utils.CurrencyMask
import com.sugarspoon.easyfinance.utils.extensions.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpendingFragment : BaseFragment<FragmentSpendingBinding>(
    FragmentSpendingBinding::inflate
) {

    private val viewModel: SpendingViewModel by viewModels()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        setListeners()
        setViews()
    }

    private fun setViews() = binding.run {
        val items = CategorySpending.CategoryOption.getTitles()
        val adapter = ArrayAdapter(requireContext(), R.layout.list_spending, items)
        menuAutocomplete.setAdapter(adapter)
        menuAutocomplete.setText(Category.categoryTitle, false)
        spendingValueTil.editText?.setText(DEFAULT_VALUE)
        viewModel.handle(SpendingIntent.ResumeSpending)
    }

    private fun setObservers() = viewModel.state.observe(viewLifecycleOwner) { state ->
        when (state) {
            is SpendingState.DisplaySpending -> displaySpending(total = state.value)
            is SpendingState.DisplayDescriptionError -> displayDescriptionError()
            is SpendingState.DisplayTypeError -> displayTypeError()
            is SpendingState.DisplayValueError -> displayValueError()
            is SpendingState.Success -> displaySuccess()
        }
    }

    private fun setListeners() = binding.run {
        spendingDescriptionTil.editText?.afterTextChanged {
            viewModel.handle(SpendingIntent.OnFillDescription(description = it))
        }

        spendingValueTil.editText?.afterTextChanged {
            viewModel.handle(SpendingIntent.OnFillValue(value = CurrencyMask.parseValue(it)))
        }

        spendingValueTil.editText?.addCurrencyMask(displayCurrency = true)

        menuAutocomplete.setOnItemClickListener { parent, view, position, id ->
            val type: String = menuAutocomplete.adapter.getItem(position) as String
            viewModel.handle(SpendingIntent.OnFillType(type = type))
        }

        spendingAddBt.setOnClickListener {
            viewModel.handle(SpendingIntent.OnAddItemCLicked)
        }
    }

    private fun displaySpending(total: Float) = binding.run {
        spendingTv.text = getString(R.string.label_spending, total.formatCurrencyBRL(true))
    }

    private fun displayDescriptionError() = binding.spendingDescriptionTil.displayMessageError(
        message = getString(R.string.error_description),
        lifecycleScope = lifecycleScope
    )

    private fun displayValueError() = binding.spendingValueTil.displayMessageError(
        message = getString(R.string.error_value),
        lifecycleScope = lifecycleScope
    )

    private fun displayTypeError() = binding.spendingTypeTil.displayMessageError(
        message = getString(R.string.error_type),
        lifecycleScope = lifecycleScope
    )

    private fun displaySuccess() {
        showToast(SUCCESS_MESSAGE)
        clear()
    }

    private fun clear() = binding.run {
        spendingDescriptionTil.clear()
        spendingValueTil.editText?.setText("0.00")
        spendingTypeTil.clear()
        menuAutocomplete.setText(Category.categoryTitle, false)
    }

    companion object {
        private const val DEFAULT_VALUE = "0"
        private const val SUCCESS_MESSAGE = "Despesa incluída com sucesso"
    }
}