package net.lab0.grammar.kotlin

import org.antlr.v4.runtime.BaseErrorListener
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.ConsoleErrorListener
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.tree.TerminalNode


fun parseKotlin(code: String): KotlinParser {
  val lexer = KotlinLexer(CharStreams.fromString(code))
  lexer.removeErrorListener(ThrowingKotlinErrorListener())
  val tokens = CommonTokenStream(lexer)
  val parser = KotlinParser(tokens)
  parser.addErrorListener(BaseErrorListener())
  parser.removeErrorListener(ConsoleErrorListener.INSTANCE)
  return parser
}

val COMMENT_TOKEN_TYPES = arrayOf(
    KotlinParser.LineComment,
    KotlinParser.DelimitedComment,
    KotlinParser.Inside_Comment,
    KotlinParser.StrExpr_Comment,
)

fun extractSpots(code: String): List<Highlights.Spot<KotlinHighlight>> {
  val parser = parseKotlin(code)

  val kotlinListener = KotlinSyntaxHighlighterVisitor()
  val highlights = kotlinListener.visit(parser.kotlinFile())

  // add comments
  val tokens = parser.tokenStream
  (0 until tokens.size()).forEach {
    val token = tokens[it]
    if (token.type in COMMENT_TOKEN_TYPES) {
      highlights.add(KotlinHighlight.COMMENT, token.startIndex, token.stopIndex)
    }
  }

  return highlights.spots.sortedBy { it.start }
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
