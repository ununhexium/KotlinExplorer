package com.example.jetpackcomposeexplorer.ui.test

import androidx.compose.animation.core.TransitionState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonConstants
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.example.jetpackcomposeexplorer.ui.purple500

@Composable
fun FavButton(buttonState: MutableState<ButtonState>, state: TransitionState) { //line changed
  Button(
      colors = ButtonConstants.defaultOutlinedButtonColors(),
      border = BorderStroke(1.dp, purple500),
      shape = RoundedCornerShape(6.dp),
      modifier = Modifier.size(state[width], 60.dp), //line changed
      onClick = {
        buttonState.value = if (buttonState.value == ButtonState.IDLE) {
          ButtonState.PRESSED
        } else {
          ButtonState.IDLE
        }
      }
  ) {
    ButtonContent()
  }
}

@Composable
fun ButtonContent() {
  Row(verticalAlignment = Alignment.CenterVertically) {
    Column(
        Modifier.width(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Icon(
          tint = purple500,
          asset = Icons.Default.FavoriteBorder,
          modifier = Modifier.size(24.dp)
      )
    }
    Spacer(modifier = Modifier.width(16.dp))
    Text(
        "ADD TO FAVORITES!",
        softWrap = false,
        color = purple500
    )
  }
}
