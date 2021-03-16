package net.lab0.kotlinexplorer.framework.presentation.composable.chapter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddTask
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.lab0.kotlinexplorer.business.course.data.kotlin.KOTLIN
import net.lab0.kotlinexplorer.business.domain.Chapter
import net.lab0.kotlinexplorer.business.domain.LessonProgress
import net.lab0.kotlinexplorer.business.interactor.abstraction.GetAllChapters
import net.lab0.kotlinexplorer.business.interactor.abstraction.GetLessonsInProgress
import net.lab0.kotlinexplorer.framework.presentation.composable.BigVerticalSpacer
import net.lab0.kotlinexplorer.framework.presentation.composable.SmallHorizontalSpacer
import net.lab0.kotlinexplorer.framework.presentation.composable.frame.TopLevelScaffold
import net.lab0.kotlinexplorer.framework.presentation.composable.frame.TopLevelScreen
import net.lab0.kotlinexplorer.framework.presentation.composable.lesson.LessonScreen
import net.lab0.kotlinexplorer.framework.presentation.fragment.chapterlist.ChapterListViewModel
import net.lab0.kotlinexplorer.mvi.Resource

@FlowPreview
@ExperimentalCoroutinesApi
@Composable
fun ChapterUi(
  topLevelNavController: NavHostController,
  lessonsNavController: NavHostController,
  viewModelFactory: ViewModelProvider.Factory,
) {
  val viewModel: ChapterListViewModel = viewModel(factory = viewModelFactory)
  viewModel.loadLessonsInProgress()
  val chapterListViewState by viewModel.uiDataState.collectAsState()
  val scaffoldState = rememberScaffoldState()

  TopLevelScaffold(
    navController = topLevelNavController,
    title = "Chapters",
    scaffoldState = scaffoldState,
    quickScreens = listOf(TopLevelScreen.Chapters, TopLevelScreen.Tools),
  ) {
    val scrollState = rememberScrollState()

    Column(
      modifier = Modifier.verticalScroll(scrollState),
    ) {
      ChapterList(
        modifier = Modifier.padding(bottom = 64.dp),
        chapters = chapterListViewState.chapters.map { chapter ->
          val completion = chapterListViewState.getChapterCompletion(chapter)
          val lessonsInProgress = chapterListViewState.lessonsInProgress.map { it.lessonId }
          val nextLesson = chapter.lessons.firstOrNull { it.id !in lessonsInProgress }

          ChapterCardData(
            chapter.id,
            chapter.title,
            completion,
            chapter.lessons.map { lesson ->
              LessonListItemData(
                id = lesson.id,
                title = lesson.title,
                completed = lesson.id in lessonsInProgress,
                highlighted = lesson == nextLesson,
                progress = chapterListViewState.getLessonCompletion(lesson.id)
              )
            }
          )
        },
        onPlay = { _, lessonId ->
          // navigate to lesson
          lessonsNavController.navigate(
            LessonScreen.LessonPage.route(lessonId, 0)
          )
        }
      )

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
      ) {
        Button(
          onClick = {
            // TODO navigate to Extra content request
          },
          enabled = chapterListViewState.canRequestMoreChapters,
        ) {
          if (chapterListViewState.canRequestMoreChapters) {
            Icon(
              imageVector = Icons.Default.AddTask,
              "Add Task",
            )
            SmallHorizontalSpacer()
          }
          Text(
            text =
            if (chapterListViewState.canRequestMoreChapters) "More please!"
            else "Requested"
          )
        }
      }

      BigVerticalSpacer()
      BigVerticalSpacer()
    }
  }
}

@Preview
@Composable
private fun ChapterUiPreview() {
  MaterialTheme {
    Surface(
      color = Color(0xFF4CAF50)
    ) {
      Column(
        modifier = Modifier.padding(20.dp)
      ) {
        Surface(
          color = MaterialTheme.colors.background
        ) {
          ChapterUi(
            rememberNavController(),
            rememberNavController(),
            object : ViewModelProvider.Factory {
              override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ChapterListViewModel(
                  object : GetLessonsInProgress {
                    override fun invoke(): Flow<Resource<List<LessonProgress>>> = flow {
                      emit(Resource.LoadedResource(listOf()))
                    }
                  },
                  object : GetAllChapters {
                    override fun invoke(): Flow<Resource<List<Chapter>>> = flow {
                      emit(Resource.LoadedResource(KOTLIN))
                    }
                  }
                ) as T
              }
            }
          )
        }
      }
    }
  }
}
