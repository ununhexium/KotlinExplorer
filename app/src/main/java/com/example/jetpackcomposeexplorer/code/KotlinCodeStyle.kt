package com.example.jetpackcomposeexplorer.code

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.ANNOTATION
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.CLASS_DECLARATION
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.MODIFIER
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.FUNCTION_DECLARATION
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.KEYWORD
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.STRING
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.VAL
import com.example.jetpackcomposeexplorer.code.KotlinHighlight.VAR

val keywordStyle = SpanStyle(Color(0xffCC7832))
val stringStyle = SpanStyle(Color(0xff87664F))
val functionDeclarationStyle = SpanStyle(Color(0xffFFC66D))
val annotationStyle = SpanStyle(Color(0xffBBB529))

val ijStyle = { it: KotlinHighlight ->
  when (it) {
    ANNOTATION -> annotationStyle
    CLASS_DECLARATION -> keywordStyle
    MODIFIER -> keywordStyle
    FUNCTION_DECLARATION -> functionDeclarationStyle
    KEYWORD -> keywordStyle
    STRING -> stringStyle
    VAL -> keywordStyle
    VAR -> keywordStyle
    else -> SpanStyle(color = Color(0xffA9B7C6))
  }
}
