package com.example.jetpackcomposeexplorer.code

import com.example.jetpackcomposeexplorer.code.KotlinHighlight.ANNOTATION
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.CLASS_DECLARATION
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.CLASS_IDENTIFIER
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.CLASS_PARAMETER
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.COMMA
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.FUNCTION_DECLARATION
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.KEYWORD
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.MODIFIER
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.STRING
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.VAL
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.VAR
import net.lab0.grammar.kotlin.KotlinParser
import net.lab0.grammar.kotlin.KotlinParserBaseListener
import org.antlr.v4.runtime.tree.TerminalNode

class KotlinSyntaxHighlighterVisitor : KotlinParserBaseListener() {
  val highlights = Highlights<KotlinHighlight>()

  override fun enterPackageHeader(ctx: KotlinParser.PackageHeaderContext) {
    ctx.PACKAGE()?.let {
      highlights.addHighlight(KEYWORD, ctx.PACKAGE().range)
    }
  }

  override fun enterFunctionDeclaration(ctx: KotlinParser.FunctionDeclarationContext) {
    highlights.addHighlight(KEYWORD, ctx.FUN().range)

    highlights.addHighlight(FUNCTION_DECLARATION, ctx.identifier().range)
  }

  override fun enterStringLiteral(ctx: KotlinParser.StringLiteralContext) {
    highlights.addHighlight(STRING, ctx.range)
  }

  override fun enterClassModifier(ctx: KotlinParser.ClassModifierContext) {
    highlights.addHighlight(MODIFIER, ctx.range)
  }

  override fun enterClassDeclaration(ctx: KotlinParser.ClassDeclarationContext) {
    highlights.addHighlight(CLASS_DECLARATION, ctx.CLASS().range)
    highlights.addHighlight(CLASS_IDENTIFIER, ctx.simpleIdentifier().range)
  }

  override fun enterClassParameter(ctx: KotlinParser.ClassParameterContext) {
    ctx.VAL()?.let {
      highlights.addHighlight(VAL, ctx.VAL().range)
    }
    ctx.VAR()?.let {
      highlights.addHighlight(VAR, ctx.VAR().range)
    }
    highlights.addHighlight(CLASS_PARAMETER, ctx.simpleIdentifier().range)
  }

  override fun enterAnnotation(ctx: KotlinParser.AnnotationContext) {
    highlights.addHighlight(ANNOTATION, ctx.LabelReference().range)
  }

  override fun enterVisibilityModifier(ctx: KotlinParser.VisibilityModifierContext) {
    highlights.addHighlight(MODIFIER, ctx.range)
  }

  override fun enterFunctionModifier(ctx: KotlinParser.FunctionModifierContext) {
    highlights.addHighlight(MODIFIER, ctx.range)
  }

  override fun enterMemberModifier(ctx: KotlinParser.MemberModifierContext) {
    highlights.addHighlight(MODIFIER, ctx.range)
  }

  override fun visitTerminal(node: TerminalNode) {
    when (node.symbol.tokenIndex) {
      KotlinParser.COMMA -> highlights.addHighlight(COMMA, node.range)
      KotlinParser.VAL -> highlights.addHighlight(VAL, node.range)
      KotlinParser.VAR -> highlights.addHighlight(VAR, node.range)
      KotlinParser.FUN -> highlights.addHighlight(KEYWORD, node.range)
      KotlinParser.OVERRIDE -> highlights.addHighlight(MODIFIER, node.range)
    }
  }
}
