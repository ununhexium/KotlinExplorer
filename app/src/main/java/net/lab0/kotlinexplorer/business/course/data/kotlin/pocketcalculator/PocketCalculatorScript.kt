package net.lab0.kotlinexplorer.business.course.data.kotlin.pocketcalculator

fun add(a: Int, b: Int): Int {
  return a + b
}

fun squareRoot(a: Int): Double =
    kotlin.math.sqrt(a.toDouble())

fun println(any:Any): Unit {
  print(any.toString())
  print("\n")
}

fun main() {
  println(add(2, 3))
  kotlin.io.println(squareRoot(9))
}
