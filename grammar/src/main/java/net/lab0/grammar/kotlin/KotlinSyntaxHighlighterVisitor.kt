package net.lab0.grammar.kotlin

import net.lab0.grammar.kotlin.KotlinHighlight.ANNOTATION
import net.lab0.grammar.kotlin.KotlinHighlight.COMMA
import net.lab0.grammar.kotlin.KotlinHighlight.COMMENT
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
      // TODO: visit children

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
        KotlinParser.HexLiteral -> add(NUMBER, node.range)
        KotlinParser.IntegerLiteral -> add(NUMBER, node.range)
        KotlinParser.LineComment -> add(COMMENT, node.range)
        KotlinParser.RETURN -> add(KEYWORD, node.range)
        KotlinParser.IF -> add(KEYWORD, node.range)
        KotlinParser.ELSE -> add(KEYWORD, node.range)
        KotlinParser.BooleanLiteral -> add(KEYWORD, node.range)
        KotlinParser.NullLiteral -> add(KEYWORD, node.range)
      }
    }

  override fun visitIfExpression(ctx: KotlinParser.IfExpressionContext) =
      hl {
        add(visitChildren(ctx))
      }
}
