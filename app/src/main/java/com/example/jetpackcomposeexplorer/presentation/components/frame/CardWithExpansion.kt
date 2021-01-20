package com.example.jetpackcomposeexplorer.presentation.components.frame

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


// design: https://www.designcrowd.de/design/15152008
@Composable
fun CardWithExpansion(
    card: @Composable RowScope.() -> Unit,
    expansion: @Composable RowScope.() -> Unit,
) {
  // TODO: the card should be above the expansion, in a box, with
  ConstraintLayout(
      modifier = Modifier.fillMaxWidth(),
  ) {
    val (above, below) = createRefs()

    Row(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)
            .fillMaxWidth()
            .constrainAs(below) {
              top.linkTo(above.bottom)
            },
    ) {
      Card(
          shape = MaterialTheme.shapes.medium.copy(
              topLeft = CornerSize(0),
              topRight = CornerSize(0),
          ),
          elevation = 4.dp,
      ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            content = expansion
        )
      }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .constrainAs(above) {
              top.linkTo(parent.top)
              start.linkTo(parent.start)
              end.linkTo(parent.end)
            },
        shape = MaterialTheme.shapes.medium,
        elevation = 4.dp,
    ) {
      Row(
          verticalAlignment = Alignment.CenterVertically,
          content = card
      )
    }
  }
}

@Preview
@Composable
fun ChaptersListPreview() {
  MaterialTheme {
    Surface(
        color = Color(0xFF4CAF50)
    ) {
      Column(
          modifier = Modifier.padding(20.dp)
      ) {
        CardWithExpansion(
            card = {
              ChapterCard(
                  dummyChapter1
              )
            }
        ) {
          Text(
              "SHOW MORE",
              color = MaterialTheme.colors.secondary,
              style = MaterialTheme.typography.body1,
          )
        }
      }
    }
  }
}
