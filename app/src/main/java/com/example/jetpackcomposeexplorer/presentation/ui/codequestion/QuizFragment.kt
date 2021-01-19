package com.example.jetpackcomposeexplorer.presentation.ui.codequestion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Text
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.jetpackcomposeexplorer.model.course.LessonPage
import com.example.jetpackcomposeexplorer.model.course.data.introduction
import com.example.jetpackcomposeexplorer.presentation.components.InfoLessonPage
import com.example.jetpackcomposeexplorer.presentation.components.code.CodeQuizPage
import com.example.jetpackcomposeexplorer.presentation.components.LessonPage
import org.commonmark.parser.Parser

class QuizFragment : Fragment() {
  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?,
  ): View {
    return ComposeView(requireContext()).apply {
      val viewModel = QuizViewModel(
          introduction
      )

      setContent {
        LessonPage(progress = viewModel.progress.value) {
          val page = viewModel.page.value
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
            Text("Finished")
          }
        }
      }
    }
  }
}
