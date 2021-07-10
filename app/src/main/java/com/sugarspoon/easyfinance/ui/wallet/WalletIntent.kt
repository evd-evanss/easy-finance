package com.sugarspoon.easyfinance.ui.wallet

sealed class WalletIntent {
    object ClearMoneyClicked : WalletIntent()
    object ResumeData : WalletIntent()
    data class AddMoneyClicked(val value: Float) : WalletIntent()
    data class OnValueAdd(val value: Float) : WalletIntent()
}
