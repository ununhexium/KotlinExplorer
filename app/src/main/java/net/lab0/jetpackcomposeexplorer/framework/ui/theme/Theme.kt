package net.lab0.jetpackcomposeexplorer.framework.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import net.lab0.jetpackcomposeexplorer.framework.ui.theme.Theme.DARK_DEFAULT
import net.lab0.jetpackcomposeexplorer.framework.ui.theme.Theme.LIGHT_DEFAULT

private val DarkColorPalette = darkColors(
    primary = Red500,
    primaryVariant = Red500,
    secondary = Green500,
    background = Gray200,
    surface = Gray200,
    onBackground = Teal200
)

private val LightColorPalette = lightColors(
    primary = purple500,
    primaryVariant = purple700,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

enum class Theme {
  DARK_DEFAULT,
  LIGHT_DEFAULT
}

@Composable
fun JetpackComposeExplorerTheme(
    theme: Theme = if (isSystemInDarkTheme()) DARK_DEFAULT else LIGHT_DEFAULT,
    content: @Composable () -> Unit
) {
  val colors =
      when (theme) {
        DARK_DEFAULT -> darkColors()
        LIGHT_DEFAULT -> lightColors()
      }

  MaterialTheme(
      colors = colors,
      typography = typography,
      shapes = shapes,
      content = content
  )
}