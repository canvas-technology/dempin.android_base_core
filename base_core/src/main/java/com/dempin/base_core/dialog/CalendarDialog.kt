package com.dempin.base_core.dialog

import android.app.Activity
import android.app.DatePickerDialog
import android.widget.DatePicker
import java.util.Calendar

object CalendarDialog {

    fun openCalendar(
        activity: Activity,
        year:Int,
        month:Int,
        dayOfMonth:Int,
        minDate: Long = -1,
        callBack: (Calendar) -> Unit) {
        val calendar = Calendar.getInstance()
        val dialog = DatePickerDialog(
            activity,
            android.R.style.Theme_DeviceDefault_Light_Dialog,
            { _: DatePicker?, i: Int, i2: Int, i3: Int ->
                with(calendar) {
                    set(Calendar.YEAR, i)
                    set(Calendar.MONTH, i2)
                    set(Calendar.DAY_OF_MONTH, i3)
                }
                callBack.invoke(calendar)
            },
            year, month, dayOfMonth
        )
        dialog.datePicker.minDate = minDate
        if (!activity.isFinishing) {
            dialog.show()
        }
    }

}