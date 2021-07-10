package com.sugarspoon.easyfinance.ui.spending

import androidx.lifecycle.viewModelScope
import com.sugarspoon.easyfinance.base.BaseViewModel
import com.sugarspoon.easyfinance.data.local.entity.SpendingEntity
import com.sugarspoon.easyfinance.data.repository.LocalRepository
import com.sugarspoon.easyfinance.utils.extensions.orEmpty
import com.sugarspoon.easyfinance.utils.extensions.unMaskCurrency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpendingViewModel @Inject constructor(
    private val repository: LocalRepository
): BaseViewModel<SpendingIntent, SpendingState>() {

    private var spendingEntity = SpendingEntity()

    override fun handle(intent: SpendingIntent) {
        when(intent) {
            is SpendingIntent.ResumeSpending -> getSpending()
            is SpendingIntent.OnFillDescription -> setDescription(description = intent.description)
            is SpendingIntent.OnFillValue -> setValue(value = intent.value)
            is SpendingIntent.OnFillType -> setType(type = intent.type)
            is SpendingIntent.OnAddItemCLicked -> onAddItemClicked()
        }
    }

    private fun setDescription(description: String) {
        spendingEntity = spendingEntity.copy(description = description)
    }

    private fun setValue(value: Float) {
        spendingEntity = spendingEntity.copy(
            value = value
        )
    }

    private fun setType(type: String) {
        spendingEntity = spendingEntity.copy(type = type)
    }

    private fun onAddItemClicked() {
        if(spendingEntity.description.orEmpty().isEmpty()) {
            _state.value = SpendingState.DisplayDescriptionError
        } else if(spendingEntity.value.orEmpty() <= 0f) {
            _state.value = SpendingState.DisplayValueError
        } else if(spendingEntity.type.orEmpty().isEmpty()) {
            _state.value = SpendingState.DisplayTypeError
        } else {
            saveSpending(spendingEntity)
        }
    }

    private fun saveSpending(spending: SpendingEntity) = viewModelScope.launch {
        repository.insertSpending(spending)
        _state.value = SpendingState.Success
        getSpending()
    }

    private fun getSpending() = viewModelScope.launch {
        val spendingList = repository.getAllSpending()
        var total = 0f
        spendingList.forEach {
            total += it.value.orEmpty()
        }
        _state.value = SpendingState.DisplaySpending(value = total)
    }
}
