package net.lab0.kotlinexplorer.framework.presentation.composable.visualizer.floatingpoint

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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.lab0.kotlinexplorer.framework.presentation.composable.DefaultVerticalSpacer
import net.lab0.kotlinexplorer.framework.presentation.composable.MediumVerticalSpacer
import net.lab0.kotlinexplorer.framework.presentation.composable.frame.Checkable
import kotlin.random.Random


@OptIn(ExperimentalUnsignedTypes::class)
@Composable
fun FloatingPointVisualizer(initialValue: MutableState<DatedFloat>) {

  val (datedFloat, setDatedFloat) = initialValue

  // custom update methods to avoid precision loss / drifting when converting between types

  var exponentSlider by remember {
    mutableStateOf(datedFloat.editor.exponentAsInt.toFloat())
  }

  val exponentSetter = { n: Float ->
    exponentSlider = n

    setDatedFloat(
      DatedFloat(
        datedFloat.editor.setExponent(exponentSlider.toInt()).value
      )
    )
  }

  var mantissaSlider by remember {
    mutableStateOf(datedFloat.editor.mantissaAsInt.toFloat())
  }

  val mantissaSetter = { n: Float ->
    mantissaSlider = n

    setDatedFloat(
      DatedFloat(
        datedFloat.editor.setMantissa(mantissaSlider.toInt()).value
      )
    )
  }

  val fullFloatSetter = { n: Float ->
    val d = DatedFloat(n)

    exponentSetter(d.editor.exponentAsInt.toFloat())
    mantissaSetter(d.editor.mantissaAsInt.toFloat())

    setDatedFloat(d)
  }

  Column(
    modifier = Modifier.fillMaxWidth(),
    verticalArrangement = Arrangement.SpaceBetween,
  ) {

    ToStringNotation(datedFloat)

    ScientificNotationBase10(datedFloat)

    RawBinaryNotation(datedFloat)

    MediumVerticalSpacer()

    PresetValues(datedFloat, fullFloatSetter)

    MediumVerticalSpacer()

    Sliders(
      datedFloat,
      setDatedFloat,
      exponentSlider,
      exponentSetter,
      mantissaSlider,
      mantissaSetter
    )
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
private fun ScientificNotationBase10(
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
        DecimalScientificNotationBase10(datedFloat)
      }
    }
  }
}

@Composable
private fun Sliders(
  datedFloat: DatedFloat,
  setDatedFloat: (DatedFloat) -> Unit,
  exponentSlider: Float,
  exponentSetter: (Float) -> Unit,
  mantissaSlider: Float,
  mantissaSetter: (Float) -> Unit,
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
    value = exponentSlider,
    onValueChange = exponentSetter,
    valueRange = (0f .. FloatEditor.exponentMax.toFloat()),
    colors = SliderDefaults.colors(
      thumbColor = MaterialTheme.colors.secondary,
      activeTrackColor = MaterialTheme.colors.secondary,
    )
  )

  // mantissa
  Text("Mantissa", style = MaterialTheme.typography.h6)
  Slider(
    value = mantissaSlider,
    onValueChange = mantissaSetter,
    valueRange = 0f .. FloatEditor.mantissaMax.toFloat(),
  )
}

@Composable
private fun PresetValues(datedFloat: DatedFloat, fullFloatSetter: (Float) -> Unit) {
  Column(
    modifier = Modifier.fillMaxWidth(),
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
      Button(
        onClick = { fullFloatSetter(Float.MAX_VALUE) }
      ) {
        Text(text = "Max", style = MaterialTheme.typography.body2)
      }

      Button(
        onClick = { fullFloatSetter(Float.MIN_VALUE) }
      ) {
        Text(text = "Min", style = MaterialTheme.typography.body2)
      }

      Button(onClick = {
        fullFloatSetter(
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
      }) {
        Text(text = "Random", style = MaterialTheme.typography.body2)
      }
    }

    DefaultVerticalSpacer()

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
      Button(
        onClick = { fullFloatSetter(Float.POSITIVE_INFINITY) }
      ) {
        Text(text = "∞", style = MaterialTheme.typography.body2)
      }

      Button(
        onClick = { fullFloatSetter(Float.NEGATIVE_INFINITY) }
      ) {
        Text(text = "-∞", style = MaterialTheme.typography.body2)
      }

      Button(
        onClick = { fullFloatSetter(Float.NaN) }
      ) {
        Text(text = "NaN", style = MaterialTheme.typography.body2)
      }
    }

    DefaultVerticalSpacer()

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
      Button(
        onClick = { fullFloatSetter(0f) }
      ) {
        Text(text = "0", style = MaterialTheme.typography.body2)
      }

      Button(
        onClick = { fullFloatSetter(-0f) }
      ) {
        Text(text = "-0", style = MaterialTheme.typography.body2)
      }

      Button(
        onClick = { fullFloatSetter(1f) }
      ) {
        Text(text = "1", style = MaterialTheme.typography.body2)
      }

      Button(
        onClick = { fullFloatSetter(.5f) }
      ) {
        Text(text = ".5", style = MaterialTheme.typography.body2)
      }
    }

    DefaultVerticalSpacer()

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
      Button(
        onClick = { fullFloatSetter(0.1f) }
      ) {
        Text(text = "0.1", style = MaterialTheme.typography.body2)
      }

      Button(
        onClick = { fullFloatSetter(Math.PI.toFloat()) }
      ) {
        Text(text = "π", style = MaterialTheme.typography.body2)
      }

      Button(
        onClick = { fullFloatSetter(Math.E.toFloat()) }
      ) {
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
private fun DecimalScientificNotationBase10(datedFloat: DatedFloat) {
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
private fun FloatingPointVisualizerPreview() {
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
          FloatingPointVisualizer(mutableStateOf(DatedFloat(116f)))
        }
      }
    }
  }
}
