package net.lab0.kotlinexplorer.framework.presentation.composable.lesson

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.lab0.kotlinexplorer.business.domain.problemreport.ProblemReport
import net.lab0.kotlinexplorer.framework.presentation.composable.problemreport.ProblemReportUi

@Composable
fun LessonPage(
    lessonId: String,
    progress: Float,
    title: String,
    onBack: () -> Unit,
    onProblemReport: (ProblemReport) -> Unit,
    page: @Composable ColumnScope.() -> Unit,
) {
  val (reportPopup, setReportPopup) = remember { mutableStateOf(false) }

  Column(
      modifier = Modifier.fillMaxHeight(),
      verticalArrangement = Arrangement.SpaceBetween
  ) {
    LessonPageHeader(
        title = title,
        backAction = onBack,
        reportMistakeAction = { setReportPopup(!reportPopup) }
    )
    if (reportPopup) {
      ProblemReportUi(
          problemLocation = "$lessonId, title = $title",
          submitReport = onProblemReport,
          onCloseProblemReport = { setReportPopup(false) }
      )
    } else {
      page()
    }
    LinearProgressIndicator(
        progress = progress,
        modifier = Modifier
            .fillMaxWidth()
            .height(4.dp)
    )
  }
}

@Preview
@Composable
fun LessonPagePreview() {
  MaterialTheme {
    Surface {
      Column {
        LessonPage(
            "lessonId",
            0.116f,
            "Somewhere over the rainbow",
            {},
            {}
        ) {
          Surface(
              modifier = Modifier.fillMaxSize(),
              color = Color.Gray
          ) {

          }
        }
      }
    }
  }
}

@Preview
@Composable
fun LessonPagePreview_singleButton() {
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
          LessonPage(
              lessonId = "lessonId",
              progress = 1.0f,
              title = "Foo",
              onBack = {},
              onProblemReport = {},
          ) {
            Button(
                onClick = {},
                modifier = Modifier.align(Alignment.CenterHorizontally),
            ) {
              Text("Finished")
            }
          }
        }
      }
    }
  }
}
