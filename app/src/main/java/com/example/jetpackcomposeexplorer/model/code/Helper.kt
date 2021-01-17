package com.example.jetpackcomposeexplorer.model.code

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import net.lab0.grammar.kotlin.KotlinHighlight
import net.lab0.grammar.kotlin.extractSpots

fun extractHighlightsAndAnnotate(kotlinCode: String, styler: (KotlinHighlight) -> SpanStyle ): AnnotatedString {
  val spots = extractSpots(kotlinCode)

  val builder = AnnotatedString.Builder(kotlinCode)

  spots.forEach {
    builder.addStyle(
        styler(it.highlight),
        it.start,
        it.end + 1
    )
  }

  return builder.toAnnotatedString()
}
