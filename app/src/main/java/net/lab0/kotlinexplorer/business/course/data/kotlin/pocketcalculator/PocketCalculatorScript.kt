package net.lab0.kotlinexplorer.business.course.data.kotlin.pocketcalculator

import kotlin.math.sqrt

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

fun square(a: Int): Int {
  return a * a
}

fun squareRoot(a: Int): Double {
  val aAsDouble = a.toDouble()
  return sqrt(aAsDouble)
}

fun main() {
  val a = 3
  val b = 1
  println(square(a))
  println(squareRoot(a))
  println(kotlin.math.min(a, b))
}
