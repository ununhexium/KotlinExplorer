package net.lab0.grammar.kotlin

import net.lab0.grammar.kotlin.KotlinHighlight.ANNOTATION
import net.lab0.grammar.kotlin.KotlinHighlight.BRACKET
import net.lab0.grammar.kotlin.KotlinHighlight.COMMA
import net.lab0.grammar.kotlin.KotlinHighlight.COMMENT
import net.lab0.grammar.kotlin.KotlinHighlight.FUNCTION_DECLARATION
import net.lab0.grammar.kotlin.KotlinHighlight.NUMBER
import net.lab0.grammar.kotlin.KotlinHighlight.KEYWORD
import net.lab0.grammar.kotlin.KotlinHighlight.MODIFIER
import net.lab0.grammar.kotlin.KotlinHighlight.STRING
import org.antlr.v4.runtime.tree.TerminalNode

class KotlinSyntaxHighlighterVisitor : KotlinParserBaseVisitor<Highlights<KotlinHighlight>>() {
  private fun hl(l: Highlights<KotlinHighlight>.() -> Unit): Highlights<KotlinHighlight> {
    val h = Highlights<KotlinHighlight>()
    l(h)
    return h
  }

  override fun defaultResult() = hl {}

  override fun aggregateResult(
      aggregate: Highlights<KotlinHighlight>,
      nextResult: Highlights<KotlinHighlight>
  ): Highlights<KotlinHighlight> {
    aggregate.add(nextResult)
    return aggregate
  }

  override fun visitAnnotation(ctx: KotlinParser.AnnotationContext) =
    hl {
      add(ANNOTATION, ctx.LabelReference().range)

      add(visitChildren(ctx))
    }

  override fun visitClassDeclaration(ctx: KotlinParser.ClassDeclarationContext) =
    hl {
      ctx.CLASS()?.let { add(KEYWORD, it.range) }
      ctx.INTERFACE()?.let { add(KEYWORD, it.range) }

      add(visitChildren(ctx))
    }

  override fun visitClassParameter(ctx: KotlinParser.ClassParameterContext) =
    hl {
      ctx.VAL()?.let { add(KEYWORD, it.range) }
      ctx.VAR()?.let { add(KEYWORD, it.range) }

      add(visitChildren(ctx))
    }

  override fun visitClassParameters(ctx: KotlinParser.ClassParametersContext) =
    hl {
      ctx.COMMA().forEach {
        add(COMMA, it.range)
      }

      add(visitChildren(ctx))
    }

  override fun visitFunctionDeclaration(ctx: KotlinParser.FunctionDeclarationContext) =
    hl {
      add(KEYWORD, ctx.FUN().range)
      add(FUNCTION_DECLARATION, ctx.identifier().range)

      add(visitChildren(ctx))
    }

  override fun visitModifier(ctx: KotlinParser.ModifierContext) =
    hl {
      add(MODIFIER, ctx.range)

      add(visitChildren(ctx))
    }

  override fun visitPackageHeader(ctx: KotlinParser.PackageHeaderContext) =
    hl {
      ctx.PACKAGE()?.let { add(KEYWORD, it.range) }

      add(visitChildren(ctx))
    }

  override fun visitPropertyDeclaration(ctx: KotlinParser.PropertyDeclarationContext) =
    hl {
      ctx.VAL()?.let { add(KEYWORD, ctx.VAL().range) }
      ctx.VAR()?.let { add(KEYWORD, ctx.VAR().range) }

      add(visitChildren(ctx))
    }

  override fun visitStringLiteral(ctx: KotlinParser.StringLiteralContext) =
    hl {
      add(STRING, ctx.range)

      add(visitChildren(ctx))
    }

  override fun visitTerminal(node: TerminalNode) =
    hl {
      when (node.symbol.type) {
        KotlinParser.BooleanLiteral -> add(KEYWORD, node.range)
        KotlinParser.ELSE -> add(KEYWORD, node.range)
        KotlinParser.HexLiteral -> add(NUMBER, node.range)
        KotlinParser.IF -> add(KEYWORD, node.range)
        KotlinParser.IntegerLiteral -> add(NUMBER, node.range)
        KotlinParser.LineComment -> add(COMMENT, node.range)
        KotlinParser.NullLiteral -> add(KEYWORD, node.range)
        KotlinParser.RETURN -> add(KEYWORD, node.range)
        KotlinParser.WHERE -> add(KEYWORD, node.range)

        // brackets, parent, ...
        KotlinParser.LPAREN -> add(BRACKET, node.range)
        KotlinParser.RPAREN -> add(BRACKET, node.range)
        KotlinParser.LSQUARE -> add(BRACKET, node.range)
        KotlinParser.RSQUARE -> add(BRACKET, node.range)
        KotlinParser.LANGLE -> add(BRACKET, node.range)
        KotlinParser.RANGLE -> add(BRACKET, node.range)
        KotlinParser.LCURL -> add(BRACKET, node.range)
        KotlinParser.RCURL -> add(BRACKET, node.range)
      }
    }
}
