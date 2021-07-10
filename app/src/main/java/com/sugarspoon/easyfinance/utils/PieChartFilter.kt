package com.sugarspoon.easyfinance.utils

import android.graphics.Color
import com.github.mikephil.charting.data.PieEntry
import com.sugarspoon.easyfinance.R
import com.sugarspoon.easyfinance.data.local.entity.SpendingEntity
import com.sugarspoon.easyfinance.data.local.model.Category

class PieChartFilter {

    fun filterByCategory(
        list: List<SpendingEntity>,
    ): List<PieEntry> {
        var cont = 0
        val entries = mutableListOf<PieEntry>()
        list.filter {
            it.type == Category.payments
        }.size.run {
            if (this > 0) {
                entries.add(cont, PieEntry(this.toFloat()))
                cont++
            }
        }
        list.filter {
            it.type == Category.transport
        }.size.run {
            if (this > 0) {
                entries.add(cont, PieEntry(this.toFloat()))
                cont++
            }
        }
        list.filter {
            it.type == Category.feeding
        }.size.run {
            if (this > 0) {
                entries.add(cont, PieEntry(this.toFloat()))
                cont++
            }
        }
        list.filter {
            it.type == Category.clothes
        }.size.run {
            if (this > 0) {
                entries.add(cont, PieEntry(this.toFloat()))
                cont++
            }
        }
        list.filter {
            it.type == Category.education
        }.size.run {
            if (this > 0) {
                entries.add(1, PieEntry(this.toFloat()))
                cont++
            }
        }
        list.filter {
            it.type == Category.health
        }.size.run {
            if (this > 0) {
                entries.add(cont, PieEntry(this.toFloat()))
                cont++
            }
        }
        list.filter {
            it.type == Category.home
        }.size.run {
            if (this > 0) {
                entries.add(cont, PieEntry(this.toFloat()))
                cont++
            }
        }
        list.filter {
            it.type == Category.leizure
        }.size.run {
            if (this > 0) {
                entries.add(cont, PieEntry(this.toFloat()))
                cont++
            }
        }
        return entries
    }
}