package net.lab0.grammar.kotlin

import org.antlr.v4.runtime.BaseErrorListener
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.ConsoleErrorListener


fun parseKotlin(code: String): KotlinParser {
    val lexer = KotlinLexer(CharStreams.fromString(code))
    lexer.removeErrorListener(ThrowingKotlinErrorListener())
    val tokens = CommonTokenStream(lexer)
    val parser = KotlinParser(tokens)
    parser.addErrorListener(BaseErrorListener())
    parser.removeErrorListener(ConsoleErrorListener.INSTANCE)
    return parser
}
