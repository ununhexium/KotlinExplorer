package com.example.jetpackcomposeexplorer.framework.presentation.ui.codequestion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.jetpackcomposeexplorer.business.course.data.kotlin.LessonFinderImpl
import com.example.jetpackcomposeexplorer.business.course.LessonPage.InfoPage
import com.example.jetpackcomposeexplorer.business.course.LessonPage.CodeQuestionPage
import com.example.jetpackcomposeexplorer.business.course.data.kotlin.LessonFinder
import com.example.jetpackcomposeexplorer.framework.presentation.components.InfoLessonPage
import com.example.jetpackcomposeexplorer.framework.presentation.components.LessonPage
import com.example.jetpackcomposeexplorer.framework.presentation.components.code.CodeQuizPage
import com.example.jetpackcomposeexplorer.framework.presentation.components.frame.LessonDrawer
import org.commonmark.parser.Parser

class QuizFragment : Fragment() {

  val args: QuizFragmentArgs by navArgs()

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?,
  ): View {
    val lessonFinder:LessonFinder = LessonFinderImpl()
    return ComposeView(requireContext()).apply {
      val lesson = lessonFinder.findLessonById(args.lessonId)

      val viewModel = QuizViewModel(lesson.pages)

      setContent {
        Scaffold(
            drawerContent = {
              LessonDrawer(
                  chapter = lesson.chapter.title,
                  lesson = lesson.title,
                  lessonPages = lesson.pages.map { it.title },
                  currentPage = viewModel.page.value?.title
              )
            }
        ) {
          LessonPage(progress = viewModel.progress.value,
              viewModel.page.value?.title ?: "Finished") {
            val page = viewModel.page.value
            if (page != null) {
              when (page) {
                is InfoPage ->
                  InfoLessonPage(
                      Parser.builder().build().parse(page.markdown),
                      nextPage = viewModel::goToNextPage,
                  )
                is CodeQuestionPage ->
                  CodeQuizPage(
                      model = CodeQuestionPageViewModel(page),
                      nextQuestion = viewModel::goToNextPage,
                  )
              }
            } else {
              Text("Finished")
            }
          }

        }
      }
    }
  }
}
