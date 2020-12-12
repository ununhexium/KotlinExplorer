package com.example.jetpackcomposeexplorer.code

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import net.lab0.grammar.kotlin.KotlinParser
import net.lab0.grammar.kotlin.parseKotlin
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.tree.TerminalNode


fun extractSpots(code: String): List<Highlights.Spot<KotlinHighlight>> {
  val parser = parseKotlin(code)

  val kotlinListener = KotlinSyntaxHighlighterVisitor()
  val highlights =kotlinListener.visit(parser.kotlinFile())

  return highlights.spots.sortedBy { it.start }
}

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


val KotlinParser.IdentifierContext.range: IntRange
  get() =
    this.start.startIndex..this.stop.stopIndex

val TerminalNode.range: IntRange
  get() =
    this.symbol.startIndex..this.symbol.stopIndex

val ParserRuleContext.range: IntRange
  get() =
    this.start.startIndex..this.stop.stopIndex