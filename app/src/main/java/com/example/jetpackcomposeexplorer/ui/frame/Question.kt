package com.example.jetpackcomposeexplorer.ui.frame

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.example.jetpackcomposeexplorer.R
import com.example.jetpackcomposeexplorer.ui.frame.preview.PreviewTopBar

@Composable
fun Question(
    question: @Composable () -> Unit,
    vararg answers: @Composable () -> Unit = arrayOf() // TODO rm default, must be min 1 answer
) {

  Column(
      modifier = Modifier
          .fillMaxWidth()
          .fillMaxHeight(),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
  ) {
    ConstraintLayout(
        modifier = Modifier
            .weight(1f)
            .fillMaxWidth()
    ) {
      val surface = createRef()

      Surface(
          elevation = 5.dp,
          modifier = Modifier.constrainAs(surface) {
            centerVerticallyTo(parent)
            centerHorizontallyTo(parent)
          }
      ) {
        question()
      }
    }

    val weighted = Modifier.weight(1f)
    Row(
        weighted
            .fillMaxWidth()
            .fillMaxHeight()
            .border(BorderStroke(2.dp, Color.Green))
    ) {
      Answers(answers = answers)
    }

    Button(onClick = {}) {
      Text("Validate")
    }
  }
}

@Preview
@Composable
fun PreviewQuestion() {
  MaterialTheme {
    Scaffold(
        bodyContent = {
          Question(
              question = {
                Row {
                  Image(
                      asset = imageResource(id = R.drawable.beach),
                      contentScale = ContentScale.Crop,
                      modifier = Modifier
                          .padding(8.dp)
                          .clip(CircleShape)
                          .size(100.dp)
//                      modifier = Modifier.clip(CircleShape).size(100.dp)
//                        modifier = Modifier.clip(CircleShape).size(50.dp)
                  )
                  Text(
                      text = "Hi!",
                      modifier = Modifier.align(Alignment.CenterVertically),
                      style = MaterialTheme.typography.h2
                  )
                }
              },
              answers = arrayOf(
                  { Text("Hello") },
                  { Icon(Icons.Default.Check) },
                  { Text("3") },
                  { Icon(Icons.Default.ArrowDropDown) },
              )
          )
        },
        topBar = { PreviewTopBar() },
    )
  }
}
