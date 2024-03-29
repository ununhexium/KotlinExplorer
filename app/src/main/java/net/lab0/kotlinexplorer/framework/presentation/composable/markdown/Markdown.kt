package net.lab0.kotlinexplorer.framework.presentation.composable.markdown

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
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
import net.lab0.kotlinexplorer.framework.ui.theme.sourceCodeFontFamily
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
fun MDImage(image: Image, modifier: Modifier = Modifier) {
  Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
    // not implemented
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
      is Text -> {
        if (parent is Paragraph && child != parent.lastChild) {
          append(child.literal + " ")
        } else {
          append(child.literal)
        }
      }
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
        pushStyle(
          TextStyle(
            fontFamily = sourceCodeFontFamily,
            color = colors.secondary,
          ).toSpanStyle()
        )
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
  // Support for links was here. Deleted when migrating to compose beta01
  val layoutResult = remember { mutableStateOf<TextLayoutResult?>(null) }

  Text(
    text = text,
    modifier = modifier,
    style = style,
    inlineContent = mapOf(
      TAG_IMAGE_URL to InlineTextContent(
        Placeholder(
          style.fontSize,
          style.fontSize,
          PlaceholderVerticalAlign.Bottom
        )
      ) {
        // image not implemented
      }
    ),
    onTextLayout = { layoutResult.value = it }
  )
}
