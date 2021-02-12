package net.lab0.jetpackcomposeexplorer.utils

// https://youtrack.jetbrains.com/issue/KT-12380
object Do { inline infix fun<reified T> exhaustive(any: T?) = any }
