package com.example.jetpackcomposeexplorer.framework.presentation.ui.codequestion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.jetpackcomposeexplorer.business.course.Finder
import com.example.jetpackcomposeexplorer.business.course.FinderImpl
import com.example.jetpackcomposeexplorer.business.course.LessonPage.CodeQuestionPage
import com.example.jetpackcomposeexplorer.business.course.LessonPage.InfoPage
import com.example.jetpackcomposeexplorer.business.course.data.kotlin.KotlinTheme
import com.example.jetpackcomposeexplorer.framework.datasource.database.LessonDao
import com.example.jetpackcomposeexplorer.framework.datasource.database.LessonEntity
import com.example.jetpackcomposeexplorer.framework.datasource.mapper.LessonMapper
import com.example.jetpackcomposeexplorer.framework.datasource.service.LessonDaoService
import com.example.jetpackcomposeexplorer.framework.datasource.service.LessonDaoServiceImpl
import com.example.jetpackcomposeexplorer.framework.presentation.components.InfoLessonPage
import com.example.jetpackcomposeexplorer.framework.presentation.components.LessonPage
import com.example.jetpackcomposeexplorer.framework.presentation.components.code.CodeQuizPage
import com.example.jetpackcomposeexplorer.framework.presentation.components.frame.LessonDrawer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.commonmark.parser.Parser

@AndroidEntryPoint
class QuizFragment(
    private val lessonFinder: Finder,
    private val lessonDaoService: LessonDaoService,
) : Fragment() {

  val args: QuizFragmentArgs by navArgs()

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?,
  ): View {

    return ComposeView(requireContext()).apply {
      lifecycleScope.launch {
        val lesson = lessonDaoService.getOrCreateLesson(args.lessonId)

        val viewModel = QuizViewModel(lesson, lessonDaoService)

        setContent {
          Scaffold(
              drawerContent = {
                LessonDrawer(
                    chapter = lessonFinder.findChapterOf(lesson.lessonData).title,
                    lesson = lesson.lessonData.title,
                    lessonPages = lesson.lessonData.pages.map { it.title },
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
}
