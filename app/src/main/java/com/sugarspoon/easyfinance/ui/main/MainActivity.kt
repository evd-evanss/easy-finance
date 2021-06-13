package com.sugarspoon.easyfinance.ui.main

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.sugarspoon.easyfinance.R
import com.sugarspoon.easyfinance.base.BaseActivity
import com.sugarspoon.easyfinance.databinding.ActivityMainBinding
import com.sugarspoon.easyfinance.utils.extensions.removeStatusBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: BaseActivity<ActivityMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setView()
        window.removeStatusBar()
    }

    private fun setView() = binding.run {
        val navController = findNavController(R.id.mainHost)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            destination.label?.let { title ->
                setEasyToolbar(title = title.toString())
            }
        }
        mainBottomNavigation.setupWithNavController(navController)
    }

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)
}