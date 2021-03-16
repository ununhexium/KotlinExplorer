package net.lab0.kotlinexplorer.framework.presentation.composable.math

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun Int8Visualizer(byteState: MutableState<Byte>) {
  val (byte, setByte) = byteState

  Column(modifier = Modifier.fillMaxWidth()) {
    Text(
      byte.toInt().toString(),
      modifier = Modifier.align(Alignment.CenterHorizontally),
      style = MaterialTheme.typography.h4
    )

    Column(modifier = Modifier.align(Alignment.CenterHorizontally)) {
      Text(
        text = Integer
          .toBinaryString(byte.toInt())
          .takeLast(8)
          .padStart(8, '0'),
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.colors.primary,
        fontFamily = FontFamily.Monospace,
      )

      Text(
        text = "8" + " ".repeat(6) + "0",
        style = MaterialTheme.typography.body1,
        fontFamily = FontFamily.Monospace,
      )
    }

    Row(modifier = Modifier.fillMaxWidth()) {
      Slider(
        value = byte.toFloat(),
        onValueChange = { setByte(it.toInt().toByte()) },
        valueRange = Byte.MIN_VALUE.toInt().toFloat() .. Byte.MAX_VALUE.toInt().toFloat(),
      )
    }

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween
    ) {

      Button(onClick = { setByte((byte - 1).toByte()) }) {
        Text(
          text = "-1",
          style = MaterialTheme.typography.body1
        )
      }

      Button(onClick = { setByte(Random.Default.nextBytes(1)[0]) }) {
        Text(
          text = "Random",
          style = MaterialTheme.typography.body1
        )
      }

      Button(onClick = { setByte((byte + 1).toByte()) }) {
        Text(
          text = "+1",
          style = MaterialTheme.typography.body1
        )
      }

    }
  }
}

@Preview
@Composable
fun Int8VisualizerPreview() {
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
          Int8Visualizer(mutableStateOf(-116))
        }
      }
    }
  }
}
