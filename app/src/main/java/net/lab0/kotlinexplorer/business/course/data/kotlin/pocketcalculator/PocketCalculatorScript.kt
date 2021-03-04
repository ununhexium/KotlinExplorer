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

fun add(a: Int, b: Int): Int {
  return a + b
}

fun squareRoot(a: Int): Double =
  kotlin.math.sqrt(a.toDouble())

fun main() {
  kotlin.io.println(add(2, 3)) // 5
  println(
    // 3
    squareRoot(add(3, add(3, 3)))
  )
}
