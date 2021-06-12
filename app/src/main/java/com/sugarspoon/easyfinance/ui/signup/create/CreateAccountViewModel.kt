package com.sugarspoon.easyfinance.ui.signup.create

import com.sugarspoon.easyfinance.base.BaseViewModel
import com.sugarspoon.easyfinance.utils.extensions.isEmailValid
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateAccountViewModel @Inject constructor() : BaseViewModel<CreateAccountIntent, CreateAccountStates>() {

    private var email: String = ""
    private var password: String = ""

    override fun handle(intent: CreateAccountIntent) {
        when(intent) {
            is CreateAccountIntent.OpenSignupStartClicked -> openSignUpStart()
            is CreateAccountIntent.OnEmailTextChanged -> setEmail(intent.email)
            is CreateAccountIntent.OnPasswordTextChanged -> setPassword(intent.password)
            is CreateAccountIntent.OnCreateAccountClicked -> validateFields()
        }
    }

    private fun setEmail(email: String) {
        this.email = email
    }

    private fun setPassword(password: String) {
        this.password = password
    }

    private fun validateFields() {
        if (!validateEmail()) {
            _state.value = CreateAccountStates.DisplayEmailError(error = EMAIL_ERROR)
        }
        if (!validatePassword()) {
            _state.value = CreateAccountStates.DisplayPasswordError(error = PASSWORD_ERROR)
        }
        if (validatePassword() and validateEmail()) {

        }
    }

    private fun validateEmail() = email.isEmailValid()

    private fun validatePassword() = password.length == 8

    private fun openSignUpStart() {
        _state.value = CreateAccountStates.OpenSignupStart
    }

    companion object {
        private const val EMAIL_ERROR = "Email inválido"
        private const val PASSWORD_ERROR = "Senha inválida"
    }
}