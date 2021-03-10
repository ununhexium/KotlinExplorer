package net.lab0.kotlinexplorer.business.course.data.kotlin.pocketcalculator

import kotlin.io.println as printWithLine

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

// integer division
fun divideInts(a: Int, b: Int): Double {
  return a.toDouble() / b
}

// change the type of number
fun divideIntsReturnDouble(
  a: Int, b: Int
): Double {
  return a.toDouble() / b
}

// fancy notation
fun squareRoot(a: Int): Double =
  // calling external function
  kotlin.math.sqrt(a.toDouble())

// compute the average
fun average(a: Int, b: Int): Double {
  return (a + b) / 2.0
}

fun println(double: Double) {
  print(double.toString())
  print("\n")
}

fun main() {
  // fully qualified function call
  printWithLine("Results")

  // 5
  kotlin.io.println(add(2, 3))

  // 2
  println(divideInts(7, 3))

  // 2.333...
  println(divideIntsReturnDouble(7, 3))

  // 3.0
  println(
    // chaining function calls
    squareRoot(add(3, add(3, 3)))
  )
}
