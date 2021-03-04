package net.lab0.kotlinexplorer.business.course.data.kotlin.pocketcalculator

/**
 * Requirements
 *
 * basic functions
 * fully qualified names
 * function call orders
 *
 * Operators priority
 *
 * More number types
 */

// your own function
fun add(a: Int, b: Int): Int {
  return a + b
}

// fancy notation
fun squareRoot(a: Int): Double =
  // calling external function
  kotlin.math.sqrt(a.toDouble())

fun main() {
  // fully qualified function call
  kotlin.io.println(add(2, 3))
  println(
    // chaining function calls
    squareRoot(add(3, add(3, 3)))
  )
}
