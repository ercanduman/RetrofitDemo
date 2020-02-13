package ercanduman.retrofitdemo.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import ercanduman.retrofitdemo.BuildConfig

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Any.logd(message: String) {
    if (BuildConfig.DEBUG) Log.d(this.javaClass.name, message)
}

fun printException(exception: Exception) {
    if (BuildConfig.DEBUG) exception.printStackTrace()
}