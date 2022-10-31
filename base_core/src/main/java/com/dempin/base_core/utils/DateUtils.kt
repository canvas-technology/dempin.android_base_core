package com.dempin.base_core.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

object DateUtils {

    private const val THOUSAND_MILLIS = 1000L

    @SuppressLint("SimpleDateFormat")
    fun getCurrentDateForApiQuery():String{
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        return sdf.format(Date())
    }


    @SuppressLint("SimpleDateFormat")
    fun getFormattedDate(date:Date):String{
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        return sdf.format(date)
    }


    fun getCurrentDateForCalendar():Long{
        return System.currentTimeMillis() - THOUSAND_MILLIS
    }

}