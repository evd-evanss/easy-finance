package com.sugarspoon.easyfinance.ui.signup.create

sealed class CreateAccountStates {
    data class DisplayEmailError(val error: String) : CreateAccountStates()
    data class DisplayPasswordError(val error: String) : CreateAccountStates()
    object OpenSignupStart : CreateAccountStates()
}
