package net.lab0.kotlinexplorer.framework.presentation.composable.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.lab0.kotlinexplorer.R

@Composable
fun LoginUi(
  username: String? = null,
  onStartApp: () -> Unit,
  loginAnonymously: () -> Unit,
) {
  Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.SpaceEvenly,
  ) {
    val kotlinExplorerLogo = painterResource(
      id = R.drawable.kotlinexplorer_logo,
    )

    val scale = 4f
    Image(
      painter = kotlinExplorerLogo,
      contentDescription = "Kotlin Explorer logo",
      modifier = Modifier
        .align(Alignment.CenterHorizontally)
        .padding(bottom = 32.dp)
        .scale(scale),
    )

    Column(
      modifier = Modifier
        .fillMaxWidth()
        .align(Alignment.CenterHorizontally),
    ) {
      val modifier = Modifier
        .align(Alignment.CenterHorizontally)
        .padding(vertical = 8.dp)

      if (username == null) {
        Button(
          modifier = modifier,
          onClick = {
            loginAnonymously()
            onStartApp()
          },
        ) {
          Text("Use anonymously")
        }
      } else {
        Button(
          modifier = modifier,
          onClick = onStartApp,
        ) {
          Text("Continue as $username")
        }
      }
    }
  }
}

@Preview
@Composable
fun LoginUiPreview_authentified() {
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
          LoginUi("Foo", {}, {})
        }
      }
    }
  }
}

@Preview
@Composable
fun LoginUiPreview_notAuth() {
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
          LoginUi(null, {}, {})
        }
      }
    }
  }
}
