package com.example.bistroftchallenge.core.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object FunctionUtils {

    fun getTimeFromLong(timestamp: Long): String {
        val date = Date(timestamp)
        val format = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        return format.format(date)
    }

}
