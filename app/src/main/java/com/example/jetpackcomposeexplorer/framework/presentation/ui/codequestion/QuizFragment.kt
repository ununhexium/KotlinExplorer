package com.example.jetpackcomposeexplorer.framework.presentation.ui.codequestion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.jetpackcomposeexplorer.R
import com.example.jetpackcomposeexplorer.business.course.data.kotlin.KOTLIN
import com.example.jetpackcomposeexplorer.business.domain.LessonPage
import com.example.jetpackcomposeexplorer.framework.presentation.chapterlist.ChapterListFragmentDirections
import com.example.jetpackcomposeexplorer.framework.presentation.components.InfoLessonPage
import com.example.jetpackcomposeexplorer.framework.presentation.components.LessonPage
import com.example.jetpackcomposeexplorer.framework.presentation.components.code.CodeQuizPage
import com.example.jetpackcomposeexplorer.framework.presentation.components.frame.LessonDrawer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.commonmark.parser.Parser

class QuizFragment(
    private val viewModelFactory: ViewModelProvider.Factory,
) : Fragment() {

  val args: QuizFragmentArgs by navArgs()
  val viewModel: QuizViewModel by viewModels { viewModelFactory }

  @FlowPreview
  @ExperimentalCoroutinesApi
  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?,
  ): View {
    return ComposeView(requireContext()).apply {
      val lesson = KOTLIN.flatMap { it.lessons }.first { it.id == args.lessonId }
      val chapter = KOTLIN.first { it.lessons.any { it.id == args.lessonId } }

      setContent {
        Scaffold(
            drawerContent = {
              LessonDrawer(
                  chapter = chapter.title,
                  lesson = lesson.title,
                  lessonPages = lesson.pages.map { it.title },
                  currentPage = viewModel.viewState.value?.currentPage?.title
              )
            }
        ) {
          LessonPage(progress = viewModel.viewState.value?.progress ?: 0f,
              viewModel.viewState.value?.currentPage?.title ?: "Finished") {
            val page = viewModel.viewState.value?.currentPage
            if (page != null) {
              when (page) {
                is LessonPage.InfoPage ->
                  InfoLessonPage(
                      Parser.builder().build().parse(page.markdown),
                      nextPage = viewModel::goToNextPage,
                  )
                is LessonPage.CodeQuestionPage ->
                  CodeQuizPage(
                      model = CodeQuestionPageViewModel(page),
                      nextQuestion = viewModel::goToNextPage,
                  )
              }
            } else {
              Button(
                  onClick = {
                    findNavController().navigate(
                        R.id.action_quizFragment_to_chapterListFragment
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
