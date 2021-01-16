package com.example.jetpackcomposeexplorer.presentation.ui.codequestion

import com.example.jetpackcomposeexplorer.presentation.components.Answer
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import java.lang.IllegalStateException

class CodeQuestionViewModelTest {

  lateinit var vm: CodeQuestionViewModel

  @Before
  fun before() {
    vm = CodeQuestionViewModel()
  }

  @Test
  fun `can add an answer`() {
    // given
    val foo = Answer(0, "Foo", false)
    vm.setChoices(1, foo)

    // then
    assertThat(vm.answers.value.first()).isEqualTo(foo)
  }

  @Test
  fun `can add answers by string`() {
    // given
    vm.setChoices(1, "a", "b", "c")

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
    vm.setChoices(1, foo)

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
    vm.setChoices(1, foo)
    vm.select(foo)

    // when
    vm.select(foo)

    // then
    assertThat(vm.selected.value).hasSize(1)
  }

  @Test(expected = IllegalStateException::class)
  fun `can only set the choices once and for all`() {
    // given
    val foo = Answer(0, "Foo", false)
    val bar = Answer(0, "Bar", false)
    vm.setChoices(1, foo)

    // when
    vm.setChoices(1, bar)

    // then exception
  }

  @Test
  fun `can undo selection`() {
    // given
    val foo = Answer(0, "Foo", false)
    vm.setChoices(1, foo)
    vm.select(foo)

    // when
    vm.undo()

    // then
    assertThat(vm.answers.value).isEqualTo(listOf(foo))
    assertThat(vm.selected.value).isEqualTo(setOf<Answer>())
  }

  @Test
  fun `can limit the number of answers`() {
    // given
    val foo = Answer(0, "Foo", false)
    val bar = Answer(0, "Bar", false)
    vm.setChoices(1, foo)
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
    vm.setChoices(2, foo)

    // no selection
    assertThat(vm.canValidate.value).isEqualTo(false)

    // not enough selections
    vm.select(foo)
    assertThat(vm.canValidate.value).isEqualTo(false)

    // when select one more
    vm.select(bar)
    assertThat(vm.canValidate.value).isEqualTo(true)

    // when unselecting
    vm.undo()
    assertThat(vm.canValidate.value).isEqualTo(false)
  }

  @Test
  fun `silly 0 selection case`() {
    // given
    vm.setChoices(0, "a")

    // then
    assertThat(vm.canValidate.value).isEqualTo(true)
  }
}
