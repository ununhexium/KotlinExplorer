package net.lab0.kotlinexplorer.framework.presentation.composable.code.input

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Replay
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CodeInputControlBar(
  canUndoOrReset: Boolean,
  canValidate: Boolean,
  onUndo: () -> Unit,
  onReset: () -> Unit,
  onValidate: () -> Unit,
) {
  ControlBar(
    startItems = {
      Row {
        val secondaryButtonColors = ButtonDefaults.buttonColors(
          backgroundColor = MaterialTheme.colors.secondaryVariant,
          contentColor = MaterialTheme.colors.surface
        )
        Button(
          onClick = onUndo,
          enabled = canUndoOrReset,
          colors = secondaryButtonColors
        ) {
          Icon(
            imageVector = Icons.Default.Backspace,
            contentDescription = "Backspace",
          )
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Button(
          onClick = onReset,
          enabled = canUndoOrReset,
          colors = secondaryButtonColors
        ) {
          Icon(
            imageVector = Icons.Default.Replay,
            contentDescription = "Replay",
          )
        }
      }
    },
    endItems = {
      Row {
        Button(
          onClick = onValidate,
          enabled = canValidate,
        ) {
          Icon(
            imageVector = Icons.Default.Done,
            contentDescription = "Done",
          )
        }
      }
    }
  )
}

@Preview
@Composable
fun CodeInputControlBarPreview() {
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
          CodeInputControlBar(
            true,
            true,
            {},
            {},
            {}
          )
        }
      }
    }
  }
}
