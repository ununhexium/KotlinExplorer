package com.example.jetpackcomposeexplorer.kotlin

import kotlin.reflect.KProperty

val l by lazy {
  "foo"
}

val c by cached({ "FOO" }) {
  it.repeat(2)
}

fun <I, T> cached(input: () -> I, transform: (I) -> T) =
    ThreadUnsafeCachedImpl(input, transform)

class ThreadUnsafeCachedImpl<I, out T>(
    val input: () -> I,
    val transform: (I) -> T,
) {
  private var _value: T? = null
  private var lastInput: I? = null

  operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
    val currentInput = input()
    if (lastInput != currentInput) {
      lastInput = currentInput
      _value = transform(currentInput)
    }

    @Suppress("UNCHECKED_CAST")
    return _value as T
  }
}