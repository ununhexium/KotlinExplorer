package com.example.jetpackcomposeexplorer.framework.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeexplorer.framework.presentation.components.markdown.MDDocument
import org.commonmark.node.Node
import org.commonmark.parser.Parser

@Composable
fun InfoLessonPage(
    node: Node,
    nextPage: () -> Unit,
) {
  ConstraintLayout(
      modifier = Modifier
          .fillMaxSize()
          .padding(8.dp)
  ) {
    val (markdown, next) = createRefs()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .constrainAs(markdown) {
              top.linkTo(parent.top)
              start.linkTo(parent.start)
              end.linkTo(parent.end)
            }
    ) {
      MDDocument(document = node)
    }

    Button(
        onClick = nextPage,
        modifier = Modifier.constrainAs(next) {
          end.linkTo(parent.end, 8.dp)
          bottom.linkTo(parent.bottom, 8.dp)
        },
        colors = ButtonDefaults.buttonColors()
    ) {
      Icon(imageVector = Icons.Default.ArrowForward)
    }
  }
}

@Preview
@Composable
fun InfoLessonPagePreview() {
  MaterialTheme {
    Surface {
      Column {
        InfoLessonPage(
            Parser.builder().build().parse(
                """
                  |# Title
                  |
                  |1. One
                  |1. Two
                  |
                  |* true
                  |* false
                """.trimMargin()
            )
        ) {}
      }
    }
  }
}
