package net.lab0.kotlinexplorer.framework.presentation.composable.code.input

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Replay
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.lab0.kotlinexplorer.framework.presentation.composable.DefaultHorizontalSpacer

@Composable
fun NextPageControlBar(
  modifier: Modifier = Modifier,
  onNext: () -> Unit,
  onRetry: () -> Unit,
) {
  Row(
    modifier = modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.End
  ) {
    Row {
      onRetry?.let {
        Button(
          onClick = onRetry,
        ) {
          Icon(
            imageVector = Icons.Default.Replay,
            contentDescription = "Retry",
          )
        }
      }

      DefaultHorizontalSpacer()

      Button(
        onClick = onNext,
      ) {
        Icon(
          imageVector = Icons.Default.ArrowForward,
          contentDescription = "Next page",
        )
      }
    }
  }
}

@Preview
@Composable
fun NextPageControlBarPreview() {
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
          NextPageControlBar(onNext = {}, onRetry = {})
        }
      }
    }
  }
}
