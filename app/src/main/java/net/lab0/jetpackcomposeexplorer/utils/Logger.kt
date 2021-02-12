package net.lab0.jetpackcomposeexplorer.utils

import android.util.Log
import net.lab0.jetpackcomposeexplorer.utils.Constants.DEBUG
import net.lab0.jetpackcomposeexplorer.utils.Constants.TAG

var isUnitTest = false

fun printLogD(className: String?, message: String ) {
    if (DEBUG && !isUnitTest) {
        Log.d(TAG, "$className: $message")
    }
    else if(DEBUG && isUnitTest){
        println("$className: $message")
    }
}
