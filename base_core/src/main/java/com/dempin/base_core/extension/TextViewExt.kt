package com.canvas.demin.pos.extension

import androidx.appcompat.widget.AppCompatTextView

fun AppCompatTextView.getInt():Int{
    val value = text.toString()
    if(value.isEmpty()){
        return 1
    }
    return try{
        value.toInt()
    }catch (ex:Exception){
        1
    }
}