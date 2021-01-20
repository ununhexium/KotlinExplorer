package com.example.jetpackcomposeexplorer.presentation.components.frame

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeexplorer.model.course.data.kotlin.kotlin
import java.text.NumberFormat


// design: https://www.designcrowd.de/design/15152008
@Composable
fun CardWithExpansion(
    chapter: ChapterData,
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
            modifier = Modifier.fillMaxWidth().padding(4.dp),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
          expansion()
        }
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
      ) {
        Box(
            modifier = Modifier.padding(8.dp),
            contentAlignment = Alignment.Center,
        ) {
          CircularProgressIndicator(
              progress = chapter.completion,
              modifier = Modifier.padding(8.dp).preferredSize(64.dp)
          )
          Text(
              NumberFormat.getPercentInstance().format(chapter.completion),
              color = MaterialTheme.colors.primary,
              style = MaterialTheme.typography.body1,
          )
        }
        Column(
            verticalArrangement = Arrangement.Center,
        ) {
          Text(
              text = chapter.title,
              style = MaterialTheme.typography.h6,
          )
          Text(
              text = "${chapter.lessons.size} LESSONS",
              style = MaterialTheme.typography.body1,
              color = Color.Gray,
          )
        }
      }
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
      Column(modifier = Modifier.padding(20.dp)) {
        CardWithExpansion(
            kotlin.map {
              ChapterData(
                  it.title,
                  0.33f,
                  it.lessons.map { _ ->
                    LessonData("Foo")
                  }
              )
            }.first()
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
