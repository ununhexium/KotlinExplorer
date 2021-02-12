package net.lab0.jetpackcomposeexplorer.framework.presentation.components.markdown

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.lab0.jetpackcomposeexplorer.model.code.DefaultCodeStyle
import net.lab0.jetpackcomposeexplorer.model.code.extractHighlightsAndAnnotate
import net.lab0.jetpackcomposeexplorer.framework.presentation.components.code.KotlinCode
import net.lab0.jetpackcomposeexplorer.framework.presentation.components.code.Monospace
import org.commonmark.node.Document
import org.commonmark.node.FencedCodeBlock
import java.util.*


@Composable
fun MDFencedCodeBlock(
    fencedCodeBlock: FencedCodeBlock,
    modifier: Modifier = Modifier,
) {
  val padding = if (fencedCodeBlock.parent is Document) 8.dp else 0.dp
  Box(modifier = modifier.padding(bottom = padding, start = 8.dp)) {
    when (fencedCodeBlock.info.toLowerCase(Locale.ROOT)) {
      "kotlin" ->
        KotlinCode(
            extractHighlightsAndAnnotate(
                fencedCodeBlock.literal.trim(),
                DefaultCodeStyle.textStyler
            ),
            codeStyle = DefaultCodeStyle,
        )
      "kotlin_lines" ->
        KotlinCode(
            extractHighlightsAndAnnotate(
                fencedCodeBlock.literal.trim(),
                DefaultCodeStyle.textStyler
            ),
            codeStyle = DefaultCodeStyle,
            showLineNumbers = true,
        )
      else ->
        Monospace(
            text = fencedCodeBlock.literal.trim(),
            modifier = Modifier
                .background(Color.LightGray, MaterialTheme.shapes.medium)
                .padding(start = 4.dp, end = 4.dp),
        )
    }
  }
}

@Preview
@Composable
fun MDFencedCodeBlockPreview() {
  MaterialTheme {
    Surface {
      Column {
        MDDocument(
            parseMD(
                """
                  |Hello `World`
                  |
                  |```
                  |println("foo")
                  |```
                  |
                  |```
                  |val i1 = 1
                  |val i2 = 2
                  |val i3 = 3
                  |```
                """.trimMargin()
            )
        )
      }
    }
  }
}
