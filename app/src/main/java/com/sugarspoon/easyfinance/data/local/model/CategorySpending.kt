package com.sugarspoon.easyfinance.data.local.model

import com.sugarspoon.easyfinance.R

enum class CategorySpending(
    val color: Int,
    val title: String
){
    FEEDING(color = R.color.colorBlue, title = "Alimentação"),
    EDUCATION(color = R.color.colorBlue, title = "Educação"),
    LEISURE(color = R.color.colorBlue, title = "Lazer"),
    HOME(color = R.color.colorBlue, title = "Moradia"),
    PAYMENTS(color = R.color.colorBlue, title = "Pagamentos"),
    CLOTHES(color = R.color.colorBlue, title = "Roupas"),
    TRANSPORT(color = R.color.colorBlue, title = "Transportes"),
    HEALTH(color = R.color.colorBlue, title = "Saúde");

    object CategoryOption {

        private val list = values()

        fun getTitles() : List<String> {
            val titles = mutableListOf<String>()
            list.forEach { titles.add(it.title) }
            return titles
        }

        fun getColorByType(type: String) = list.first {
            it.title == type
        }.color
    }

}