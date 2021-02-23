package net.lab0.kotlinexplorer.business.course.data.kotlin.moreorless

import kotlin.random.Random

fun main() {
  // generates a number between 0 and 99
  val target = Random.nextInt(100)

  var proposition:Int? = null
  var tries = 0

  while (proposition != target) {
    proposition = readLine()!!.toInt()
    tries++

    print(
        when {
          proposition == target -> "You win in $tries!"
          proposition > target -> "Too big!"
          else -> "Too small!"
        }
    )
  }
}
