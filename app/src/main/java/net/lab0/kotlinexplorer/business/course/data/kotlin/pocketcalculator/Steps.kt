// Steps to learn functions

/**
 * declare a function
 * give it a name
 * what is a valid name?
 * what is a good name?
 */
fun sayHello() {
// do things inside the function
  println("Hello")
}

fun sayHello(name: String) {
  println("Hello $name")
}

fun makeHello(name: String): String {
  return "Hello $name"
}

fun addOne(a: Int): Int {
  return a + 1
}

fun add(a:Int, b:Int): Int {
  return a + b
}

/**
 * named arguments
 * add(b = 1, a = 2)
 */

/**
 * Number, Int, Double, Float
 *
 * Number range
 *
 * Infinity, NaN
 *
 */

/**
 * Number types conversion
 * .toInt .toDouble .toFloat
 */

/**
 * Change type
 */
fun say(a:Int): String {
  return "$a"
}

/**
 * Call external fully qualified function
 */
fun squareRoot(a: Int): Double =
  kotlin.math.sqrt(a.toDouble())

/**
 * Read a new function's declaration
 *
 * public fun sqrt(x: Double): Double = ...
 */

/**
 * Function call order
 *
 * fun addOne(1)
 *
 * addThree(x) {
 *   return add1(add1(add1(x)))
 * }
 */

/**
 * Math operators priority
 */

// shorter notation
fun makeHelloToo(name: String) =
  "Hello $name"
