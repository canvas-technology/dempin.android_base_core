package com.dempin.base_core.utils

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.os.CancellationSignal
import android.os.ParcelFileDescriptor
import android.print.PageRange
import android.print.PrintAttributes
import android.print.PrintDocumentAdapter
import android.print.PrintDocumentInfo
import android.print.pdf.PrintedPdfDocument
import android.graphics.pdf.PdfDocument.PageInfo
import android.graphics.Paint
import com.dempin.base_core.enum.PrintType
import com.dempin.base_core.extension.ignoreNull
import java.io.FileOutputStream


class MyPrintDocumentAdapter(
    private val context: Context,
    private val bitmaps: List<Bitmap>,
    private val callBackResult: (Int) -> Unit
) : PrintDocumentAdapter() {

    private val paint = Paint()
    private lateinit var myPdfDocument: PrintedPdfDocument

    override fun onLayout(
        oldAttributes: PrintAttributes?,
        newAttributes: PrintAttributes,
        cancellationSignal: CancellationSignal?,
        callback: LayoutResultCallback?,
        extras: Bundle?
    ) {
        try {
            myPdfDocument = PrintedPdfDocument(context, newAttributes)
            if (cancellationSignal?.isCanceled.ignoreNull()) {
                callback?.onLayoutCancelled()
                callBackResult.invoke(PrintType.CANCELED.type)
                return
            }

            val infoBuilder = PrintDocumentInfo
                .Builder("print_output.pdf")
                .setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT)
                .setPageCount(bitmaps.size)

            val info = infoBuilder.build()
            callback?.onLayoutFinished(info, true)
        } catch (ex: Exception) {
            callBackResult.invoke(PrintType.ERROR.type)
        }

    }

    override fun onWrite(
        pages: Array<out PageRange>?,
        destination: ParcelFileDescriptor?,
        cancellationSignal: CancellationSignal?,
        callback: WriteResultCallback?
    ) {
        if (cancellationSignal?.isCanceled.ignoreNull()) {
            callback?.onWriteCancelled()
            callBackResult.invoke(PrintType.CANCELED.type)
            myPdfDocument.close()
            return
        }
        bitmaps.forEachIndexed { index, bitmap ->
            if (pageInRange(pages, index)) {
                val newPage = PageInfo.Builder(
                    bitmap.width,
                    bitmap.height, index
                ).create()
                val page = myPdfDocument.startPage(newPage)
                if (cancellationSignal?.isCanceled.ignoreNull()) {
                    callback?.onWriteCancelled()
                    callBackResult.invoke(PrintType.CANCELED.type)
                    myPdfDocument.close()
                    return
                }
                val canvas = page.canvas
                canvas.drawBitmap(bitmaps[index], 0f, 0f, paint)
                myPdfDocument.finishPage(page)
            }
        }

        try {
            myPdfDocument.writeTo(
                FileOutputStream(
                    destination?.fileDescriptor
                )
            )
        } catch (e: Exception) {
            callback?.onWriteFailed(e.toString())
            callBackResult.invoke(PrintType.ERROR.type)
            return
        } finally {
            myPdfDocument.close()
        }

        callback?.onWriteFinished(pages)
    }

    private fun pageInRange(pageRanges: Array<out PageRange>?, page: Int): Boolean {
        if (pageRanges == null) {
            return false
        }
        for (i in pageRanges.indices) {
            if (page >= pageRanges[i].start &&
                page <= pageRanges[i].end
            ) return true
        }
        return false
    }


    override fun onFinish() {
        super.onFinish()
        callBackResult.invoke(PrintType.FINISH.type)
    }
}