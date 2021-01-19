package com.example.jetpackcomposeexplorer.presentation.components.markdown

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.gesture.tapGestureFilter
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.AmbientUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeexplorer.presentation.theme.sourceCodeFontFamily
import org.commonmark.node.BlockQuote
import org.commonmark.node.BulletList
import org.commonmark.node.Code
import org.commonmark.node.Document
import org.commonmark.node.Emphasis
import org.commonmark.node.FencedCodeBlock
import org.commonmark.node.HardLineBreak
import org.commonmark.node.Heading
import org.commonmark.node.Image
import org.commonmark.node.IndentedCodeBlock
import org.commonmark.node.Link
import org.commonmark.node.ListBlock
import org.commonmark.node.Node
import org.commonmark.node.OrderedList
import org.commonmark.node.Paragraph
import org.commonmark.node.StrongEmphasis
import org.commonmark.node.Text
import org.commonmark.node.ThematicBreak

/**
 * These functions will render a tree of Markdown nodes parsed with CommonMark.
 * Images will be rendered using Chris Banes Accompanist library (which uses Coil)
 *
 * To use this, you need the following two dependencies:
 * implementation "com.atlassian.commonmark:commonmark:0.15.2"
 * implementation "dev.chrisbanes.accompanist:accompanist-coil:0.2.0"
 *
 * The following is an example of how to use this:
 * ```
 * val parser = Parser.builder().build()
 * val root = parser.parse(MIXED_MD) as Document
 * val markdownComposer = MarkdownComposer()
 *
 * MarkdownComposerTheme {
 *    MDDocument(root)
 * }
 * ```
 */
private const val TAG_URL = "url"
private const val TAG_IMAGE_URL = "imageUrl"

@Composable
fun MDDocument(document: Node) {
  MDBlockChildren(document)
}


@Composable
fun MDParagraph(paragraph: Paragraph, modifier: Modifier = Modifier) {
  if (paragraph.firstChild is Image && paragraph.firstChild == paragraph.lastChild) {
    // Paragraph with single image
    MDImage(paragraph.firstChild as Image, modifier)
  } else {
    val padding = if (paragraph.parent is Document) 8.dp else 0.dp
    Box(modifier = modifier.padding(bottom = padding)) {
      val styledText = buildAnnotatedString {
        pushStyle(MaterialTheme.typography.body1.toSpanStyle())
        appendMarkdownChildren(paragraph, MaterialTheme.colors)
        pop()
      }
      MarkdownText(styledText, MaterialTheme.typography.body1)
    }
  }
}

@Composable
fun MDImage(image: Image, modifier: Modifier = Modifier) {
  Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
    // TODO image
  }
}

@Composable
fun MDBulletList(bulletList: BulletList, modifier: Modifier = Modifier) {
  val marker = bulletList.bulletMarker
  MDListItems(bulletList, modifier = modifier) {
    val text = buildAnnotatedString {
      pushStyle(MaterialTheme.typography.body1.toSpanStyle())
      append("$marker ")
      appendMarkdownChildren(it, MaterialTheme.colors)
      pop()
    }
    MarkdownText(text, MaterialTheme.typography.body1, modifier)
  }
}


@Composable
fun MDListItems(
    listBlock: ListBlock,
    modifier: Modifier = Modifier,
    item: @Composable (node: Node) -> Unit,
) {
  val bottom = if (listBlock.parent is Document) 8.dp else 0.dp
  val start = if (listBlock.parent is Document) 0.dp else 8.dp
  Column(modifier = modifier.padding(bottom = bottom, start = start)) {
    var listItem = listBlock.firstChild
    while (listItem != null) {
      var child = listItem.firstChild
      while (child != null) {
        when (child) {
          is BulletList -> MDBulletList(child, modifier)
          is OrderedList -> MDOrderedList(child, modifier)
          else -> item(child)
        }
        child = child.next
      }
      listItem = listItem.next
    }
  }
}

@Composable
fun MDBlockQuote(blockQuote: BlockQuote, modifier: Modifier = Modifier) {
  val color = MaterialTheme.colors.onBackground
  Box(
      modifier = modifier
          .drawBehind {
            drawLine(
                color = color,
                strokeWidth = 2f,
                start = Offset(12.dp.value, 0f),
                end = Offset(12.dp.value, size.height)
            )
          }
          .padding(start = 16.dp, top = 4.dp, bottom = 4.dp)
  ) {
    val text = buildAnnotatedString {
      pushStyle(
          MaterialTheme.typography.body1.toSpanStyle()
              .plus(SpanStyle(fontStyle = FontStyle.Italic))
      )
      appendMarkdownChildren(blockQuote, MaterialTheme.colors)
      pop()
    }
    Text(text, modifier)
  }
}

@Composable
fun MDIndentedCodeBlock(
    indentedCodeBlock: IndentedCodeBlock,
    modifier: Modifier = Modifier,
) {
  // Ignored
}

@Composable
fun MDThematicBreak(
    thematicBreak: ThematicBreak,
    modifier: Modifier = Modifier,
) {
  //Ignored
}

@Composable
fun MDBlockChildren(parent: Node) {
  var child = parent.firstChild
  while (child != null) {
    when (child) {
      is BlockQuote -> MDBlockQuote(child)
      is ThematicBreak -> MDThematicBreak(child)
      is Heading -> MDHeading(child)
      is Paragraph -> MDParagraph(child)
      is FencedCodeBlock -> MDFencedCodeBlock(child)
      is IndentedCodeBlock -> MDIndentedCodeBlock(child)
      is Image -> MDImage(child)
      is BulletList -> MDBulletList(child)
      is OrderedList -> MDOrderedList(child)
    }
    child = child.next
  }
}

fun AnnotatedString.Builder.appendMarkdownChildren(
    parent: Node, colors: Colors,
) {
  var child = parent.firstChild
  while (child != null) {
    when (child) {
      is Paragraph -> appendMarkdownChildren(child, colors)
      is Text -> append(child.literal)
      is Image -> appendInlineContent(TAG_IMAGE_URL, child.destination)
      is Emphasis -> {
        pushStyle(SpanStyle(fontStyle = FontStyle.Italic))
        appendMarkdownChildren(child, colors)
        pop()
      }
      is StrongEmphasis -> {
        pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
        appendMarkdownChildren(child, colors)
        pop()
      }
      is Code -> {
        pushStyle(TextStyle(fontFamily = sourceCodeFontFamily, background = Color.LightGray).toSpanStyle())
        append(child.literal)
        pop()
      }
      is HardLineBreak -> {
        append("\n")
      }
      is Link -> {
        val underline = SpanStyle(
            colors.primary,
            textDecoration = TextDecoration.Underline
        )
        pushStyle(underline)
        pushStringAnnotation(TAG_URL, child.destination)
        appendMarkdownChildren(child, colors)
        pop()
        pop()
      }
    }
    child = child.next
  }
}

@Composable
fun MarkdownText(
    text: AnnotatedString,
    style: TextStyle,
    modifier: Modifier = Modifier,
) {
  val uriHandler = AmbientUriHandler.current
  val layoutResult = remember { mutableStateOf<TextLayoutResult?>(null) }

  Text(
      text = text,
      modifier = modifier.tapGestureFilter { pos ->
        layoutResult.value?.let { layoutResult ->
          val position = layoutResult.getOffsetForPosition(pos)
          text.getStringAnnotations(position, position)
              .firstOrNull()
              ?.let { sa ->
                if (sa.tag == TAG_URL) {
                  uriHandler.openUri(sa.item)
                }
              }
        }
      },
      style = style,
      inlineContent = mapOf(
          TAG_IMAGE_URL to InlineTextContent(
              Placeholder(
                  style.fontSize,
                  style.fontSize,
                  PlaceholderVerticalAlign.Bottom
              )
          ) {
            // TODO image
          }
      ),
      onTextLayout = { layoutResult.value = it }
  )
}
