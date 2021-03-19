package net.lab0.kotlinexplorer.business.course.data.kotlin.pocketcalculator

import kotlin.math.min

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

fun squareRoot(a: Int): Int {
  val aAsDouble = a.toDouble()
  return kotlin.math.sqrt(aAsDouble).toInt()
}

fun main() {
  val a = 3
  val b = 1
  kotlin.io.println(square(a))
  println(squareRoot(square(a)))
  println(min(a,b))
}
