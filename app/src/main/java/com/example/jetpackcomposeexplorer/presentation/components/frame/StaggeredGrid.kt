package com.example.jetpackcomposeexplorer.presentation.components.frame

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeexplorer.presentation.components.code.AnswerChip

@Composable
fun StaggeredGrid(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
  Layout(
      modifier = modifier,
      content = content,
  ) { measurables, constraints ->
    val maxWidth = constraints.maxWidth

    // cut items by width
    val groupedPlaceables = mutableListOf(mutableListOf<Placeable>())

    measurables.mapIndexed { index, measurable ->
      measurable.measure(constraints)
    }.forEach { placeable ->
      /*
       * Need a new row to accommodate for the next element,
       * except if this line is already empty (element is too long anyway)
       */
      val lastLine = groupedPlaceables.last()
      if (placeable.width + lastLine.sumBy { it.width } > maxWidth && lastLine.isNotEmpty()) {
        groupedPlaceables.add(mutableListOf())
      }
      groupedPlaceables.last().add(placeable)
    }

    val widths = groupedPlaceables.map {
      it.sumBy { it.width }
    }

    val targetWidth = widths.maxByOrNull { it }
    val targetHeight = groupedPlaceables.sumOf {
      it.maxOf { it.height }
    }

    val actualWidth = targetWidth
        ?.coerceIn(constraints.minWidth..constraints.maxWidth)
        ?: constraints.minWidth
    val actualHeight = (targetHeight
        ?.coerceIn(constraints.minHeight..constraints.maxHeight)
        ?: constraints.minHeight)

    layout(
        width = actualWidth,
        height = actualHeight,
    ) {
      var currentWidth = 0
      var currentHeight = 0
      var currentRow = 0

      groupedPlaceables.forEach { placeableList ->
        placeableList.forEach { placeable ->
          placeable.placeRelative(
              IntOffset(
                  x = currentWidth + (actualWidth - widths[currentRow]) / 2,
                  y = currentHeight
              )
          )
          currentWidth += placeable.width
        }
        currentWidth = 0
        currentRow++
        currentHeight += placeableList.maxOf { it.height }
      }
    }
  }
}


@Preview
@Composable
fun StaggeredGridPreview() {
  MaterialTheme {
    Surface(
        color = Color(0xFF4CAF50)
    ) {
      Column(
          modifier = Modifier.padding(20.dp)
      ) {
        val modifier = Modifier.padding(4.dp)
        Surface(
            color = MaterialTheme.colors.surface
        ) {
          StaggeredGrid {
            (0..10).forEach {
              Row(
                  modifier = modifier,
              ) {
                AnswerChip(it.toString(), true) {}
              }
            }

            Row(
                modifier = modifier,
            ) {
              AnswerChip("ksjhdrfgsfulkjsgkjhluihasdf", true) {}
            }

            Row(
                modifier = modifier,
            ) {
              AnswerChip("6874506872089572345892340", true) {}
            }
          }
        }
      }
    }
  }
}