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

  fun hasAtLeastSpotsH(vararg spots: Spot<H>): SpotsAssert<H> {
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
          ((s.start + 1) .. min(s.end - 1, code.length)).forEach { i ->
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
                0 -> "${('A' .. 'Z').toList()[it / 10]}"
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

  fun hasAtLeastSpots(vararg spots: Spot<H>): SpotsAssert<H> {
    isNotNull

    val a = actual.toList()

    val containsAtLeast = a.containsAll(spots.toList())

    if (!containsAtLeast) {
      val missing = spots.filter { it !in a }
      val missingMarks = buildRangesLine(missing)
      failWithMessage(
        buildMissingSpotsMessage(
          "Missing spots:",
          missing,
          missingMarks
        )
      )
    }

    // return the current assertion for method chaining
    return this
  }

  fun hasExactlySpots(vararg spots: Spot<H>): SpotsAssert<H> {
    isNotNull

    val a = actual.toList()

    val containsExactly = a.toSet() == spots.toSet()

    if (!containsExactly) {
      val missing = spots.filter { it !in a }
      val tooMuch = a.filter { it !in spots }

      val outOfRange = missing.firstOrNull { it.start < 0 || it.end < 0 } ?:
            tooMuch.firstOrNull { it.start < 0 || it.end < 0 }
      if(outOfRange != null) {
        failWithMessage("The spot $outOfRange")
      }

      val missingMarks = buildRangesLine(missing)
      val extraneousMarks = buildRangesLine(tooMuch)

      val messageBuilder = StringBuilder()

      if (missing.isNotEmpty()) {
        messageBuilder.append(
          buildMissingSpotsMessage(
            "Missing spots:",
            missing,
            missingMarks
          )
        ).append("\n")
      }

      if (missing.isNotEmpty() && tooMuch.isNotEmpty()) {
        messageBuilder.append("\n")
      }

      if (tooMuch.isNotEmpty()) {
        messageBuilder.append(
          buildMissingSpotsMessage(
            "Extraneous spots:",
            tooMuch,
            extraneousMarks
          )
        )
      }

      failWithMessage(messageBuilder.toString())
    }

    // return the current assertion for method chaining
    return this
  }

  private fun buildMissingSpotsMessage(
    label: String,
    spots: List<Spot<H>>,
    missingMarks: Array<Char>
  ) = "$label\n" + spots.joinToString("\n") { "  $it" } + "\n" +
      (code.indices).joinToString("") {
        when (val i = it % 10) {
          0 -> "${('A' .. 'Z').toList()[(it / 10) % 26]}"
          1 -> "_"
          else -> "$i"
        }
      } + "\n" +
      code.split('\n').joinToString("\u21B5") + "\n" +
      missingMarks.joinToString("").trimEnd() + "\n" +
      verticalWriter(
        spots.map { ((it.end + it.start) / 2) to it.highlight.toString() }.toMap()
      ).trimEnd()

  private fun buildRangesLine(missing: List<Spot<H>>): Array<Char> {
    val missingMarks = Array(code.length) { ' ' }
    missing.forEach { s ->
      val range = s.end - s.start
      if (range == 0 && s.start < code.length) {
        missingMarks[s.start] = '^'
      } else {
        ((s.start + 1) .. min(s.end - 1, code.length)).forEach { i ->
          missingMarks[i] = '-'
        }
        if (s.start < code.length) missingMarks[s.start] = '<'
        if (s.end < code.length) missingMarks[s.end] = '>'
      }
    }
    return missingMarks
  }
}
