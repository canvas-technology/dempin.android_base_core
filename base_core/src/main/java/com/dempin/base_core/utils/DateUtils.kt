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
        return try {
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            sdf.format(date)
        }catch (ex:Exception){
            ""
        }
    }


    @SuppressLint("SimpleDateFormat")
    fun getFormattedDate(date:Date,format:String):String{
        return try{
            val sdf = SimpleDateFormat(format)
            sdf.format(date)
        }catch (ex:Exception){
            ""
        }
    }

    fun getCurrentDateForCalendar():Long{
        return System.currentTimeMillis() - THOUSAND_MILLIS
    }

}