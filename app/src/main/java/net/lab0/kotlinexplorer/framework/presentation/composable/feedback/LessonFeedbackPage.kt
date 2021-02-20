package net.lab0.kotlinexplorer.framework.presentation.composable.feedback

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.lab0.kotlinexplorer.framework.presentation.composable.BigVerticalSpacer
import net.lab0.kotlinexplorer.framework.presentation.composable.code.input.ControlBar

@Composable
fun <T> LessonFeedbackPage(
    durationTopic: EvaluationTopic<T>,
    difficultyTopic: EvaluationTopic<T>,
    onValidate: () -> Unit,
    onSkip: () -> Unit,
) where T : Any {
  Column(
      modifier = Modifier
          .fillMaxSize()
          .padding(8.dp),
      verticalArrangement = Arrangement.Bottom
  ) {

    RadioFeedbackTopic(
        topic = durationTopic.topic,
        options = durationTopic.options,
        onSelection = { durationTopic.onSelection }
    )
    BigVerticalSpacer()

    RadioFeedbackTopic(
        topic = difficultyTopic.topic,
        options = difficultyTopic.options,
        onSelection = { difficultyTopic.onSelection }
    )
    BigVerticalSpacer()

    ControlBar(
        startItems = {
          Button(
              colors = ButtonDefaults.outlinedButtonColors(
                  MaterialTheme.colors.surface,
                  MaterialTheme.colors.primary,
              ),
              onClick = onSkip,
          ) {
            Text(text = "Skip")
          }
        }
    ) {
      Button(onClick = onValidate) {
        Icon(imageVector = Icons.Default.CheckCircle)
      }
    }
  }
}

@Preview
@Composable
fun LessonFeedbackPagePreview() {
  MaterialTheme {
    Surface(
        color = Color(0xFF4CAF50)
    ) {
      Column(
          modifier = Modifier.padding(20.dp)
      ) {
        Surface(
            color = MaterialTheme.colors.surface
        ) {
          LessonFeedbackPage(
              EvaluationTopic(
                  "Lesson duration",
                  listOf(
                      "Too short",
                      "Balanced",
                      "Too long",
                      "No Answer",
                  )
              ) {},
              EvaluationTopic(
                  "Lesson difficulty",
                  listOf(
                      "Too easy",
                      "Balanced",
                      "Too hard",
                      "No Answer",
                  )
              ) {},
              {},
              {},
          )
        }
      }
    }
  }
}
