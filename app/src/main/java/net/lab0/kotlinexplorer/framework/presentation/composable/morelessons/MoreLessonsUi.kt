package net.lab0.kotlinexplorer.framework.presentation.composable.morelessons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.lab0.kotlinexplorer.framework.presentation.composable.BigVerticalSpacer
import net.lab0.kotlinexplorer.framework.presentation.composable.DefaultVerticalSpacer
import net.lab0.kotlinexplorer.framework.presentation.composable.code.input.ControlBar
import net.lab0.kotlinexplorer.framework.presentation.composable.form.RadioSelection
import net.lab0.kotlinexplorer.framework.presentation.composable.form.rememberRadioState

@Composable
fun MoreLessonsUi(
  onValidate: (liking: String?, whyMoreLessons: String?, comment: String?) -> Unit,
) {
  Column(
    modifier = Modifier.fillMaxWidth(),
    verticalArrangement = Arrangement.SpaceBetween,
  ) {
    val likings = listOf("\uD83D\uDE04", "\uD83D\uDE42", "\uD83D\uDE11", "\uD83E\uDD2E")
    val likingState = rememberRadioState()

    val why = listOf(
      "I'm curious about Kotlin.",
      "I want to know more.",
      "I need to learn more.",
    )
    val whyState = rememberRadioState()

    val (comment, setComment) = remember { mutableStateOf("") }

    Column(
      modifier = Modifier.fillMaxWidth(),
    ) {
      Text(
        text = "How did you like this app?",
        style = MaterialTheme.typography.h5
      )

      DefaultVerticalSpacer()

      RadioSelection(
        choices = likings,
        modifier = Modifier
          .fillMaxWidth()
          .align(Alignment.CenterHorizontally),
        textStyle = MaterialTheme.typography.h4,
        radioState = likingState,
      )
    }

    BigVerticalSpacer()

    Column(
      modifier = Modifier.fillMaxWidth(),
    ) {
      Text(
        text = "Why do you want more lessons?",
        style = MaterialTheme.typography.h5
      )

      DefaultVerticalSpacer()

      RadioSelection(
        choices = why,
        modifier = Modifier
          .fillMaxWidth()
          .align(Alignment.CenterHorizontally),
        radioState = whyState,
      )
    }

    BigVerticalSpacer()

    Column(
      modifier = Modifier.fillMaxWidth(),
    ) {
      Text(
        text = "Any word for the author?",
        style = MaterialTheme.typography.h5
      )

      DefaultVerticalSpacer()
      TextField(
        value = comment,
        onValueChange = { setComment(it) },
        modifier = Modifier
          .height(128.dp)
          .padding(4.dp)
          .fillMaxWidth(),
      )
    }

    ControlBar {
      Button(
        onClick = {
          onValidate(
            likingState.value?.let { likings[it] },
            whyState.value?.let { why[it] },
            comment,
          )
        }
      ) {
        Icon(Icons.Default.Check, "Validate the form")
      }
    }
  }
}

@Preview
@Composable
fun MoreLessonsUiPreview() {
  MaterialTheme {
    Surface(
      color = Color(0xFF4CAF50)
    ) {
      Column(
        modifier = Modifier.padding(20.dp)
      ) {
        Surface(
          color = MaterialTheme.colors.background
        ) {
          MoreLessonsUi { _, _, _ -> }
        }
      }
    }
  }
}
