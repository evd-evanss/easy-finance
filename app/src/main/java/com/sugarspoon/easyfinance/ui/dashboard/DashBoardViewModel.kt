package com.sugarspoon.easyfinance.ui.dashboard

import com.sugarspoon.easyfinance.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashBoardViewModel @Inject constructor() : BaseViewModel<DashBoardIntent, DashBoardStates>() {

    private var email: String = ""
    private var password: String = ""

    override fun handle(intent: DashBoardIntent) {
        when(intent) {

        }
    }

}