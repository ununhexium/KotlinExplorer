package net.lab0.kotlinexplorer.framework.presentation.fragment.lessonpage

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import net.lab0.kotlinexplorer.framework.presentation.fragment.lessonpage.mvi.LessonPageUiState
import net.lab0.kotlinexplorer.utils.printLogD
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
internal class LessonPageViewModelTest {

  val answerA = Answer(0, "A")
  val answerB = Answer(1, "B")
  val answerC = Answer(2, "C")

  val allAnswers = listOf(answerA, answerB, answerC)

  private val testDispatcher = TestCoroutineDispatcher()

  fun getVm() = LessonPageViewModel(testDispatcher).also {
    it.init(116, allAnswers)
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
    val vm = LessonPageViewModel()

    // when
    vm.init(116, allAnswers)

    // then
    assertThat(vm.uiDataState.value).isEqualTo(LessonPageUiState(116, allAnswers))
  }

  @Test
  fun canSelectAnAnswer_WhenItsInTheAvailableChoices() = runBlocking {
    // given
    val vm = getVm()

    // when
    vm.select(answerA)

    // wait
    testDispatcher.advanceUntilIdle()

    // then
    assertThat(
        vm.uiDataState.value
    ).isEqualTo(
        LessonPageUiState(116, allAnswers, listOf(answerA))
    )
  }

  @Test
  fun undoHasNoEffect_WhenTheSelectionListIsEmpty() {
    // given
    val vm = LessonPageViewModel()

    // when
    vm.undo()

    // then no exception, no change
    assertThat(
        vm.uiDataState.value
    ).isEqualTo(
        LessonPageUiState(0, listOf(), listOf())
    )
  }

  @Test
  fun corou() = runBlockingTest {
    // given
    val vm = getVm()

    // when
    vm.incScore()

    // wait
    testDispatcher.advanceUntilIdle()

    // then
    assertThat(vm.score).isEqualTo(1)
  }
}
