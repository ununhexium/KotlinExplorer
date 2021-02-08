package com.example.jetpackcomposeexplorer.framework.presentation.ui.codequestion

import com.example.jetpackcomposeexplorer.business.domain.Lesson
import com.example.jetpackcomposeexplorer.business.domain.LessonBrowser
import com.example.jetpackcomposeexplorer.business.domain.LessonProgress
import com.example.jetpackcomposeexplorer.business.domain.state.DataState
import com.example.jetpackcomposeexplorer.business.interactor.ActiveElements
import com.example.jetpackcomposeexplorer.business.interactor.abstraction.SaveLessonProgress
import com.example.jetpackcomposeexplorer.framework.presentation.common.BaseViewModel
import com.example.jetpackcomposeexplorer.framework.presentation.ui.codequestion.state.LessonStateEvent
import com.example.jetpackcomposeexplorer.framework.presentation.ui.codequestion.state.LessonStateEvent.GoToNextPage
import com.example.jetpackcomposeexplorer.framework.presentation.ui.codequestion.state.LessonViewState
import com.example.jetpackcomposeexplorer.utils.printLogD
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flow

@ExperimentalCoroutinesApi
@FlowPreview
class QuizViewModel(
    private val saveLessonProgress: SaveLessonProgress,
) : BaseViewModel<LessonViewState, LessonStateEvent>() {

  override fun initNewViewState(): LessonViewState {
    val lessonId = ActiveElements.activeLessonId
    val lesson = LessonBrowser.getLessonById(lessonId)

    printLogD(
        QuizViewModel::class.java.simpleName,
        "Loading quiz model with lesson ID $lessonId: $lesson"
    )

    return LessonViewState(
        lesson = lesson,
        page = 0,
    )
  }

  override fun handleNewData(data: LessonViewState) {
    data.let { viewState ->
      setLesson(viewState.lesson)
      setPage(viewState.page)
    }
  }

  private fun setPage(page: Int) {
    val update = getCurrentViewStateOrNew()
    update.page = page
    setViewState(update)
  }

  private fun setLesson(lesson: Lesson) {
    val update = getCurrentViewStateOrNew()
    update.lesson = lesson
    setViewState(update)
  }

  override fun setStateEvent(stateEvent: LessonStateEvent) {
    val job = when (stateEvent) {
      GoToNextPage -> {
        goToNextPageFlow(stateEvent)
      }
    }

    launchJob(stateEvent, job)
  }

  private fun goToNextPageFlow(stateEvent: LessonStateEvent) = flow {
    val state = getCurrentViewStateOrNew()
    val lesson = state.lesson

    val currentPage = state.page

    emit(
        if (currentPage < lesson.pages.size) {
          DataState.data(
              data = LessonViewState(
                  page = currentPage + 1,
                  lesson = state.lesson,
              )
          )
        } else {
          saveLessonProgress(LessonProgress(lesson.id, 1, 0))
          DataState.data()
        }
    )
  }

  fun goToNextPage() {
    setStateEvent(GoToNextPage)
  }
}
