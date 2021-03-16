package net.lab0.kotlinexplorer.framework.presentation.composable.math

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.lab0.kotlinexplorer.framework.presentation.composable.DefaultVerticalSpacer
import net.lab0.kotlinexplorer.framework.presentation.composable.MediumVerticalSpacer
import net.lab0.kotlinexplorer.framework.presentation.composable.frame.Checkable
import java.util.*
import kotlin.random.Random


/**
 * Workaround when working with NaN and compose refuses
 * to update a value that "didn't change",
 * when working with different NaN values.
 */
data class DatedFloat(
  val float: Float,
  val editor: FloatEditor = FloatEditor(float),
  val date: Date = Date()
)

@OptIn(ExperimentalUnsignedTypes::class)
@Composable
fun FloatingPointVisualizer(initialValue:Float) {

  val (datedFloat, setDatedFloat) = remember { mutableStateOf(DatedFloat(initialValue)) }

  Column(
    modifier = Modifier.fillMaxWidth(),
    verticalArrangement = Arrangement.SpaceBetween,
  ) {

    ToStringNotation(datedFloat)

    ScientificNotation(datedFloat)

    RawBinaryNotation(datedFloat)

    MediumVerticalSpacer()

    PresetValues(datedFloat, setDatedFloat)

    MediumVerticalSpacer()

    Sliders(datedFloat, setDatedFloat)
  }
}

@Composable
private fun ToStringNotation(datedFloat: DatedFloat) {
  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.Center,
  ) {
    Text(
      text = datedFloat.float.toString(),
      style = MaterialTheme.typography.h4,
      fontFamily = FontFamily.Monospace
    )
  }
}

@Composable
private fun ScientificNotation(
  datedFloat: DatedFloat
) {
  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.Center,
  ) {
    when {
      datedFloat.float.isNaN() -> Text(
        text = "Not a Number",
        fontFamily = FontFamily.Monospace,
        color = MaterialTheme.colors.secondaryVariant,
        style = MaterialTheme.typography.h4
      )

      datedFloat.float == Float.POSITIVE_INFINITY ||
          datedFloat.float == Float.NEGATIVE_INFINITY ->
        Text(
          text = datedFloat.float.toString(),
          fontFamily = FontFamily.Monospace,
          color = MaterialTheme.colors.secondaryVariant,
          style = MaterialTheme.typography.h4
        )

      else -> {
        DecimalScientificNotation(datedFloat)
      }
    }
  }
}

@Composable
private fun Sliders(
  datedFloat: DatedFloat,
  setDatedFloat: (DatedFloat) -> Unit,
) {
  Text("Parts", style = MaterialTheme.typography.h5)

  DefaultVerticalSpacer()

  // sign
  Text("Sign", style = MaterialTheme.typography.h6)
  Checkable(
    checked = datedFloat.editor.signBit == "1",
    onCheckedChange = { checked: Boolean ->
      setDatedFloat(
        DatedFloat(
          if (checked) {
            datedFloat.editor.setNegative().value
          } else {
            datedFloat.editor.setPositive().value
          }
        )
      )
    },
    color = MaterialTheme.colors.secondaryVariant,
  ) {
    Text(
      "Negative",
      style = MaterialTheme.typography.body1,
      color = MaterialTheme.colors.secondaryVariant
    )
  }

  DefaultVerticalSpacer()

  // exponent
  Text("Exponent", style = MaterialTheme.typography.h6)
  Slider(
    value = datedFloat.editor.exponentDecBase2Hack?.toFloat() ?: 255f,
    onValueChange = { exponent ->
      setDatedFloat(
        DatedFloat(
          datedFloat.editor.setExponent(exponent.toInt()).value
        )
      )
    },
    valueRange = (
        0f .. 255f
        ),
    colors = SliderDefaults.colors(
      thumbColor = MaterialTheme.colors.secondary,
      activeTrackColor = MaterialTheme.colors.secondary,
    )
  )

  // mantissa
  Text("Mantissa", style = MaterialTheme.typography.h6)
  Slider(
    value = datedFloat.editor.mantissaDecBase2Hack.toFloat(),
    onValueChange = { mantissa ->
      setDatedFloat(
        DatedFloat(
          datedFloat.editor.setMantissa(mantissa.toInt()).value
        )
      )
    },
    valueRange = 0f .. FloatEditor.mantissaMask.toFloat(),
  )
}

@Composable
private fun PresetValues(datedFloat: DatedFloat, setDatedFloat: (DatedFloat) -> Unit) {
  Column(
    modifier = Modifier.fillMaxWidth(),
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
      Button(onClick = { setDatedFloat(DatedFloat(Float.MAX_VALUE)) }) {
        Text(text = "Max", style = MaterialTheme.typography.body2)
      }

      Button(onClick = { setDatedFloat(DatedFloat(Float.MIN_VALUE)) }) {
        Text(text = "Min", style = MaterialTheme.typography.body2)
      }

      Button(onClick = {
        setDatedFloat(
          DatedFloat(
            datedFloat
              .editor
              .setExponent(Random.nextInt(0, 256))
              .setMantissa(Random.nextInt(0, 0x7fffff))
              .let {
                if (Random.nextBoolean()) it.setPositive()
                else it.setNegative()
              }
              .value
          )
        )
      }) {
        Text(text = "Random", style = MaterialTheme.typography.body2)
      }
    }

    DefaultVerticalSpacer()

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
      Button(onClick = { setDatedFloat(DatedFloat(Float.POSITIVE_INFINITY)) }) {
        Text(text = "∞", style = MaterialTheme.typography.body2)
      }

      Button(onClick = { setDatedFloat(DatedFloat(Float.NEGATIVE_INFINITY)) }) {
        Text(text = "-∞", style = MaterialTheme.typography.body2)
      }

      Button(onClick = { setDatedFloat(DatedFloat(Float.NaN)) }) {
        Text(text = "NaN", style = MaterialTheme.typography.body2)
      }
    }

    DefaultVerticalSpacer()

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
      Button(onClick = { setDatedFloat(DatedFloat(0f)) }) {
        Text(text = "0", style = MaterialTheme.typography.body2)
      }

      Button(onClick = { setDatedFloat(DatedFloat(-0f)) }) {
        Text(text = "-0", style = MaterialTheme.typography.body2)
      }

      Button(onClick = { setDatedFloat(DatedFloat(1f)) }) {
        Text(text = "1", style = MaterialTheme.typography.body2)
      }

      Button(onClick = { setDatedFloat(DatedFloat(.5f)) }) {
        Text(text = ".5", style = MaterialTheme.typography.body2)
      }
    }

    DefaultVerticalSpacer()

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
      Button(onClick = { setDatedFloat(DatedFloat(0.1f)) }) {
        Text(text = "0.1", style = MaterialTheme.typography.body2)
      }

      Button(onClick = { setDatedFloat(DatedFloat(Math.PI.toFloat())) }) {
        Text(text = "π", style = MaterialTheme.typography.body2)
      }

      Button(onClick = { setDatedFloat(DatedFloat(Math.E.toFloat())) }) {
        Text(text = "e", style = MaterialTheme.typography.body2)
      }
    }
  }
}

@Composable
private fun RawBinaryNotation(float: DatedFloat) {
  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween,
  ) {
    // sign
    Column {
      Row {
        Text(
          text = "sign",
          style = MaterialTheme.typography.body2,
        )
      }
      Row {
        Text(
          text = float.editor.signBit,
          fontFamily = FontFamily.Monospace,
          color = MaterialTheme.colors.secondaryVariant,
        )
      }
    }

    // exponent
    Column {
      Row {
        Text(
          text = "exponent",
          style = MaterialTheme.typography.body2,
        )
      }
      Row {
        Text(
          text = float.editor.exponentBits,
          fontFamily = FontFamily.Monospace,
          color = MaterialTheme.colors.secondary
        )
      }
    }

    // mantissa
    Column {
      Row {
        Text(
          text = "mantissa",
          style = MaterialTheme.typography.body2,
        )
      }
      Row {
        Text(
          text = float.editor.mantissaBits,
          fontFamily = FontFamily.Monospace,
          color = MaterialTheme.colors.primary,
        )
      }
    }
  }
}

@Composable
private fun DecimalScientificNotation(datedFloat: DatedFloat) {
  // sign
  Text(
    text = if (datedFloat.editor.signBit == "0") "+" else "-",
    fontFamily = FontFamily.Monospace,
    color = MaterialTheme.colors.secondaryVariant,
    style = MaterialTheme.typography.h4,
  )

  // mantissa

  Text(
    text = datedFloat.editor.mantissaDec ?: "Error",
    fontFamily = FontFamily.Monospace,
    color = MaterialTheme.colors.primary,
    style = MaterialTheme.typography.h4,
  )

  Text(
    "·10",
    fontFamily = FontFamily.Monospace,
    style = MaterialTheme.typography.h4,
  )

  // exponent
  Text(
    modifier = Modifier.padding(bottom = 4.dp),
    text = datedFloat.editor.exponentDec ?: "Error",
    fontFamily = FontFamily.Monospace,
    color = MaterialTheme.colors.secondary,
    style = MaterialTheme.typography.h5,
  )
}


@Preview
@Composable
fun FloatingPointVisualizerPreview() {
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
          FloatingPointVisualizer(116f)
        }
      }
    }
  }
}
