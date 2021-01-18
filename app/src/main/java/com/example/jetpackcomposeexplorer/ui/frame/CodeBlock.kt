package  com.example.jetpackcomposeexplorer.ui.frame

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposeexplorer.presentation.components.code.Monospace

@Deprecated("Prototype")
val placeholder = "/**ANSWER**/"

/**
 * A code block for showing Kotlin code
 */
@Composable
fun CodeBlock(sourceCode: String) {
  Row {
    Column {
      val lines = sourceCode.split("\n")

      lines.forEachIndexed { index, line ->
        Row {
          val answerIndex = line.indexOf(placeholder)

          if (answerIndex != -1) {
            val before = line.substring(0, answerIndex)
            val after = line.substring(answerIndex + placeholder.length)
            Monospace(before)
            Box(
                Modifier.border(
                    BorderStroke(2.dp, MaterialTheme.colors.primary),
                    RoundedCornerShape(20)
                )
            ) {
              // placeholder to have the right size
              Monospace(" ".repeat(placeholder.length))
            }
            Monospace(after)
          } else {
            Monospace(line)
          }
        }
      }
    }
  }
}

@Preview
@Composable
fun PreviewCodeBlock1() {
  MaterialTheme {
    Surface {
      CodeBlock(
          """
        |class MainActivity : AppCompatActivity() {
        |    override fun onCreate(savedInstanceState: Bundle?) {
        |        super.onCreate(savedInstanceState)
        |        setContent {
        |            $placeholder
        |        }
        |    }
        |}
      """.trimMargin()
      )
    }
  }
}


@Preview
@Composable
fun PreviewCodeBlock2() {
  MaterialTheme {
    Surface {
      CodeBlock(
          "Hello $placeholder"
      )
    }
  }
}


@Preview
@Composable
fun PreviewCodeBlock4() {
  MaterialTheme {
    Surface {
      CodeBlock(
          "Hello $placeholder and $placeholder!"
      )
    }
  }
}


@Preview
@Composable
fun PreviewCodeBlock3() {
  MaterialTheme {
    Surface {
      CodeBlock(
          """
            |Hello $placeholder 
            |and $placeholder!
          """.trimMargin()
      )
    }
  }
}
