package com.sugarspoon.easyfinance.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.text.NumberFormat
import java.util.*

object CurrencyMask {

    private const val IN_CENTS = 100L
    private const val TRILLION = 1000L * 1000 * 1000 * 1000
    private const val QUADRILLION = 1000L * 1000 * 1000 * 1000 * 1000
    const val MAX_VALUE = 1L * QUADRILLION * IN_CENTS

    fun unmask(string: String): String {
        return string.replace("[^0-9]".toRegex(), "")
    }

    fun parseValue(string: String): Float {
        return unmask(string).toFloat() / 100
    }

    fun insert(
            locale: Locale,
            editText: EditText,
            displayCurrency: Boolean,
            maxValue: Long,
            afterChange: ((t: Float) -> Unit)? = null
    ) {
        editText.addTextChangedListener(object : TextWatcher {
            var isUpdating: Boolean = false
            var old = ""

            override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int,
                                       count: Int) {
                if (isUpdating) {
                    isUpdating = false
                    return
                }

                val string = charSequence.toString()

                if (string != old) {
                    setNewValue(string)
                }

                // is erasing text
                if (old.length > string.length && string.isNotEmpty()) {
                    old = string
                    return
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun afterTextChanged(s: Editable) {
                if (editText.text.toString() == "0,00" && s.toString() == "0,0") {
                    setNewValue(editText.text.toString())
                }
            }

            private fun setNewValue(string: String) {
                isUpdating = true
                val cleanString = unmask(string)

                try {
                    var parsed = cleanString.toDouble()
                    if (parsed > maxValue) parsed = (parsed - (parsed % 10)) / 10
                    var formatted = NumberFormat.getCurrencyInstance(locale).format(parsed / 100)

                    if (!displayCurrency)
                        formatted = formatted.replace("[^0-9,.]".toRegex(), "")

                    old = formatted
                    editText.setText(formatted)
                    editText.setSelection(editText.text.length)

                    afterChange?.invoke(parseValue(formatted))
                } catch (e: NumberFormatException) {
                    e.printStackTrace()
                }
            }
        })
    }
}