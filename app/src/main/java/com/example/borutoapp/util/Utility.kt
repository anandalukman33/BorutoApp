package com.example.borutoapp.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Utility {

    fun parseMillis(millis: Long): String {
        val date = Date(millis)
        val format = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.ROOT)
        return format.format(date)
    }
}