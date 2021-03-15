package net.lab0.kotlinexplorer.utils

import android.util.Log
import net.lab0.kotlinexplorer.BuildConfig.DEBUG
import kotlin.reflect.KClass

var isUnitTest = false

fun printLogD(className: String?, message: String) {
    val TAG = "DEBUG"
    if (DEBUG && !isUnitTest) {
        Log.d(TAG, "$className: $message")
    }
    else if(DEBUG && isUnitTest){
        println("$className: $message")
    }
}

fun printLogD(className: KClass<*>, message: String) {
    printLogD(className.simpleName, message)
}
