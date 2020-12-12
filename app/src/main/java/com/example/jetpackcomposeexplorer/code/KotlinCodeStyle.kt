package com.example.jetpackcomposeexplorer.code

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import net.lab0.grammar.kotlin.KotlinHighlight
import net.lab0.grammar.kotlin.KotlinHighlight.ANNOTATION
import net.lab0.grammar.kotlin.KotlinHighlight.BRACKET
import net.lab0.grammar.kotlin.KotlinHighlight.CLASS_DECLARATION
import net.lab0.grammar.kotlin.KotlinHighlight.MODIFIER
import net.lab0.grammar.kotlin.KotlinHighlight.FUNCTION_DECLARATION
import net.lab0.grammar.kotlin.KotlinHighlight.KEYWORD
import net.lab0.grammar.kotlin.KotlinHighlight.NUMBER
import net.lab0.grammar.kotlin.KotlinHighlight.STRING

val annotationStyle = SpanStyle(Color(0xffBBB529))
val functionDeclarationStyle = SpanStyle(Color(0xffFFC66D))
val keywordStyle = SpanStyle(Color(0xffCC7832))
val normalStyle = SpanStyle(color = Color(0xffA9B7C6))
val numberStyle = SpanStyle(Color(0xff6897BB))
val stringStyle = SpanStyle(Color(0xff87664F))

val ijStyle = { it: KotlinHighlight ->
  when (it) {
    ANNOTATION -> annotationStyle
    BRACKET -> functionDeclarationStyle
    CLASS_DECLARATION -> keywordStyle
    FUNCTION_DECLARATION -> functionDeclarationStyle
    KEYWORD -> keywordStyle
    MODIFIER -> keywordStyle
    NUMBER -> numberStyle
    STRING -> stringStyle
    else -> normalStyle
  }
}
