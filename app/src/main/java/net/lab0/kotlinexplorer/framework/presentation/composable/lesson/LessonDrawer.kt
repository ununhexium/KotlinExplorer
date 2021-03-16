package net.lab0.kotlinexplorer.framework.presentation.composable.lesson

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.popUpTo
import androidx.navigation.compose.rememberNavController
import net.lab0.kotlinexplorer.business.domain.Lesson
import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.framework.presentation.composable.frame.drawer.DrawerMenuEntryH5
import net.lab0.kotlinexplorer.framework.presentation.composable.frame.Indent

@Composable
fun LessonDrawer(
  navController: NavController,
  chapter: String,
  lesson: Lesson,
  currentPage: LessonPage?,
  modifier: Modifier = Modifier,
  onPageNavigation: (pageName: LessonPage) -> Unit,
) {
  val lessonPages = lesson.pages
  val lessonName = lesson.title

  val scrollState = rememberScrollState()

  val topEntriesModifier = Modifier.padding(vertical = 16.dp)

  Column(
    modifier = modifier
    .padding(horizontal = 16.dp)
    .verticalScroll(scrollState)
  ) {
    DrawerMenuEntryH5(
      title = "Chapters",
      icon = Icons.Default.MenuBook,
      modifier = topEntriesModifier,
      onClick = {
        navController.navigate(LessonScreen.Chapters.routeDefinition) {
          popUpTo(LessonScreen.Chapters.routeDefinition) {
            inclusive = true
          }
        }
      }
    )

    DrawerMenuEntryH5(
      title = "Report an issue",
      icon = Icons.Default.Warning,
      modifier = topEntriesModifier,
      onClick = {
        currentPage?.let { lessonPage ->
          navController.navigate(
            LessonScreen.ProblemReport.route(
              lesson.id,
              lesson.pages.indexOfFirst { it == lessonPage })
          )
        }
      }
    )

    Surface(
      modifier = Modifier
        .fillMaxWidth(0.8f)
        .padding(vertical = 8.dp)
        .align(Alignment.CenterHorizontally),
      color = Color.Gray,
    ) {
      Spacer(
        modifier = Modifier
          .height(1.dp)
      )
    }

    Text(
      text = chapter,
      modifier = topEntriesModifier,
      style = MaterialTheme.typography.h5,
      color = MaterialTheme.colors.onBackground,
    )

    Indent {
      Text(
        text = lessonName.toUpperCase(),
        style = MaterialTheme.typography.h6.copy(),
        color = Color.Gray,
        fontWeight = FontWeight.Bold,
      )
    }

    lessonPages.takeWhile { it != currentPage }.forEach { page ->
      Indent(2) {
        Text(
          text = page.title,
          modifier = Modifier.clickable { onPageNavigation(page) },
          style = MaterialTheme.typography.h6,
        )
      }
    }

    lessonPages.find { it == currentPage }?.let { page ->
      Indent(2) {
        Text(
          text = page.title,
          modifier = Modifier.clickable { onPageNavigation(page) },
          style = MaterialTheme.typography.h6,
          color = MaterialTheme.colors.primary,
        )
      }
    }

    lessonPages.takeLastWhile { it != currentPage }.forEach { page ->
      Indent(2) {
        Text(
          text = page.title,
          modifier = Modifier.clickable { onPageNavigation(page) },
          style = MaterialTheme.typography.h6,
          color = Color.Gray
        )
      }
    }
  }
}

val sampleLesson = LessonImpl(
  "",
  "Lesson title",
  (0..20).map{
    LessonPage.InfoPage("Page$it", "")
  }
)

@Preview
@Composable
fun LessonDrawerPreview_chapter1() {
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
          LessonDrawer(
            navController = rememberNavController(),
            chapter = "Chapter 1",
            lesson = sampleLesson,
            currentPage = sampleLesson.pages[5],
          ) {}
        }
      }
    }
  }
}
