package com.sugarspoon.easyfinance.ui.wallet

sealed class WalletStates {
    data class SetValue(val value: Float) : WalletStates()
}
