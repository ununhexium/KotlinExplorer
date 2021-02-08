package com.example.jetpackcomposeexplorer.framework.presentation.ui.codequestion

import com.example.jetpackcomposeexplorer.business.domain.LessonBrowser
import com.example.jetpackcomposeexplorer.business.domain.LessonProgress
import com.example.jetpackcomposeexplorer.business.domain.state.DataState
import com.example.jetpackcomposeexplorer.business.interactor.ActiveElements
import com.example.jetpackcomposeexplorer.business.interactor.abstraction.SaveLessonProgress
import com.example.jetpackcomposeexplorer.framework.presentation.common.BaseViewModel
import com.example.jetpackcomposeexplorer.framework.presentation.ui.codequestion.state.LessonStateEvent
import com.example.jetpackcomposeexplorer.framework.presentation.ui.codequestion.state.LessonStateEvent.GoToNextPage
import com.example.jetpackcomposeexplorer.framework.presentation.ui.codequestion.state.LessonViewState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flow

@ExperimentalCoroutinesApi
@FlowPreview
class QuizViewModel(
    private val saveLessonProgress: SaveLessonProgress,
) : BaseViewModel<LessonViewState, LessonStateEvent>() {

  override fun initNewViewState(): LessonViewState {
    return LessonViewState(
        lesson = LessonBrowser.getLessonById(ActiveElements.activeLessonId),
        page = 0,
    )
  }

  override fun handleNewData(data: LessonViewState) {
    data.lesson.let {
      setViewState(
          getCurrentViewStateOrNew().also {
            it.lesson = data.lesson
          }
      )
    }
  }

  override fun setStateEvent(stateEvent: LessonStateEvent) {
    launchJob(
        stateEvent,
        when (stateEvent) {
          GoToNextPage -> {
            goToNextPageFlow()
          }
        }
    )
  }

  private fun goToNextPageFlow() = flow {
    val state = viewState.value
    val lesson = state?.lesson

    val result = if (lesson != null) {
      val currentPage = state.page ?: 0

      if (currentPage < lesson.pages.size) {
        DataState.data(
            data = LessonViewState(
                page = currentPage + 1,
                lesson = viewState.value!!.lesson
            )
        )
      } else {
        saveLessonProgress(LessonProgress(lesson.id, 1, 0))
        DataState.data()
      }
    } else {
      null
    }

    emit(result)
  }

  fun goToNextPage() {
    setStateEvent(GoToNextPage)
  }
}
