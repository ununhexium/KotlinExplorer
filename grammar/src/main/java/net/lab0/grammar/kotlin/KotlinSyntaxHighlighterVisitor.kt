package net.lab0.grammar.kotlin

import net.lab0.grammar.kotlin.KotlinHighlight.ANNOTATION
import net.lab0.grammar.kotlin.KotlinHighlight.BRACKET
import net.lab0.grammar.kotlin.KotlinHighlight.CHARACTER
import net.lab0.grammar.kotlin.KotlinHighlight.CHARACTER_ESCAPED
import net.lab0.grammar.kotlin.KotlinHighlight.COMMA
import net.lab0.grammar.kotlin.KotlinHighlight.FUNCTION
import net.lab0.grammar.kotlin.KotlinHighlight.KEYWORD
import net.lab0.grammar.kotlin.KotlinHighlight.MODIFIER
import net.lab0.grammar.kotlin.KotlinHighlight.NUMBER
import net.lab0.grammar.kotlin.KotlinHighlight.OPERATOR
import net.lab0.grammar.kotlin.KotlinHighlight.STRING
import net.lab0.grammar.kotlin.KotlinHighlight.STRING_ESCAPED_CHARACTER
import net.lab0.grammar.kotlin.KotlinHighlight.TYPE
import net.lab0.grammar.kotlin.KotlinParser.PostfixUnaryExpressionContext
import org.antlr.v4.runtime.tree.RuleNode
import org.antlr.v4.runtime.tree.TerminalNode

/**
 *
 * This stuff sometimes breaks on unexpected inputs. Stuff that should not be null can be null and it crashes the app.
 */
class KotlinSyntaxHighlighterVisitor(
  val wellKnownFunctions: List<String> = listOf("println", "print"),
) : KotlinParserBaseVisitor<Highlights<KotlinHighlight>>() {
  private fun hl(l: Highlights<KotlinHighlight>.() -> Unit): Highlights<KotlinHighlight> {
    val h = Highlights<KotlinHighlight>()
    l(h)
    return h
  }

  override fun defaultResult() = hl {}

  override fun aggregateResult(
    aggregate: Highlights<KotlinHighlight>,
    nextResult: Highlights<KotlinHighlight>,
  ): Highlights<KotlinHighlight> {
    aggregate.add(nextResult)
    return aggregate
  }

  override fun visitChildren(node: RuleNode): Highlights<KotlinHighlight> {
//    println("Visiting ${node.javaClass.simpleName} ${node.text}")
    return super.visitChildren(node)
  }

  override fun visitAdditiveOperator(ctx: KotlinParser.AdditiveOperatorContext) =
    hl {
      add(OPERATOR, ctx.range)
    }

  override fun visitAnnotation(ctx: KotlinParser.AnnotationContext) =
    hl {
      add(ANNOTATION, ctx.LabelReference().range)

      add(visitChildren(ctx))
    }

  override fun visitAssignmentOperator(ctx: KotlinParser.AssignmentOperatorContext) =
    hl {
      add(OPERATOR, ctx.range)
    }

  override fun visitBlock(ctx: KotlinParser.BlockContext) =
    hl {
      add(BRACKET, ctx.LCURL().range)
      add(BRACKET, ctx.RCURL().range)

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

  override fun visitComparisonOperator(ctx: KotlinParser.ComparisonOperatorContext) =
    hl {
      add(OPERATOR, ctx.range)
    }

  override fun visitEqualityOperation(ctx: KotlinParser.EqualityOperationContext) =
    hl {
      add(OPERATOR, ctx.range)
    }

  override fun visitFunctionDeclaration(ctx: KotlinParser.FunctionDeclarationContext) =
    hl {
      add(KEYWORD, ctx.FUN().range)
      ctx.identifier()?.let {
        add(FUNCTION, ctx.identifier().range)
      }

      add(visitChildren(ctx))
    }

  override fun visitLineStringLiteral(ctx: KotlinParser.LineStringLiteralContext) =
    hl {
      ctx.QUOTE_OPEN()?.let { add(STRING, it.range) }
      ctx.QUOTE_CLOSE()?.let { add(STRING, it.range) }

      add(visitChildren(ctx))
    }

  override fun visitLineStringContent(ctx: KotlinParser.LineStringContentContext) =
    hl {
      add(visitChildren(ctx))
    }

  override fun visitLineStringExpression(ctx: KotlinParser.LineStringExpressionContext) =
    hl {
      add(STRING_ESCAPED_CHARACTER, ctx.LineStrExprStart().range)
      add(STRING_ESCAPED_CHARACTER, ctx.RCURL().range)

      add(visitChildren(ctx))
    }

  override fun visitModifier(ctx: KotlinParser.ModifierContext) =
    hl {
      add(MODIFIER, ctx.range)

      add(visitChildren(ctx))
    }

  override fun visitMultiLineStringLiteral(ctx: KotlinParser.MultiLineStringLiteralContext) =
    hl {
      add(STRING, ctx.TRIPLE_QUOTE_OPEN().range)
      add(STRING, ctx.TRIPLE_QUOTE_CLOSE().range)

      add(visitChildren(ctx))
    }

  override fun visitMultiLineStringContent(ctx: KotlinParser.MultiLineStringContentContext) =
    hl {
      add(visitChildren(ctx))
    }

  override fun visitMultiLineStringExpression(ctx: KotlinParser.MultiLineStringExpressionContext) =
    hl {
      add(STRING_ESCAPED_CHARACTER, ctx.MultiLineStrExprStart().range)
      add(STRING_ESCAPED_CHARACTER, ctx.RCURL().range)

      add(visitChildren(ctx))
    }

  override fun visitMultiplicativeOperation(ctx: KotlinParser.MultiplicativeOperationContext) =
    hl {
      add(OPERATOR, ctx.range)
    }

  override fun visitImportHeader(ctx: KotlinParser.ImportHeaderContext) =
    hl {
      add(KEYWORD, ctx.IMPORT().range)

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

  override fun visitPostfixUnaryExpression(ctx: PostfixUnaryExpressionContext) =
    hl {
      if (ctx.atomicExpression()?.text in wellKnownFunctions) {
        add(FUNCTION, ctx.atomicExpression().range)
      }

      add(visitChildren(ctx))
    }

  override fun visitPostfixUnaryOperation(ctx: KotlinParser.PostfixUnaryOperationContext) =
    hl {
      ctx.INCR()?.let {
        add(OPERATOR, it.range)
      }

      ctx.DECR()?.let {
        add(OPERATOR, it.range)
      }

      ctx.EXCL()?.let {
        if (it.size == 2) {
          add(OPERATOR, it.first().range.first, it.last().range.last)
        }
      }

      add(visitChildren(ctx))
    }


  override fun visitTerminal(node: TerminalNode) =
    hl {
      when (node.symbol.type) {
        KotlinParser.BooleanLiteral -> add(KEYWORD, node.range)
        KotlinParser.FloatLiteral -> add(NUMBER, node.range)
        KotlinParser.RealLiteral -> add(NUMBER, node.range)
        KotlinParser.ELSE -> add(KEYWORD, node.range)
        KotlinParser.HexLiteral -> add(NUMBER, node.range)
        KotlinParser.IF -> add(KEYWORD, node.range)
        KotlinParser.IntegerLiteral -> add(NUMBER, node.range)
        KotlinParser.NullLiteral -> add(KEYWORD, node.range)
        KotlinParser.RETURN -> add(KEYWORD, node.range)
        KotlinParser.WHERE -> add(KEYWORD, node.range)

        // comments
//          KotlinParser.Inside_Comment -> add(COMMENT, node.range)
//          KotlinParser.LineComment -> add(COMMENT, node.range)
//          KotlinParser.DelimitedComment -> add(COMMENT, node.range)
//          KotlinParser.StrExpr_Comment -> add(COMMENT, node.range)

        // brackets, parent, ...
        KotlinParser.LPAREN -> add(BRACKET, node.range)
        KotlinParser.RPAREN -> add(BRACKET, node.range)
        KotlinParser.LSQUARE -> add(BRACKET, node.range)
        KotlinParser.RSQUARE -> add(BRACKET, node.range)

        // characters
        KotlinParser.CharacterLiteral -> {
          val range = node.range

          if ('\\' in node.text) {
            add(CHARACTER, range.start .. range.start)
            add(CHARACTER_ESCAPED, (range.start + 1) .. (range.last - 1))
            add(CHARACTER, range.last .. range.last)
          } else {
            add(CHARACTER, range)
          }

        }

        // operators
        KotlinParser.ASSIGNMENT -> add(OPERATOR, node.range)

        // strings
        KotlinParser.LineStrText -> add(STRING, node.range)
        KotlinParser.LineStrExprStart -> add(STRING_ESCAPED_CHARACTER, node.range)
        KotlinParser.LineStrEscapedChar -> add(STRING_ESCAPED_CHARACTER, node.range)
        KotlinParser.MultiLineStringQuote -> add(STRING, node.range)
        KotlinParser.MultiLineStrText -> add(STRING, node.range)
        KotlinParser.MultiLineStrEscapedChar -> add(STRING_ESCAPED_CHARACTER, node.range)
      }
    }

  override fun visitType(ctx: KotlinParser.TypeContext) =
    hl {
      add(TYPE, ctx.range)
    }

  override fun visitTypeParameters(ctx: KotlinParser.TypeParametersContext) =
    hl {
      add(BRACKET, ctx.LANGLE().range)
      add(BRACKET, ctx.RANGLE().range)

      add(visitChildren(ctx))
    }

  override fun visitWhenExpression(ctx: KotlinParser.WhenExpressionContext) =
    hl {
      add(KEYWORD, ctx.WHEN().range)
      ctx.LCURL()?.let { add(BRACKET, it.range) }
      ctx.RCURL()?.let { add(BRACKET, it.range) }

      add(visitChildren(ctx))
    }

  override fun visitWhileExpression(ctx: KotlinParser.WhileExpressionContext) =
    hl {
      add(KEYWORD, ctx.WHILE().range)

      add(visitChildren(ctx))
    }


}
