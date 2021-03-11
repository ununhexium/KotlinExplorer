package net.lab0.kotlinexplorer.framework.presentation.composable.code.input

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CloudQueue
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ControlBar(
  startItems: (@Composable RowScope.() -> Unit)? = null,
  endItems: @Composable RowScope.() -> Unit,
) {
  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    if (startItems != null) {
      startItems()
    } else {
      Spacer(modifier = Modifier.padding(0.dp))
    }

    endItems()
  }
}

@Preview
@Composable
fun ControlBarPreview() {
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
          ControlBar(
            startItems = {
              Button(onClick = {}) {
                Icon(
                  Icons.Default.CloudQueue,
                  contentDescription = "Cloud Queue",
                )
              }
            },
            endItems = {
              Button(onClick = {}) {
                Icon(
                  Icons.Default.Check,
                  contentDescription = "Check",
                )
              }
            }
          )
        }
      }
    }
  }
}

@Preview
@Composable
fun ControlBarPreview_endOnly() {
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
          ControlBar(
            endItems = {
              Button(onClick = {}) {
                Icon(
                  Icons.Default.Check,
                  contentDescription = "Check",
                )
              }
            }
          )
        }
      }
    }
  }
}
