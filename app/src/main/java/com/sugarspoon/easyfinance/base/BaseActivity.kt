package com.sugarspoon.easyfinance.base

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import com.sugarspoon.easyfinance.R
import kotlinx.android.synthetic.main.easy_toolbar.*

abstract class BaseActivity<Binding : ViewBinding> : AppCompatActivity() {

    val context: Context
        get() = this
    lateinit var binding: Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        binding = getViewBinding()
        setContentView(binding.root)
    }

    abstract fun getViewBinding(): Binding

    private fun setToolbar(title: String) {
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        setTitle(title)
    }

    fun setToolbar(
        title: String = "",
        displayHomeAsUpEnabled: Boolean = false
    ) {
        setToolbar(title)
        supportActionBar?.setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
        setTitle(title)
    }

    fun setEasyToolbar(
        title: String = ""
    ) {
        toolbarTextTitle.text = title
    }

    //MENU METHODS
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                //this is used because when user hits home button the previous view is reconstructed
                //and when back button (at navbar) is pressed this doesn't happen,
                //so this makes the previous view never reconstructed when home is hit.
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val EMPTY = ""
    }
}