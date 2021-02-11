package com.example.jetpackcomposeexplorer.framework.presentation.lesson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.jetpackcomposeexplorer.R
import com.example.jetpackcomposeexplorer.business.course.data.kotlin.KOTLIN
import com.example.jetpackcomposeexplorer.business.domain.LessonPage
import com.example.jetpackcomposeexplorer.framework.presentation.components.InfoLessonPage
import com.example.jetpackcomposeexplorer.framework.presentation.components.LessonPage
import com.example.jetpackcomposeexplorer.framework.presentation.components.code.CodeQuizPage
import com.example.jetpackcomposeexplorer.framework.presentation.components.frame.LessonDrawer
import com.example.jetpackcomposeexplorer.framework.presentation.ui.codequestion.CodeQuestionPageViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.commonmark.parser.Parser

@ExperimentalCoroutinesApi
class LessonFragment : Fragment(
) {
  val args: LessonFragmentArgs by navArgs()
  val viewModel: LessonViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    viewModel.loadLesson(args.lessonId)
  }

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?,
  ): View {
    return ComposeView(requireContext()).apply {

      setContent {
        val collected = viewModel.uiDataState.collectAsState()
        val state = collected.value

        Scaffold(
            drawerContent = {
              LessonDrawer(
                  chapter = "", //chapter.title,
                  lesson = state.lesson.title,
                  lessonPages = state.lesson.pages.map { it.title },
                  currentPage = state.currentPage?.title ?: ""
              )
            }
        ) {
          LessonPage(
              progress = state.progress ?: 0f,
              state.currentPage?.title ?: "Finished"
          ) {
            val page = state.currentPage
            if (page != null) {
              when (page) {
                is LessonPage.InfoPage ->
                  InfoLessonPage(
                      Parser.builder().build().parse(page.markdown),
                      nextPage = viewModel::nextPage,
                  )
                is LessonPage.CodeQuestionPage ->
                  CodeQuizPage(
                      model = CodeQuestionPageViewModel(page),
                      nextQuestion = viewModel::nextPage,
                  )
              }
            } else {
              Button(
                  onClick = {
                    findNavController().navigate(
                        R.id.action_lessonFragment_to_chapterListFragment
                    )
                  }
              ) {
                Text("Finished")
              }
            }
          }
        }
      }
    }
  }
}
