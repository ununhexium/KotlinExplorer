package net.lab0.kotlinexplorer.framework.presentation.components.markdown

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import org.commonmark.node.OrderedList


@Composable
fun MDOrderedList(orderedList: OrderedList, modifier: Modifier = Modifier) {
  var number = orderedList.startNumber
  val delimiter = orderedList.delimiter
  MDListItems(orderedList, modifier) {
    val text = buildAnnotatedString {
      pushStyle(MaterialTheme.typography.body1.toSpanStyle())
      append("${number++}$delimiter ")
      appendMarkdownChildren(it, MaterialTheme.colors)
      pop()
    }
    MarkdownText(text, MaterialTheme.typography.body1, modifier)
  }
}

@Preview
@Composable
fun MDOrderedListPreview() {
  MaterialTheme {
    Column {
      MDDocument(
          parseMD(
              """
                |Hello you
                |
                |Bye me. 
                |Bye you
                |
                |1. One
                |1. Two
              """.trimMargin()
          )
      )
    }
  }
}

@Preview
@Composable
fun MDTextPreview() {
  MaterialTheme {
    Column {
      MarkdownText(
          buildAnnotatedString {
            pushStyle(MaterialTheme.typography.body1.toSpanStyle())
            append(
                """
                  |1. One
                  |2. Two
                """.trimMargin()
            )
            pop()
          },
          MaterialTheme.typography.body1
      )
    }
  }
}