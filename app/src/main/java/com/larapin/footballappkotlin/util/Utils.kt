package com.larapin.footballappkotlin.util

/**
 * Created by Avin on 04/09/2018.
 * class Utils
 */
import android.annotation.SuppressLint
import android.view.View
import java.text.SimpleDateFormat
import java.util.*

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone(){
    visibility = View.GONE
}

@SuppressLint("SimpleDateFormat")
fun toGMTFormat(date: String?, time: String?): Date? {
    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm")
    formatter.timeZone = TimeZone.getTimeZone("UTC")
    val dateTime = "$date $time"
    return formatter.parse(dateTime)
}

fun newLine(pemain: String?): String? {
    return pemain?.replace("; ", "\n")
}

fun newLineGoals(pemain: String?): String? {
    return pemain?.replace(";", "\n")
}