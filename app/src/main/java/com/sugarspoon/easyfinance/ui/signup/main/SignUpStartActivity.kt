package com.sugarspoon.easyfinance.ui.signup.main

import android.os.Bundle
import com.sugarspoon.easyfinance.base.BaseActivity
import com.sugarspoon.easyfinance.databinding.ActivityStartSignupBinding
import com.sugarspoon.easyfinance.utils.extensions.removeStatusBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpStartActivity : BaseActivity<ActivityStartSignupBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.removeStatusBar()
    }

    override fun getViewBinding() = ActivityStartSignupBinding.inflate(layoutInflater)
}