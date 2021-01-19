package net.lab0.grammar.kotlin

import net.lab0.grammar.kotlin.Highlights.Spot
import org.assertj.core.api.AbstractAssert
import kotlin.math.min

class SpotsAssert<H>(
    private val code: String,
    actual: List<Spot<H>>
) :
    AbstractAssert<SpotsAssert<H>, List<Spot<H>>>(
        actual.toList(),
        SpotsAssert::class.java
    ) {

  companion object {
    fun <H> assertThat(code: String, actual: List<Spot<H>>): SpotsAssert<H> {
      return SpotsAssert(code, actual)
    }
  }

  fun hasSpots(vararg spots: Spot<H>): SpotsAssert<H> {
    isNotNull

    val a = actual.toList()

    val test = a.containsAll(spots.toList())

    if (!test) {
      val missing = spots.filter { it !in a }
      val missingMarks = Array(code.length) { ' ' }
      missing.forEach { s ->
        val range = s.end - s.start
        if (range == 0 && s.start < code.length) {
          missingMarks[s.start] = s.highlight.toString()[0]
        } else {
          val highlightText = s.highlight.toString()
          ((s.start + 1)..min(s.end - 1, code.length)).forEach { i ->
            val stringRelativeIndex = i - s.start - 1
            if (stringRelativeIndex < highlightText.length) {
              missingMarks[i] = highlightText[stringRelativeIndex]
            } else {
              missingMarks[i] = '-'
            }
          }
          if (s.start < code.length) missingMarks[s.start] = '<'
          if (s.end < code.length) missingMarks[s.end] = '>'
        }
      }
      failWithMessage(
          "Missing spots:\n" + missing.joinToString("\n") { "  $it" } + "\n" +
              (code.indices).joinToString("") {
                when (val i = it % 10) {
                  0 -> "${('A'..'Z').toList()[it/10]}"
                  1 -> "_"
                  else -> "$i"
                }
              } + "\n" +
              code.split('\n').joinToString("\u21B5") + "\n" +
              missingMarks.joinToString("").trimEnd()
      )
    }

    // return the current assertion for method chaining
    return this
  }
}