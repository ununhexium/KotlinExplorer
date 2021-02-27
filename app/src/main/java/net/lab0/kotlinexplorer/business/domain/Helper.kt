package net.lab0.kotlinexplorer.business.domain

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import net.lab0.grammar.kotlin.KotlinHighlight
import net.lab0.grammar.kotlin.extractSpots
import net.lab0.kotlinexplorer.framework.presentation.composable.code.normalStyle

fun extractHighlightsAndAnnotate(kotlinCode: String, styler: (KotlinHighlight) -> SpanStyle): AnnotatedString {
  val spots = extractSpots(kotlinCode)

  val builder = AnnotatedString.Builder(kotlinCode)

  builder.addStyle(normalStyle, 0, kotlinCode.length)

  spots.forEach {
    builder.addStyle(
        styler(it.highlight),
        it.start,
        it.end + 1
    )
  }

  return builder.toAnnotatedString()
}
