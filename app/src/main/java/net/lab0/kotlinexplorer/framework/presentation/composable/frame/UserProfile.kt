package net.lab0.kotlinexplorer.framework.presentation.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.lab0.kotlinexplorer.BuildConfig
import net.lab0.kotlinexplorer.R
import net.lab0.kotlinexplorer.framework.presentation.composable.frame.ThinButton
import java.util.*

@Composable
fun UserProfile(
  email: String?,
  profilePicturePlaceholder: Painter,
  profilePicture: ImageBitmap? = null,
  logIn: () -> Unit,
  logOut: () -> Unit,
  uid: String,
) {
  Column(
    modifier = Modifier.fillMaxHeight(),
    verticalArrangement = Arrangement.SpaceBetween
  ) {
    Column(
      modifier = Modifier.fillMaxWidth(),
    ) {

      Box(modifier = Modifier.height(192.dp)) {
        Surface(
          modifier = Modifier
            .height(128.dp)
            .fillMaxWidth(),
          color = MaterialTheme.colors.background,
        ) {}

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.Center,
        ) {
          Surface(
            modifier = Modifier
              .padding(top = 64.dp)
              .size(128.dp),
            color = MaterialTheme.colors.surface,
          ) {
            val padding = Modifier.padding(8.dp)
            when {
              profilePicture != null ->
                Image(profilePicture, contentDescription = "Profile picture", modifier = padding)

              else ->
                Image(
                  profilePicturePlaceholder,
                  contentDescription = "Profile picture placeholder",
                  modifier = padding
                )
            }
          }
        }
      }


      Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center
      ) {
        if (email != null) {
          Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = email,
            style = MaterialTheme.typography.h6
          )

          DefaultVerticalSpacer()

          ThinButton(
            text = "Log out".toUpperCase(Locale.getDefault()),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = logOut,
          )
        } else {
          Button(
            onClick = logIn,
            modifier = Modifier.align(Alignment.CenterHorizontally),
          ) {
            Text(
              text = "Log in",
              style = MaterialTheme.typography.h6,
            )
          }
        }
      }

      if (BuildConfig.DEBUG) {
        Row {
          Text("UID: $uid")
        }
      }
    }

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceAround
    ) {
      Text(
        text = "Contact: kotlin-explorer@googlegroups.com",
        style = MaterialTheme.typography.body2
      )
    }
  }
}

@Preview
@Composable
fun UserProfileUiPreview() {
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
          UserProfile(
            email = "foo@example.com",
            profilePicturePlaceholder = painterResource(R.drawable.ic_kotlin_logo),
            profilePicture = null,
            logIn = {},
            logOut = {},
            "uid0",
          )
        }
      }
    }
  }
}
