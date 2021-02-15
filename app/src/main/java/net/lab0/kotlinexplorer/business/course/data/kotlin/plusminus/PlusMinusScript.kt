package net.lab0.kotlinexplorer.business.course.data.kotlin.plusminus

import kotlin.random.Random

fun main() {
  // generates a number between 0 and 99
  val target = Random.nextInt(100)

  var proposition = -1
  var tries = 0

  while (proposition != target) {
    proposition = readLine()!!.toInt()
    tries++

    if (proposition == target) {
      println("You win in $tries!")
    } else if (proposition > target) {
      println("Too big!")
    } else {
      println("Too small!")
    }
  }
}
