package com.sugarspoon.easyfinance.utils.extensions

import android.os.Build
import android.text.Html
import android.text.Spanned
import android.text.TextUtils
import android.widget.TextView
import androidx.annotation.StringRes
import com.sugarspoon.easyfinance.utils.Constants
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.*

fun String.isEmailValid(): Boolean {
    return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun Float.formatCurrencyBRL(trimRs: Boolean = false): String {
    val locale = Locale("pt", "BR")
    return if (trimRs) this.formatToCurrency(locale).replace("R$", "") else this.formatToCurrency(
        locale)
}

fun Float.formatToCurrency(locale: Locale): String {
    val currencyFormatter = NumberFormat.getCurrencyInstance(locale)
    return currencyFormatter.format(this.toDouble())
}

fun TextView.setHtmlText(@StringRes stringRes: Int) {
    setHtmlText(context.getString(stringRes))
}

fun TextView.setHtmlText(text: String) {
    this.text = text.fromHtml()
}

fun String.fromHtml(): Spanned = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
    Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT)
} else {
    Html.fromHtml(this)
}

fun String.unMaskCurrency(): Float {
    var unCurrency = replace("R$", "")
    unCurrency = unCurrency.trim()
    unCurrency = unCurrency.replace(",", ".")
    return unCurrency.toFloat()
}

fun Float.formatToCurrency(locale: Locale, nearestRounding: Boolean): String {
    val currencyFormatter = NumberFormat.getCurrencyInstance(locale).apply {
        roundingMode = if(nearestRounding) RoundingMode.HALF_UP else RoundingMode.FLOOR
    }
    return currencyFormatter.format(this.toBigDecimal())
}

fun Double.formatToCurrency(locale: Locale): String {
    val currencyFormatter = NumberFormat.getCurrencyInstance(locale)
    return currencyFormatter.format(this)
}

fun Float.formatCurrencyBRL(
    showCurrency: Boolean = true,
    nearestRounding: Boolean = false,
    valueWithDots: Boolean = false
): String {
    val locale = Locale(Constants.LANGUAGE_PT, Constants.COUNTRY_BR)
    val value = this.formatToCurrency(locale, nearestRounding)
    return if(valueWithDots) {
        if (showCurrency) value.replace(",", ".")
        else value.replace("R$", "").replace(",", ".").trimStart()
    } else {
        if (showCurrency) value else value.replace("R$", "")
    }
}


fun Long.formatCurrencyBRL(trimRs: Boolean = false): String {
    val locale = Locale(Constants.LANGUAGE_PT, Constants.COUNTRY_BR)
    val string = (this.toDouble() / 100.0).formatToCurrency(locale)
    return if (trimRs) string.replace("R$", "") else string
}

fun Float?.orEmpty() = this ?: 0f