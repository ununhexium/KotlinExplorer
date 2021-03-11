package net.lab0.kotlinexplorer.framework.presentation.composable.code

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import net.lab0.kotlinexplorer.framework.util.overlapedBy

fun AnnotatedString.invertForegroundBackgroundColors(range: IntRange): AnnotatedString {
  val original = this

  return buildAnnotatedString {
    append(original.text)
    original.spanStyles.forEach { span ->
      val intersection = (span.start until span.end).overlapedBy(range)

      addStyle(
        span.item,
        span.startInclusive,
        span.endExclusive
      )
      intersection?.let {
        addStyle(
          span.item.copy(color = span.item.background, background = span.item.color),
          intersection.startInclusive,
          intersection.endExclusive
        )
      }
    }
  }
}

/*
 * redefining the boundaries because of a mess in
 * inclusive / exclusive end definitions
 */

private val <T> AnnotatedString.Range<T>.startInclusive
  get() = this.start

private val <T> AnnotatedString.Range<T>.endExclusive: Int
  get() = this.end

private val IntRange.endExclusive: Int
  get() = this.last + 1

private val IntRange.startInclusive: Int
  get() = this.first
