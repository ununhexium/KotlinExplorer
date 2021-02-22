package net.lab0.kotlinexplorer.framework.presentation.fragment.lesson

import com.google.common.truth.Truth.assertThat
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.MultipleChoiceModel
import org.junit.jupiter.api.Test

internal class MultipleChoiceModelTest {
  @Test
  fun `can select an answer`() {
    // given
    val mcm = MultipleChoiceModel(
        LessonPage.MultipleChoice(
            "title",
            "?",
            "because",
            listOf(
                "A"
            ),
            answer = setOf(0)
        ),
        false
    )

    val answers = mcm.answers.value
    assertThat(mcm.isCorrectAnswer()).isFalse()

    // when
    mcm.toggle(answers[0])

    // then
    assertThat(mcm.isCorrectAnswer()).isTrue()
  }

  @Test
  fun `can select and deselect answers`() {
    // given
    val mcm = MultipleChoiceModel(
        LessonPage.MultipleChoice(
            "title",
            "?",
            "because",
            listOf(
                "A", "B", "C"
            ),
            answer = setOf(0, 2)
        ),
        false
    )

    // when
    // select all
    mcm.answers.value.forEach {
      mcm.toggle(it)
    }
    assertThat(mcm.isCorrectAnswer()).isFalse()
    // unselect the second
    mcm.toggle(mcm.answers.value[1])

    // then
    assertThat(mcm.isCorrectAnswer()).isTrue()
  }
}