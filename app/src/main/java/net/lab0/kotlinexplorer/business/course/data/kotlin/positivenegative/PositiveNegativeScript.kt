package net.lab0.kotlinexplorer.business.course.data.kotlin.positivenegative

fun main() {
  val n = 116
  print(
      if (n > 0) "It's >0: " + n
      else if (n < 0) "It's <0: $n"
      else "It's 0"
  )
}
