package com.example.jetpackcomposeexplorer.framework.ui.frame

import androidx.compose.foundation.Image
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import com.example.jetpackcomposeexplorer.R
import kotlin.math.floor

/**
 * @param visibleIndex The index of the visible index.
 * Works similarly to the index in a list but accepts floats to
 * show the transition from one item to the next.
 */
@Composable
fun Slider(
    modifier: Modifier = Modifier,
    visibleIndex: Float = 0f,
    children: @Composable () -> Unit,
) {
  Layout(children, modifier) { measurables, constraints ->

    val previousIndex = floor(visibleIndex).toInt()
    val nextIndex = previousIndex + 2
    val currentRatio = visibleIndex - floor(visibleIndex)

    val placeables = measurables
        .subList(previousIndex, nextIndex)
        .map { measurable ->
          measurable.measure(constraints)
        }

    layout(constraints.maxWidth, constraints.maxHeight) {
      val placeable0 = placeables[0]
      val emptySpaceX0 = constraints.maxWidth - placeable0.width
      val emptySpaceY0 = constraints.maxHeight - placeable0.height
      val xOffset0 = (constraints.maxWidth * currentRatio).toInt()

      placeable0.placeRelative(
          IntOffset(
              (emptySpaceX0 / 2) - xOffset0,
              emptySpaceY0 / 2
          )
      )

      val placeable1 = placeables[1]
      val emptySpaceX1 = constraints.maxWidth - placeable1.width
      val emptySpaceY1 = constraints.maxHeight - placeable1.height
      val xOffset1 = (xOffset0 + constraints.maxWidth * (1f - currentRatio)).toInt()

      placeable1.placeRelative(
          IntOffset(
              (emptySpaceX1 / 2) - xOffset0 + xOffset1,
              emptySpaceY1 / 2
          )
      )
    }
  }
}

@Preview
@Composable
fun PreviewSlider() {
  MaterialTheme {
    Surface {
      Slider(visibleIndex = 2.5f) {
        Text("A")
        Text("B")
        Icon(Icons.Default.Phone, tint = Color.Red)
        Image(bitmap = imageResource(id = R.drawable.beach))
      }
    }
  }
}
