package net.lab0.kotlinexplorer.utils

// https://youtrack.jetbrains.com/issue/KT-12380
object Do { inline infix fun<reified T> exhaustive(any: T?) = any }