package com.example.jetpackcomposeexplorer.ui.test

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AnimatedFavButton(modifier: Modifier = Modifier) {


  //Transition Definition

  FavButton()
}

enum class ButtonState {
  IDLE, PRESSED
}

@Composable
fun FavButton(modifier: Modifier = Modifier) {

}

@Preview
@Composable
fun PreviewAnimation() {
  MaterialTheme {
    Surface {
      Column(
          Modifier.fillMaxSize(),
          verticalArrangement = Arrangement.Center,
          horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        AnimatedFavButton()
      }
    }
  }
}
