package com.damir.android.myscore.utils

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateUtil {

    companion object {
        private val formatFromApi = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
            .apply {
                timeZone = TimeZone.getTimeZone("GMT")
            }
        private val timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT)
        private val dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM)
        private val TBD = "TBD"

        fun getMatchTime(utcDate: String): String {
            return try {
                val date = formatFromApi.parse(utcDate)
                if(date != null) {
                    timeFormat.format(date)
                }else {
                    TBD
                }
            }catch (e: ParseException) {
                e.printStackTrace()
                TBD
            }
        }

        fun getMatchDate(utcDate: String): String {
            return try {
                val date = formatFromApi.parse(utcDate)
                if(date != null) {
                    dateFormat.format(date)
                }else {
                    TBD
                }
            }catch (e: ParseException) {
                e.printStackTrace()
                TBD
            }
        }
    }
}