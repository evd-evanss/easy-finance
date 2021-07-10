package com.sugarspoon.easyfinance.data.local.model

object Category {

    const val feeding = "Alimentação"
    const val education = "Educação"
    const val leizure = "Lazer"
    const val home = "Moradia"
    const val payments = "Pagamentos"
    const val clothes = "Roupas"
    const val transport = "Transportes"
    const val health = "Saúde"
    const val categoryTitle = "Selecione um tipo de despesa"

    val categorys: Array<String> = arrayOf(
        feeding,
        education,
        leizure,
        home,
        payments,
        clothes,
        transport,
        health
    )
}