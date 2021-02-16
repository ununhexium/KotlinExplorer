package net.lab0.kotlinexplorer.framework.presentation.ui.codequestion

import net.lab0.kotlinexplorer.framework.presentation.components.code.Answer
import net.lab0.kotlinexplorer.framework.presentation.fragment.lesson.CodeQuestionPageViewModel
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

class CodeQuestionPageViewModelTest {

  @Test
  fun `can add answers by string`() {
    // given
    val vm = CodeQuestionPageViewModel("", "", "",listOf("a", "b", "c"), listOf())

    // then
    assertThat(vm.possibleAnswers.value).isEqualTo(
        listOf(
            Answer(0, "a", false),
            Answer(1, "b", false),
            Answer(2, "c", false),
        )
    )
  }

  @Test
  fun `can select an answer when it is present`() {
    // given
    val vm = CodeQuestionPageViewModel("", "", "", listOf("Foo"), listOf("Foo"))

    // when
    val selection = vm.possibleAnswers.value.first()
    vm.select(selection)

    // then
    assertThat(vm.possibleAnswers.value.first()).isEqualTo(selection.copy(used = true))
    assertThat(vm.selected.value.first()).isEqualTo(selection.copy(used = true))
  }

  @Test
  fun `ignore double selection requests`() {
    // given
    val vm = CodeQuestionPageViewModel("", "", "", listOf("Foo"), listOf("Foo"))
    val firstAnswer = vm.possibleAnswers.value.first()
    vm.select(firstAnswer)

    // when
    vm.select(firstAnswer)

    // then
    assertThat(vm.selected.value).hasSize(1)
  }

  @Test
  fun `can undo selection`() {
    // given
    val vm = CodeQuestionPageViewModel("", "", "", listOf("Foo"), listOf("Foo"))
    val firstAnswer = vm.possibleAnswers.value.first()
    vm.select(firstAnswer)

    // when
    vm.undo()

    // then
    assertThat(vm.possibleAnswers.value).hasSize(1)
    assertThat(vm.selected.value).hasSize(0)
  }

  @Test
  fun `failsafe when no selection to undo`() {
    // given
    val vm = CodeQuestionPageViewModel("", "", "", listOf("Foo"), listOf("Foo"))

    // when
    vm.undo()

    // then no exception
  }

  @Test
  fun `can tell when the answer can be validated`() {
    // given
    val vm = CodeQuestionPageViewModel("", "", "", listOf("Foo", "Bar"), listOf("Foo", "Bar"))

    // no selection
    assertThat(vm.canValidate).isEqualTo(false)

    // not enough selections
    vm.select(vm.possibleAnswers.value[0])
    assertThat(vm.canValidate).isEqualTo(false)

    // when select one more
    vm.select(vm.possibleAnswers.value[1])
    assertThat(vm.canValidate).isEqualTo(true)

    // when unselecting
    vm.undo()
    assertThat(vm.canValidate).isEqualTo(false)
  }

  @Test
  fun `silly 0 selection case`() {
    // given
    val vm = CodeQuestionPageViewModel("", "", "", listOf(), listOf())

    // then
    assertThat(vm.canValidate).isEqualTo(true)
  }

  @Test
  fun `can validate the answer`() {
    // given
    val vm = CodeQuestionPageViewModel("", "", "", listOf("Foo"), listOf("Foo"))
    vm.select(vm.possibleAnswers.value.first())

    // when
    val validated = vm.isCorrectAnswer()

    // then
    assertThat(validated).isTrue()
  }
}
