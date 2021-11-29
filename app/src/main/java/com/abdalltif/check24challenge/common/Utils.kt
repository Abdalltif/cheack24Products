package com.abdalltif.check24challenge.common

object Utils {

    fun getDateTime(timestamp: Long): String? {
        val sdf = java.text.SimpleDateFormat("dd.MM.yyyy")
        val date = java.util.Date(timestamp * 1000)
        return  sdf.format(date)
    }

}