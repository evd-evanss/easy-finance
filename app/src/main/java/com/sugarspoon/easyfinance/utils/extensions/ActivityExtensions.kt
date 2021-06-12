package com.sugarspoon.easyfinance.utils.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.sugarspoon.easyfinance.R

//TOAST METHODS
fun Context.showToast(string: String) {
    Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
}

fun Context.showLongToast(string: String) {
    Toast.makeText(this, string, Toast.LENGTH_LONG).show()
}

//KEYBOARD
fun Activity.hideKeyboard() {
    currentFocus?.let {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
    }
}

fun Context.hideKeyboardFrom(view: View) {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.showKeyboard() {
    val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

fun Window.removeStatusBar(darkIcons: Boolean = false) {
    decorView.systemUiVisibility = if (darkIcons) {
        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    } else {
        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }
    statusBarColor = Color.TRANSPARENT
}

fun Window.showStatusBar(context: Context) {
    decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
    statusBarColor = ContextCompat.getColor(context, R.color.color_primary)
}