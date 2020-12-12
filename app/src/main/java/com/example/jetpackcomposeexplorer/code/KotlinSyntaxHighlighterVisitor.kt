package com.example.jetpackcomposeexplorer.code

import com.example.jetpackcomposeexplorer.code.KotlinHighlight.ANNOTATION
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.COMMA
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.COMMENT
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.DECIMAL_INTEGER
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.HEXADECIMAL_INTEGER
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.KEYWORD
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.MODIFIER
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.STRING
import net.lab0.grammar.kotlin.KotlinParser
import net.lab0.grammar.kotlin.KotlinParserBaseVisitor
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
    }

  override fun visitClassDeclaration(ctx: KotlinParser.ClassDeclarationContext) =
    hl {
      add(visit(ctx.modifierList()))

      ctx.CLASS()?.let { add(KEYWORD, it.range) }
      ctx.INTERFACE()?.let { add(KEYWORD, it.range) }

      ctx.primaryConstructor()?.let { add(visit(it)) }
    }

  override fun visitClassParameter(ctx: KotlinParser.ClassParameterContext) =
    hl {
      ctx.VAL()?.let { add(KEYWORD, it.range) }
      ctx.VAR()?.let { add(KEYWORD, it.range) }
      ctx.modifierList()?.let { add(visit(it)) }

    }

  override fun visitClassParameters(ctx: KotlinParser.ClassParametersContext) =
    hl {
      ctx.COMMA().forEach {
        add(COMMA, it.range)
      }

      ctx.classParameter().forEach {
        add(visit(it))
      }
    }

  override fun visitFunctionDeclaration(ctx: KotlinParser.FunctionDeclarationContext) =
    hl {
      ctx.modifierList()?.let { add(visit(it)) }
      add(KEYWORD, ctx.FUN().range)
    }

  override fun visitModifier(ctx: KotlinParser.ModifierContext) =
    hl {
      add(MODIFIER, ctx.range)
    }

  override fun visitPackageHeader(ctx: KotlinParser.PackageHeaderContext) =
    hl {
      ctx.PACKAGE()?.let { add(KEYWORD, it.range) }
    }

  override fun visitPropertyDeclaration(ctx: KotlinParser.PropertyDeclarationContext) =
    hl {
      ctx.VAL()?.let { add(KEYWORD, ctx.VAL().range) }
      ctx.VAR()?.let { add(KEYWORD, ctx.VAR().range) }

      add(visitExpression(ctx.expression()))
    }

  override fun visitStringLiteral(ctx: KotlinParser.StringLiteralContext) =
    hl {
      add(STRING, ctx.range)
    }

  override fun visitTerminal(node: TerminalNode) =
    hl {
      when (node.symbol.type) {
        KotlinParser.IntegerLiteral -> add(DECIMAL_INTEGER, node.range)
        KotlinParser.HexLiteral -> add(HEXADECIMAL_INTEGER, node.range)
        KotlinParser.LineComment -> add(COMMENT, node.range)
      }
    }


  //  override fun enterPackageHeader(ctx: KotlinParser.PackageHeaderContext) {
//    ctx.PACKAGE()?.let {
//      highlights.addHighlight(KEYWORD, ctx.PACKAGE().range)
//    }
//  }
//
//  override fun enterFunctionDeclaration(ctx: KotlinParser.FunctionDeclarationContext) {
//    highlights.addHighlight(KEYWORD, ctx.FUN().range)
//
//    highlights.addHighlight(FUNCTION_DECLARATION, ctx.identifier().range)
//  }
//
//  override fun enterStringLiteral(ctx: KotlinParser.StringLiteralContext) {
//    highlights.addHighlight(STRING, ctx.range)
//  }
//
//  override fun enterClassModifier(ctx: KotlinParser.ClassModifierContext) {
//    highlights.addHighlight(MODIFIER, ctx.range)
//  }
//
//  override fun enterClassDeclaration(ctx: KotlinParser.ClassDeclarationContext) {
//    highlights.addHighlight(CLASS_DECLARATION, ctx.CLASS().range)
//    highlights.addHighlight(CLASS_IDENTIFIER, ctx.simpleIdentifier().range)
//  }
//
//  override fun enterClassParameter(ctx: KotlinParser.ClassParameterContext) {
//    ctx.VAL()?.let {
//      highlights.addHighlight(VAL, ctx.VAL().range)
//    }
//    ctx.VAR()?.let {
//      highlights.addHighlight(VAR, ctx.VAR().range)
//    }
//    highlights.addHighlight(CLASS_PARAMETER, ctx.simpleIdentifier().range)
//  }
//
//  override fun enterAnnotation(ctx: KotlinParser.AnnotationContext) {
//    highlights.addHighlight(ANNOTATION, ctx.LabelReference().range)
//  }
//
//  override fun enterVisibilityModifier(ctx: KotlinParser.VisibilityModifierContext) {
//    highlights.addHighlight(MODIFIER, ctx.range)
//  }
//
//  override fun enterFunctionModifier(ctx: KotlinParser.FunctionModifierContext) {
//    highlights.addHighlight(MODIFIER, ctx.range)
//  }
//
//  override fun enterMemberModifier(ctx: KotlinParser.MemberModifierContext) {
//    highlights.addHighlight(MODIFIER, ctx.range)
//  }
//
//  override fun enterSimpleIdentifier(ctx: KotlinParser.SimpleIdentifierContext) {
//    highlights.addHighlight(SIMPLE_IDENTIFIER, ctx.range)
//  }
//
//  override fun visitTerminal(node: TerminalNode) {
//    when (node.symbol.type) {
//      KotlinParser.COMMA -> highlights.addHighlight(COMMA, node.range)
//      KotlinParser.VAL -> highlights.addHighlight(VAL, node.range)
//      KotlinParser.VAR -> highlights.addHighlight(VAR, node.range)
//      KotlinParser.FUN -> highlights.addHighlight(KEYWORD, node.range)
//      KotlinParser.OVERRIDE -> highlights.addHighlight(MODIFIER, node.range)
//      KotlinParser.IntegerLiteral -> highlights.addHighlight(INTEGER, node.range)
//      KotlinParser.LongLiteral -> highlights.addHighlight(INTEGER, node.range)
//      KotlinParser.HexLiteral -> highlights.addHighlight(INTEGER, node.range)
//    }
//  }
}
