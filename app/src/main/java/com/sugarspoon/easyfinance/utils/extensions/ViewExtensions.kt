package com.sugarspoon.easyfinance.utils.extensions

import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleCoroutineScope
import com.google.android.material.textfield.TextInputLayout
import com.sugarspoon.easyfinance.utils.CurrencyMask
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

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

fun EditText.addCurrencyMask(
    displayCurrency: Boolean = false,
    maxValueInCents: Long = CurrencyMask.MAX_VALUE,
    afterTextChanged: ((Float) -> Unit)? = null
) {
    CurrencyMask.insert(Locale("pt", "BR"), this, displayCurrency, maxValueInCents, afterTextChanged)
}

/* Positions: DRAWABLE_LEFT = 0 DRAWABLE_TOP = 1 DRAWABLE_RIGHT = 2 DRAWABLE_BOTTOM = 3 */
fun EditText.onDrawableClick(onClickListener: (View) -> Unit, position: Int = 2) {
    val editText = this
    setOnTouchListener(object : View.OnTouchListener {
        override fun onTouch(v: View?, event: MotionEvent): Boolean {
            if (event.action == MotionEvent.ACTION_UP && editText.compoundDrawables[position] != null) {
                if (event.rawX >= editText.right - editText.compoundDrawables[position].bounds.width()) {
                    onClickListener(editText)
                    return true
                }
            }
            return false
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

fun TextInputLayout.clear() {
    editText?.setText("")
}

fun TextInputLayout.displayErrorMsg(msg: String, errorColor: Int? = null) {
    isErrorEnabled = true
    if (errorColor != null) {
        editText?.setTextColor(ContextCompat.getColor(context, errorColor))
    }
    error = msg
    invalidate()
}


fun View.setVisible(
    visible: Boolean,
    useInvisible: Boolean = false,
) {
    visibility = when {
        visible -> View.VISIBLE
        useInvisible -> View.INVISIBLE
        else -> View.GONE
    }
}
