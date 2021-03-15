package net.lab0.kotlinexplorer.framework.presentation.composable.problemreport

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.lab0.kotlinexplorer.business.domain.problemreport.ProblemReport
import net.lab0.kotlinexplorer.framework.presentation.composable.BigVerticalSpacer
import net.lab0.kotlinexplorer.framework.presentation.composable.DefaultVerticalSpacer

@Composable
fun ProblemReportUi(
  problemLocation: String,
  submitReport: (ProblemReport) -> Unit,
  onCloseProblemReport: () -> Unit,
  sizeLimit: Int = 512,
) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(4.dp),
    verticalArrangement = Arrangement.Top
  ) {
    val (report, setReport) = remember { mutableStateOf("") }

    Text(text = "Problem Report", style = MaterialTheme.typography.h4)
    BigVerticalSpacer()

    Text(text = "Which issue did you encounter?", style = MaterialTheme.typography.h6)
    DefaultVerticalSpacer()

    TextField(
      modifier = Modifier
        .height(256.dp)
        .fillMaxWidth(),
      value = report,
      onValueChange = { setReport(it) },
      placeholder = {
        Text(
          text = "Tell me more ... :)",
          style = MaterialTheme.typography.body1
        )
      },
      trailingIcon = {
        Row(
          modifier = Modifier
            .fillMaxHeight()
            .padding(bottom = 8.dp),
        ) {
          Text(
            modifier = Modifier.align(Alignment.Bottom),
            text = report.length.toString() + "/" + sizeLimit,
            style = MaterialTheme.typography.body2
          )
        }
      }
    )

    DefaultVerticalSpacer()

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Button(
        onClick = onCloseProblemReport,
        colors = ButtonDefaults.outlinedButtonColors(
          MaterialTheme.colors.surface,
          MaterialTheme.colors.primary,
        ),
      ) {
        Text("Cancel")
      }

      Button(
        onClick = {
          submitReport(ProblemReport(problemLocation, report))
          onCloseProblemReport()
        },
      ) {
        Text("Submit")
      }
    }
  }
}

@Preview
@Composable
fun ProblemReportUiPreview() {
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
          ProblemReportUi("here", {}, {})
        }
      }
    }
  }
}
