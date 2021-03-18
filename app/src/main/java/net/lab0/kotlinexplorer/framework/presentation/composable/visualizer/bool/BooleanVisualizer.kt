package net.lab0.kotlinexplorer.framework.presentation.composable.visualizer.bool

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
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
import net.lab0.kotlinexplorer.framework.presentation.composable.BigVerticalSpacer
import net.lab0.kotlinexplorer.framework.presentation.composable.DefaultVerticalSpacer
import net.lab0.kotlinexplorer.framework.presentation.composable.visualizer.bool.BooleanOperator
import net.lab0.kotlinexplorer.framework.presentation.composable.visualizer.bool.BooleanOperator.EQ
import net.lab0.kotlinexplorer.framework.presentation.composable.visualizer.bool.BooleanOperator.NEQ
import net.lab0.kotlinexplorer.framework.presentation.composable.visualizer.bool.BooleanOperator.NOT
import net.lab0.kotlinexplorer.utils.Do


@Composable
fun BooleanVisualizer(
  initialValue1: Boolean,
  initialValue2: Boolean,
  initialOperator: BooleanOperator
) {
  val (bool1, setBool1) = remember { mutableStateOf(initialValue1) }
  val (bool2, setBool2) = remember { mutableStateOf(initialValue2) }
  val (operator, setOperator) = remember { mutableStateOf(initialOperator) }

  val operatorValues = BooleanOperator.values()

  Column(
    modifier = Modifier.fillMaxWidth()
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.Center,
    ) {
      Text(text = "Inputs", style = MaterialTheme.typography.body1)
    }

    DefaultVerticalSpacer()

    val operandColor = ButtonDefaults.buttonColors(
      backgroundColor = MaterialTheme.colors.secondary
    )

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
      if (operator != NOT) {
        Button(
          onClick = { setBool1(!bool1) },
          colors = operandColor,
        ) {
          Mono(bool1.toString())
        }

      }
      Button(
        onClick = {
          setOperator(
            operatorValues[(operator.ordinal + 1) % operatorValues.size]
          )
        },
        colors = ButtonDefaults.buttonColors(MaterialTheme.colors.primary),
      ) {
        Mono(
          Do exhaustiveNonNull when (operator) {
            EQ -> "=="
            NEQ -> "!="
            NOT -> "!"
          }
        )
      }

      Button(
        onClick = { setBool2(!bool2) },
        colors = operandColor,
      ) {
        Mono(bool2.toString())
      }
    }

    DefaultVerticalSpacer()

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.Center,
    ) {
      Text(
        text = "Tap the inputs to make their values change",
        style = MaterialTheme.typography.body2,
        color = Color.Gray
      )
    }

    BigVerticalSpacer()

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.Center,
    ) {
      Text(text = "Output", style = MaterialTheme.typography.body1)
    }

    DefaultVerticalSpacer()

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.Center,
    ) {
      Mono(
        Do exhaustiveNonNull when (operator) {
          EQ -> bool1 == bool2
          NEQ -> bool1 != bool2
          NOT -> !bool2
        }.toString()
      )
    }
  }
}

@Composable
fun RowScope.Mono(text: String) {
  Text(
    text,
    modifier = Modifier.align(Alignment.CenterVertically),
    style = MaterialTheme.typography.h4,
  )
}

@Preview
@Composable
private fun BooleanVisualizerPreview() {
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
          BooleanVisualizer(true, false, NEQ)
        }
      }
    }
  }
}