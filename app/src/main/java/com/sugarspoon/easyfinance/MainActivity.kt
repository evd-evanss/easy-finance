package com.sugarspoon.easyfinance

import android.os.Bundle
import com.sugarspoon.easyfinance.base.BaseActivity
import com.sugarspoon.easyfinance.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolbar(
            title = getString(R.string.app_name),
            displayHomeAsUpEnabled = true
        )
    }

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)
}