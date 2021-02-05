package com.example.jetpackcomposeexplorer.utils

import android.util.Log
import com.example.jetpackcomposeexplorer.utils.Constants.DEBUG
import com.example.jetpackcomposeexplorer.utils.Constants.TAG

var isUnitTest = false

fun printLogD(className: String?, message: String ) {
    if (DEBUG && !isUnitTest) {
        Log.d(TAG, "$className: $message")
    }
    else if(DEBUG && isUnitTest){
        println("$className: $message")
    }
}
