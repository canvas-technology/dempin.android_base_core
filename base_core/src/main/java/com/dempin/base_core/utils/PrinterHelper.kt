package com.dempin.base_core.utils

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.print.PrintJob
import android.print.PrintManager

object PrinterHelper {

    fun printWithManager(
        context: Activity,
        bitmaps: List<Bitmap>,
        tag: String,
        callBackResult: (Int) -> Unit
    ): PrintJob {
        val printManager = context.getSystemService(Context.PRINT_SERVICE) as PrintManager
        val size = bitmaps.size
        val jobName = "pin_${size}_${tag}"
        val adapter = MyPrintDocumentAdapter(context, bitmaps,callBackResult)
        return printManager.print(jobName, adapter, null)
    }

}