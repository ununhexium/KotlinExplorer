package net.lab0.kotlinexplorer.framework.presentation.composable.code

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

internal class AnnotatedStringExtensionsKtTest {
  @Test
  fun `don't invert if the range doesn't intersect an style span`() {
    // given
    val style = SpanStyle(color = Color.Red, background = Color.Black)
    val s = buildAnnotatedString {
      pushStyle(style)
      append("foo")
      pop()
    }

    // when
    val i = s.invertForegroundBackgroundColors(-10 .. -5)

    // then
    assertThat(i.spanStyles).containsExactly(
      AnnotatedString.Range(style, 0, 3),
    )
  }

  @Test
  fun `can invert the color simplest case`() {
    // given
    val style = SpanStyle(color = Color.Red, background = Color.Black)
    val s = buildAnnotatedString {
      pushStyle(style)
      append("foo")
      pop()
    }

    // when
    val i = s.invertForegroundBackgroundColors(0 .. 2)

    // then

    assertThat(i.spanStyles).containsExactly(
      AnnotatedString.Range(
        style.copy(color = style.background, background = style.color),
        0,
        3
      ),
      AnnotatedString.Range(
        style,
        0,
        3
      )
    )
  }

  @Test
  fun `can invert the color when the range is spanning over 2 formats`() {
    // given
    //0123456
    //foo bar
    val redStyle = SpanStyle(color = Color.Red, background = Color.Black)
    val blueStyle = SpanStyle(color = Color.Blue, background = Color.White)
    val s = buildAnnotatedString {
      pushStyle(redStyle)
      append("foo")
      pop()
      append(" ")
      pushStyle(blueStyle)
      append("bar")
      pop()
    }

    // when
    val i = s.invertForegroundBackgroundColors(2 until 5)

    // then
    assertThat(i.spanStyles).containsExactly(
      AnnotatedString.Range(redStyle, 0, 3),
      AnnotatedString.Range(
        redStyle.copy(
          color = redStyle.background,
          background = redStyle.color
        ), 2, 3
      ),
      AnnotatedString.Range(
        blueStyle.copy(
          color = blueStyle.background,
          background = blueStyle.color
        ), 4, 5
      ),
      AnnotatedString.Range(blueStyle, 4, 7),
    )
  }

  @Test
  fun `can invert the color when the range is inside a style span`() {
    // given
    val style = SpanStyle(color = Color.Red, background = Color.Black)
    val s = buildAnnotatedString {
      pushStyle(style)
      append("foo")
      pop()
    }

    // when
    val i = s.invertForegroundBackgroundColors(1 .. 1)

    // then
    assertThat(i.spanStyles).containsExactly(
      AnnotatedString.Range(style, 0, 3),
      AnnotatedString.Range(
        style.copy(color = style.background, background = style.color),
        1,
        2
      ),
    )
  }

  @Test
  fun `can invert the color when the range is overlapping a style span`() {
    // given
    val style = SpanStyle(color = Color.Red, background = Color.Black)
    val s = buildAnnotatedString {
      append(" ")
      pushStyle(style)
      append("foo")
      pop()
      append(" ")
    }

    // when
    val i = s.invertForegroundBackgroundColors(0 .. 5)

    // then
    assertThat(i.spanStyles).containsExactly(
      AnnotatedString.Range(
        style.copy(color = style.background, background = style.color),
        // not 0..5 because
        1,
        4,
      ),
      AnnotatedString.Range(
        style,
        1,
        4,
      )
    )
  }
}
