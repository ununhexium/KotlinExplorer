package com.example.jetpackcomposeexplorer.presentation.ui.codequestion

import com.example.jetpackcomposeexplorer.presentation.components.code.Answer
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CodeQuestionViewModelTest {
  @Test
  fun `can add an answer`() {
    // given
    val foo = Answer(0, "Foo", false)
    val vm = CodeQuestionViewModel("", "", "", 1, foo) { false }

    // then
    assertThat(vm.answers.value.first()).isEqualTo(foo)
  }

  @Test
  fun `can add answers by string`() {
    // given
    val vm = CodeQuestionViewModel("", "", "",1, "a", "b", "c") { false }

    // then
    assertThat(vm.answers.value).isEqualTo(
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
    val foo = Answer(0, "Foo", false)
    val vm = CodeQuestionViewModel("", "", "", 1, foo) { false }

    // when
    vm.select(foo)

    // then
    val selectedFoo = foo.copy(used = true)
    assertThat(vm.answers.value.first()).isEqualTo(selectedFoo)
    assertThat(vm.selected.value.first()).isEqualTo(selectedFoo)
  }

  @Test
  fun `ignore double selection requests`() {
    // given
    val foo = Answer(0, "Foo", false)
    val vm = CodeQuestionViewModel("", "", "", 1, foo) { false }
    vm.select(foo)

    // when
    vm.select(foo)

    // then
    assertThat(vm.selected.value).hasSize(1)
  }

  @Test
  fun `can undo selection`() {
    // given
    val foo = Answer(0, "Foo", false)
    val vm = CodeQuestionViewModel("", "", "", 1, foo) { false }
    vm.select(foo)

    // when
    vm.undo()

    // then
    assertThat(vm.answers.value).isEqualTo(listOf(foo))
    assertThat(vm.selected.value).isEqualTo(setOf<Answer>())
  }

  @Test
  fun `failsafe when no selection do undo`() {
    // given
    val foo = Answer(0, "Foo", false)
    val vm = CodeQuestionViewModel("", "", "", 1, foo) { false }

    // when
    vm.undo()

    // then no exception
  }

  @Test
  fun `can limit the number of answers`() {
    // given
    val foo = Answer(0, "Foo", false)
    val bar = Answer(0, "Bar", false)
    val vm = CodeQuestionViewModel("", "", "", 1, foo) { false }
    vm.select(foo)

    // when
    vm.select(bar)

    // then bar is not selected
    assertThat(vm.answers.value).isEqualTo(listOf(foo.copy(used = true)))
    assertThat(vm.selected.value).isEqualTo(setOf(foo.copy(used = true)))
  }

  @Test
  fun `can tell when the answer can be validated`() {
    // given
    val foo = Answer(0, "Foo", false)
    val bar = Answer(0, "Bar", false)
    val vm = CodeQuestionViewModel("", "", "", 2, foo) { false }

    // no selection
    assertThat(vm.canValidate).isEqualTo(false)

    // not enough selections
    vm.select(foo)
    assertThat(vm.canValidate).isEqualTo(false)

    // when select one more
    vm.select(bar)
    assertThat(vm.canValidate).isEqualTo(true)

    // when unselecting
    vm.undo()
    assertThat(vm.canValidate).isEqualTo(false)
  }

  @Test
  fun `silly 0 selection case`() {
    // given
    val vm = CodeQuestionViewModel("", "", "", 0, "a") { false }

    // then
    assertThat(vm.canValidate).isEqualTo(true)
  }

  @Test
  fun `can validate the answer`() {
    // given
    val foo = Answer(0, "Foo", false)
    val vm = CodeQuestionViewModel("", "", "", 1, foo) {
      it.size == 1 && it.first().text == "Foo"
    }
    vm.select(foo)

    // when
    val validated = vm.isCorrectAnswer()

    // then
    assertThat(validated).isTrue()
  }
}
