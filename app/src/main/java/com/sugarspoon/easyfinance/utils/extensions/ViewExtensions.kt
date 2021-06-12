package com.sugarspoon.easyfinance.utils.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleCoroutineScope
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun EditText.afterTextChanged(onTextChanged: ((String) -> Unit)) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            onTextChanged(s.toString())
        }

        override fun beforeTextChanged(
            s: CharSequence?,
            start: Int,
            count: Int,
            after: Int,
        ) {
        }

        override fun onTextChanged(
            query: CharSequence?,
            start: Int,
            before: Int,
            count: Int,
        ) {
        }
    })
}

fun TextInputLayout.clearError() {
    isErrorEnabled = false
}

fun TextInputLayout.displayMessageError(
    message: String,
    delay: Long = 1000L,
    lifecycleScope: LifecycleCoroutineScope
) = lifecycleScope.launch {
    displayErrorMsg(message)
    delay(delay)
    clearError()
}

fun TextInputLayout.displayErrorMsg(msg: String, errorColor: Int? = null) {
    isErrorEnabled = true
    if (errorColor != null) {
        editText?.setTextColor(ContextCompat.getColor(context, errorColor))
    }
    error = msg
    invalidate()
}