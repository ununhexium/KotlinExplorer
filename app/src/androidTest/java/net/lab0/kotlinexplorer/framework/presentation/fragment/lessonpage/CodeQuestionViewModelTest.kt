package net.lab0.kotlinexplorer.framework.presentation.fragment.lessonpage

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import net.lab0.kotlinexplorer.business.course.data.kotlin.helloworld.HelloWorldChapter
import net.lab0.kotlinexplorer.business.domain.Chapter
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.codequestion.CodeQuestionViewModel
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.codequestion.mvi.CodeQuestionUiState
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
internal class CodeQuestionViewModelTest {

  private val allAnswers = LessonPage.CodeQuestionPage(
      "",
      "",
      "",
      "",
      listOf("A", "B", "C"),
      listOf("D")
  )

  private val testDispatcher = TestCoroutineDispatcher()

  private fun getVm() = CodeQuestionViewModel(testDispatcher).also {
    it.init(0, allAnswers, Chapter.EMPTY)
  }

  @Before
  fun setup() {
    Dispatchers.setMain(testDispatcher)
  }

  @After
  fun tearDown() {
    Dispatchers.resetMain()
    testDispatcher.cleanupTestCoroutines()
  }

  @Test
  fun canInitializeWithData() {
    // given
    val vm = CodeQuestionViewModel()

    // when
    vm.init(116, allAnswers, HelloWorldChapter)

    // then
    assertThat(vm.uiDataState.value).isEqualTo(
        CodeQuestionUiState(
            116,
            allAnswers,
            HelloWorldChapter
        )
    )
  }

  @Test
  fun canSelectAnAnswer_WhenItsInTheAvailableChoices() = runBlocking {
    // given
    val vm = getVm()

    // when
    vm.select(0)

    // wait
    testDispatcher.advanceUntilIdle()

    // then
    assertThat(
        vm.uiDataState.value
    ).isEqualTo(
        CodeQuestionUiState(0, allAnswers, Chapter.EMPTY, listOf(0))
    )
  }

  @Test
  fun canSelectAWrongAnswer(): Unit = runBlocking {
    // given
    val vm = getVm()

    // when
    vm.select(3)

    // wait
    testDispatcher.advanceUntilIdle()

    // then
    assertThat(
        vm.uiDataState.value
    ).isEqualTo(
        CodeQuestionUiState(0, allAnswers, Chapter.EMPTY, listOf(3))
    )
  }

  @Test
  fun cannotSelectAnAnswer_WhenItsAlreadySelected() = runBlocking {
    // given
    val vm = getVm()

    // when
    vm.select(0)
    vm.select(0)

    // wait
    testDispatcher.advanceUntilIdle()

    // then
    assertThat(
        vm.uiDataState.value
    ).isEqualTo(
        CodeQuestionUiState(0, allAnswers, Chapter.EMPTY, listOf(0))
    )
  }

  @Test
  fun cannotSelectAnAnswer_WhenItsNotInTheChoices() = runBlocking {
    // given
    val vm = getVm()

    // when
    vm.select(999)

    // wait
    testDispatcher.advanceUntilIdle()

    // then
    assertThat(
        vm.uiDataState.value
    ).isEqualTo(
        CodeQuestionUiState(0, allAnswers, Chapter.EMPTY, listOf())
    )
  }

  @Test
  fun undoHasNoEffect_WhenTheSelectionListIsEmpty() {
    // given
    val vm = CodeQuestionViewModel()

    // when
    vm.undo()

    // wait
    testDispatcher.advanceUntilIdle()

    // then no exception, no change
    assertThat(
        vm.uiDataState.value
    ).isEqualTo(
        CodeQuestionUiState(0, LessonPage.CodeQuestionPage.EMPTY, Chapter.EMPTY, listOf())
    )
  }

  @Test
  fun undoDeselectsTheLastChoice() {
    // given
    val vm = getVm()

    // when
    vm.select(0)
    vm.select(1)
    vm.undo()

    // wait
    testDispatcher.advanceUntilIdle()

    // then no exception, no change
    assertThat(
        vm.uiDataState.value
    ).isEqualTo(
        CodeQuestionUiState(0, allAnswers, Chapter.EMPTY, listOf(0))
    )
  }

  @Test
  fun resetDeselectsEverything() {
    // given
    val vm = getVm()

    // when
    vm.select(0)
    vm.select(1)
    vm.select(2)
    vm.reset()

    // wait
    testDispatcher.advanceUntilIdle()

    // then no exception, no change
    assertThat(
        vm.uiDataState.value
    ).isEqualTo(
        CodeQuestionUiState(0, allAnswers, Chapter.EMPTY, listOf())
    )
  }

  @Test
  fun validateFreezesTheInteractions() {
    // given
    val vm = getVm()

    // when
    vm.select(0)

    vm.validate()
    vm.reset()
    vm.undo()
    vm.select(1)

    // wait
    testDispatcher.advanceUntilIdle()

    // then no exception, no change
    assertThat(
        vm.uiDataState.value
    ).isEqualTo(
        CodeQuestionUiState(0, allAnswers, Chapter.EMPTY, listOf(0), locked = true)
    )
  }
}
