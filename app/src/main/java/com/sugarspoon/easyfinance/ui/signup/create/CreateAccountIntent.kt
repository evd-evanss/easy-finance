package com.sugarspoon.easyfinance.ui.signup.create

sealed class CreateAccountIntent {
    object OnCreateAccountClicked : CreateAccountIntent()
    object OpenSignupStartClicked : CreateAccountIntent()
    data class OnEmailTextChanged(val email: String) : CreateAccountIntent()
    data class OnPasswordTextChanged(val password: String) : CreateAccountIntent()
}
