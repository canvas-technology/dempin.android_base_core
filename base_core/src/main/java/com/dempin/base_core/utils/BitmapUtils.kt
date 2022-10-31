package com.dempin.base_core.utils

import android.graphics.Bitmap
import android.graphics.Canvas

object BitmapUtils {

    fun mergeToBitmap(vararg bitmaps: Bitmap):Bitmap{
        var totalHeight = 0
        val width = bitmaps[0].width
        val config = bitmaps[0].config
        bitmaps.forEach {
            totalHeight += it.height
        }
        val newBitmap = Bitmap.createBitmap(width,totalHeight,config)
        val canvas = Canvas(newBitmap)
        var currentHeight = 0f
        bitmaps.forEach {bitmap->
            canvas.drawBitmap(bitmap,0f,currentHeight,null)
            currentHeight += bitmap.height
            bitmap.recycle()
        }
        return newBitmap
    }
}