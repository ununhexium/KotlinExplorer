package net.lab0.grammar.kotlin

/**
 * Writes strings vertically at the given index.
 *
 * Ex: mapOf(0 to "foo", 3 to "poop") will return:
 *
 * ```
 * f  p
 * o  o
 * o  o
 *    p
 * ```
 *
 * With 2 spaces in the middle.
 */
fun verticalWriter(
  strings: Map<Int, String>
): String {
  if (strings.isEmpty()) return ""

  val deepest = strings.keys.maxOrNull()!!
  val longest = strings.values.map { it.length }.maxOrNull()!!

  return (0 until longest).joinToString("\n") { length ->
    (0 .. deepest).joinToString("") { depth ->

      val word = strings[depth]

      if (word != null && length < word.length) {
        word[length].toString()
      } else " "
    }
  }.trimEnd()
}