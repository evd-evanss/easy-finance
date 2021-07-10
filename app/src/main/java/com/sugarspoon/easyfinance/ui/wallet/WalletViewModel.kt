package com.sugarspoon.easyfinance.ui.wallet

import androidx.lifecycle.viewModelScope
import com.sugarspoon.easyfinance.base.BaseViewModel
import com.sugarspoon.easyfinance.data.local.entity.RevenueEntity
import com.sugarspoon.easyfinance.data.repository.LocalRepository
import com.sugarspoon.easyfinance.utils.extensions.orEmpty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(
    private val repository: LocalRepository
): BaseViewModel<WalletIntent, WalletStates>() {

    private var balance = 0f
    private var revenueEntity: RevenueEntity? = null

    init {
        initRevenue()
    }

    private fun initRevenue() = viewModelScope.launch {
        revenueEntity = repository.getRevenue(0)
        if (revenueEntity == null) {
            revenueEntity = RevenueEntity(
                id = 0,
                value = balance
            )
            repository.setValue(revenueEntity!!)
        }
    }

    override fun handle(intent: WalletIntent) {
        when(intent) {
            is WalletIntent.ResumeData -> getRevenue()
            is WalletIntent.OnValueAdd -> setValue(value = intent.value)
            is WalletIntent.AddMoneyClicked -> setValue(value = intent.value, replace = false)
            is WalletIntent.ClearMoneyClicked -> clearValue()
        }
    }

    private fun setValue(
        value: Float,
        replace: Boolean = false
    ) {
        if (replace) {
            balance = value
            saveRevenue()
        } else {
            balance += value
            saveRevenue()
        }
        getRevenue()
    }

    private fun saveRevenue() = viewModelScope.launch {
        repository.updateValue(0, balance)
    }

    private fun getRevenue() = viewModelScope.launch {
        revenueEntity = repository.getRevenue(id = 0)
        _state.value = WalletStates.SetValue(value = revenueEntity?.value.orEmpty())
    }

    private fun clearValue() = viewModelScope.launch {
        balance = 0f
        repository.updateValue(0, balance)
        _state.value = WalletStates.SetValue(value = balance)
    }
}