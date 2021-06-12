package com.sugarspoon.easyfinance.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.sugarspoon.easyfinance.base.BaseActivity
import com.sugarspoon.easyfinance.databinding.ActivitySplashBinding
import com.sugarspoon.easyfinance.ui.signup.main.SignUpStartActivity
import com.sugarspoon.easyfinance.utils.extensions.removeStatusBar
import kotlinx.coroutines.delay

class SplashActivity: BaseActivity<ActivitySplashBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.removeStatusBar()
        lifecycleScope.launchWhenResumed {
            delay(2000)
            val intent = Intent(this@SplashActivity, SignUpStartActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun getViewBinding() = ActivitySplashBinding.inflate(layoutInflater)
}