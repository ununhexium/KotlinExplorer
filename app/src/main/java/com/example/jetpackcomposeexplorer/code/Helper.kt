package com.example.jetpackcomposeexplorer.code

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import net.lab0.grammar.kotlin.KotlinParser
import net.lab0.grammar.kotlin.parseKotlin
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.tree.ParseTreeWalker
import org.antlr.v4.runtime.tree.TerminalNode

fun extractHighlightsAndAnnotate(kotlinCode: String, styler: (KotlinHighlight) -> SpanStyle ): AnnotatedString {
  val parser = parseKotlin(kotlinCode)
  val walker = ParseTreeWalker()
  val kotlinListener = KotlinSyntaxHighlighterVisitor()
  walker.walk(kotlinListener, parser.kotlinFile())
  val spots = kotlinListener.highlights.spots

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