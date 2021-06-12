package com.sugarspoon.easyfinance.ui.signup.start

import com.sugarspoon.easyfinance.base.BaseFactory
import com.sugarspoon.easyfinance.base.BaseViewModel

class SignUpStartViewModel : BaseViewModel<StartIntent, StartStates>() {

    override fun handle(intent: StartIntent) {

    }

    class Factory : BaseFactory(
        block = {
            SignUpStartViewModel()
        }
    )
}