package net.lab0.jetpackcomposeexplorer.model.code

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import net.lab0.jetpackcomposeexplorer.framework.presentation.components.code.CodeStyle
import net.lab0.grammar.kotlin.KotlinHighlight
import net.lab0.grammar.kotlin.KotlinHighlight.*

val annotationStyle = SpanStyle(Color(0xffBBB529))
val commentStyle = SpanStyle(
    color = Color(0xff929792),
    fontStyle = FontStyle.Italic
)
val functionStyle = SpanStyle(Color(0xffFFC66D))
val keywordStyle = SpanStyle(Color(0xffCC7832))
val normalStyle = SpanStyle(Color(0xffA9B7C6))
val numberStyle = SpanStyle(Color(0xff6897BB))
val stringStyle = SpanStyle(Color(0xff87664F))

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
        STRING -> stringStyle
        else -> normalStyle
    }
}

val DefaultCodeStyle = object: CodeStyle<KotlinHighlight> {
    override val backgroundColor = Color(0xff2B2B2B)
    override val gutterBackgroundColor = Color(0xFF313335)
    override val placeholderBackgroundColor = Color(0xFFA9B2B8)
    override val activePlaceholderBackgroundColor = Color(0xFFD9E73F)

    override val foregroundColor = Color(0xffA9B7C6)
    override val gutterForegroundColor = Color(0xFF5B6265)
    override val placeholderForegroundColor = Color(0xFF000000)
    override val activePlaceholderForegroundColor = Color(0xFF000000)

    override val textStyler = ijStyle
}
