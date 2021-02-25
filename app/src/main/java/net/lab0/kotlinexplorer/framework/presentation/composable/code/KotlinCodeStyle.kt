package net.lab0.kotlinexplorer.framework.presentation.composable.code

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import net.lab0.grammar.kotlin.KotlinHighlight
import net.lab0.grammar.kotlin.KotlinHighlight.ANNOTATION
import net.lab0.grammar.kotlin.KotlinHighlight.BRACKET
import net.lab0.grammar.kotlin.KotlinHighlight.CLASS_DECLARATION
import net.lab0.grammar.kotlin.KotlinHighlight.COMMENT
import net.lab0.grammar.kotlin.KotlinHighlight.FUNCTION
import net.lab0.grammar.kotlin.KotlinHighlight.KEYWORD
import net.lab0.grammar.kotlin.KotlinHighlight.MODIFIER
import net.lab0.grammar.kotlin.KotlinHighlight.NUMBER
import net.lab0.grammar.kotlin.KotlinHighlight.OPERATOR
import net.lab0.grammar.kotlin.KotlinHighlight.STRING
import net.lab0.grammar.kotlin.KotlinHighlight.STRING_ESCAPED_CHARACTER


val backgroundColor = Color(0xff2B2B2B)
val stringColor = Color(0xFFAC6CAF)
val escapedStringColor = Color(0xFF6CADAF)

val yellow = Color(0xffBBB529)

val annotationStyle = SpanStyle(color = yellow, background = backgroundColor)
val commentStyle = SpanStyle(
    color = Color(0xff929792),
    background = backgroundColor,
    fontStyle = FontStyle.Italic,
)
val functionStyle = SpanStyle(color = Color(0xffFFC66D), background = backgroundColor)
val keywordStyle = SpanStyle(color = Color(0xffCC7832), background = backgroundColor)
val normalStyle = SpanStyle(color = Color(0xffA9B7C6), background = backgroundColor)
val numberStyle = SpanStyle(color = Color(0xff6897BB), background = backgroundColor)
val operatorStyle = SpanStyle(color = yellow, background = backgroundColor)
val stringStyle = SpanStyle(color = stringColor, background = backgroundColor)
val escapedStringStyle = SpanStyle(color = escapedStringColor, background = backgroundColor)

val ijStyle = { it: KotlinHighlight ->
  when (it) {
      ANNOTATION -> annotationStyle
      BRACKET -> functionStyle
      CLASS_DECLARATION -> keywordStyle
      COMMENT -> commentStyle
      FUNCTION -> functionStyle
      KEYWORD -> keywordStyle
      MODIFIER -> keywordStyle
      NUMBER -> numberStyle
      OPERATOR -> operatorStyle
      STRING -> stringStyle
      STRING_ESCAPED_CHARACTER -> escapedStringStyle
    else -> normalStyle
  }
}

val DefaultCodeStyle = object : CodeStyle<KotlinHighlight> {
  override val backgroundColor = Color(0xff2B2B2B)
  override val gutterBackgroundColor = Color(0xFF313335)
  override val placeholderBackgroundColor = Color(0xFFA9B2B8)
  override val activePlaceholderBackgroundColor = Color(0xFFD9E73F)

  override val foregroundColor = Color(0xffA9B7C6)
  override val gutterForegroundColor = Color(0xFF9FACB2)
  override val placeholderForegroundColor = Color(0xFF000000)
  override val activePlaceholderForegroundColor = Color(0xFF000000)

  override val textStyler = ijStyle
}
