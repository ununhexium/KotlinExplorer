package net.lab0.kotlinexplorer

import org.junit.jupiter.api.Test

class Sevenish {
  fun pow7(b: Int): Long = (1 .. b).fold(1L) { acc, e -> acc * 7 }

  @Test
  fun `sevenish`() {
    fun sevenish(n: Int): Long {
      val sev = mutableListOf(1L)
      var power = 0
      while (sev.size <= n) {
        power++
        val lastPower = pow7(power)
        sev.add(lastPower)
        (0 until sev.lastIndex).forEach {
          val element = lastPower + sev[it]
//          println(element)
          sev.add(element)
        }
      }
      return sev[n]
    }


    println(pow7(2))
    println(sevenish(1))

    (0..10).forEach {
      println(sevenish(it))
    }

// 7^0  7^1  7^2      7^3
//      1+7  1+49     343+1
//           7+49     343+7
//           1+7+49   343+1+7
//                    343+49
//                    343+1+49
//                    343+7+49
//                    343+1+7+49

  }
}