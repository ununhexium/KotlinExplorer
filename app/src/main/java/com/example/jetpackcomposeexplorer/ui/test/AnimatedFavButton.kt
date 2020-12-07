package com.example.jetpackcomposeexplorer.ui.test

import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.core.tween
import androidx.compose.animation.transition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview

@Composable
fun AnimatedFavButton() {
  val buttonState = remember { mutableStateOf(ButtonState.IDLE) } //3

  //1
  val transitionDefinition = transitionDefinition<ButtonState> {

    //2
    state(ButtonState.IDLE) {
      this[width] = 300.dp
    }
    //3
    state(ButtonState.PRESSED) {
      this[width] = 60.dp
    }

    //4
    transition(fromState = ButtonState.IDLE, toState = ButtonState.PRESSED) {
      width using tween(durationMillis = 1500)
    }

    transition(ButtonState.PRESSED to ButtonState.IDLE) {
      width using tween(durationMillis = 1500)
    }
  }

// 1
  val toState = if (buttonState.value == ButtonState.IDLE) {
    ButtonState.PRESSED
  } else {
    ButtonState.IDLE
  }

  val state = transition(
      definition = transitionDefinition,
      initState = buttonState.value,
      toState = toState // 2
  )


  FavButton(buttonState = buttonState, state = state)
}

@Preview
@Composable
fun PreviewAnimatedFavButton() {
  MaterialTheme {
    Surface {
      AnimatedFavButton()
    }
  }
}
