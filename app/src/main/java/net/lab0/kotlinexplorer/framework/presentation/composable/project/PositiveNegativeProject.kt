package net.lab0.kotlinexplorer.framework.presentation.composable.project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.lab0.kotlinexplorer.business.course.data.kotlin.dollar
import net.lab0.kotlinexplorer.business.domain.extractHighlightsAndAnnotate
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanksImpl
import net.lab0.kotlinexplorer.framework.presentation.composable.BigVerticalSpacer
import net.lab0.kotlinexplorer.framework.presentation.composable.MediumVerticalSpacer
import net.lab0.kotlinexplorer.framework.presentation.composable.code.KotlinCode
import net.lab0.kotlinexplorer.framework.presentation.composable.code.ijStyle
import net.lab0.kotlinexplorer.framework.presentation.composable.frame.text.Body1Text
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder as p

@Composable
fun PositiveNegativeProject(modifier: Modifier = Modifier) {
  val code = """
fun main() {
  val n = ${p(0)}
  print(
    // ${p(1)}
    if (n > 0) "It's >0: " + n
    // ${p(2)}
    else if (n < 0) "It's <0: ${dollar}n"
    else "It's 0"
  )
}
"""

  val withVar = remember { KotlinCodeWithBlanksImpl(code) }

  var n by remember { mutableStateOf(0) }
  val p1 = n > 0
  val p2 = n < 0

  var slider by remember { mutableStateOf(0f) }

  val replaced = withVar.fill(
    mapOf(
      0 to n.toString(),
      1 to p1.toString(),
      2 to p2.toString(),
    )
  )

  Column(
    modifier = modifier.fillMaxWidth(),
  ) {
    KotlinCode(code = extractHighlightsAndAnnotate(replaced, ijStyle))

    MediumVerticalSpacer()

    Row(
      modifier = Modifier.fillMaxWidth()
    ) {
      Body1Text("Output: ")

      KotlinCode(
        buildAnnotatedString {
          append(
            when {
              n > 0 -> "It's >0: $n"
              n < 0 -> "It's <0: $n"
              else -> "It's 0"
            }
          )
        }
      )
    }

    BigVerticalSpacer()

    val lowerBound = -5
    val upperBound = 5

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
    ) {
      Body1Text(string = lowerBound.toString())
      Body1Text(string = "0")
      Body1Text(string = upperBound.toString())
    }

    Slider(
      value = slider,
      onValueChange = {
        slider = it
        n = it.toInt()
      },
      valueRange = (lowerBound.toFloat() .. upperBound.toFloat()),
      steps = 9,
    )
  }
}

@Preview
@Composable
private fun PlusMinusProjectPreview() {
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
          PositiveNegativeProject()
        }
      }
    }
  }
}
