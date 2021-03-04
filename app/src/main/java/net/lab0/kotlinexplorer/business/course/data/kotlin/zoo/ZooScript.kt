package net.lab0.kotlinexplorer.business.course.data.kotlin.zoo

interface Swimmer {
  fun swim()
}

interface Flyer {
  fun fly()
}

open class Bird(val name: String) : Flyer {
  override fun fly() {
    println("🐦🐦🐦...")
  }
}

open class Fish(val name: String) : Swimmer {
  override fun swim() {
    println("🐟🐟🐟...")
  }
}

/**
 * A flying fish
 */
class Exocoetidae(name: String) : Fish(name), Flyer {
  override fun fly() {
    println("$name flies")
  }
}

fun main() {

}