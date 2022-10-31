package com.dempin.base_core.utils

import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import android.graphics.Bitmap

object BarcodeGenerator {

    fun getBitmap(text:String,width:Int,height:Int):Bitmap{
        val bitMatrix = MultiFormatWriter()
        val matrix = bitMatrix.encode(text, BarcodeFormat.QR_CODE,width,height)

        val mWidth = matrix.width
        val mHeight = matrix.height
        val pixels = IntArray(mWidth * mHeight)
        for (i in 0 until mHeight) {
            for (j in 0 until mWidth) {
                if (matrix.get(j, i)) {
                    pixels[i * mWidth + j] = 0xff000000.toInt()
                } else {
                    pixels[i * mWidth + j] = 0xffffffff.toInt()
                }
            }
        }

        val bitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888)
        bitmap.setPixels(pixels, 0, mWidth, 0, 0, mWidth, mHeight)
        return bitmap
    }

}