package net.lab0.kotlinexplorer.utils

// https://youtrack.jetbrains.com/issue/KT-12380
object Do {
  inline infix fun <reified T> exhaustive(any: T?) = any
  inline infix fun <reified T> exhaustiveNonNull(any: T) where T : Any = any
}
