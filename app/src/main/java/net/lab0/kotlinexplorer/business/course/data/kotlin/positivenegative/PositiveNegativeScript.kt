package net.lab0.kotlinexplorer.business.course.data.kotlin.positivenegative

fun main() {
  val number = 116
  print(
      if (number > 0) "It's positive: " + number
      else if(number < 0) "It's negative: $number"
      else "It's 0"
  )
}
