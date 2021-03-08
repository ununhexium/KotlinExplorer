package net.lab0.kotlinexplorer.framework.presentation.activity.lesson.multiplechoice.mvi

import com.google.common.truth.Truth.assertThat
import net.lab0.kotlinexplorer.business.domain.Chapter
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.framework.presentation.composable.lesson.MultipleChoiceAnswer
import org.junit.jupiter.api.Test

internal class MultipleChoiceUiStateTest {
  @Test
  fun `can list available choices before answering`() {
    // given
    val state = MultipleChoiceUiState(
      pageIndex = 0,
      lessonPage = LessonPage.MultipleChoice(
        title = "",
        question = "",
        explanation = "",
        choices = listOf("A", "B", "C", "D"),
        answer = setOf(0, 1)
      ),
      chapter = Chapter.EMPTY,
      selectedAnswers = listOf(),
      randomizedChoices = (0 .. 3).zip(listOf("A", "B", "C", "D")),
    )

    // when

    // then
    assertThat(state.choices).isEqualTo(
      listOf(
        MultipleChoiceAnswer(0, "A", false, null),
        MultipleChoiceAnswer(1, "B", false, null),
        MultipleChoiceAnswer(2, "C", false, null),
        MultipleChoiceAnswer(3, "D", false, null),
      )
    )
  }

  @Test
  fun `can list selected choices before answering`() {
    // given
    val state = MultipleChoiceUiState(
      pageIndex = 0,
      lessonPage = LessonPage.MultipleChoice(
        title = "",
        question = "",
        explanation = "",
        choices = listOf("A", "B", "C", "D"),
        answer = setOf(0, 1),
      ),
      chapter = Chapter.EMPTY,
      selectedAnswers = listOf(0, 2),
      randomizedChoices = (0 .. 3).zip(listOf("A", "B", "C", "D"))
    )

    // when

    // then
    assertThat(state.choices).isEqualTo(
      listOf(
        MultipleChoiceAnswer(0, "A", true, null),
        MultipleChoiceAnswer(1, "B", false, null),
        MultipleChoiceAnswer(2, "C", true, null),
        MultipleChoiceAnswer(3, "D", false, null),
      )
    )
  }

  @Test
  fun `can tell which choices are right or wrong`() {
    // given
    val state = MultipleChoiceUiState(
      pageIndex = 0,
      lessonPage = LessonPage.MultipleChoice(
        title = "",
        question = "",
        explanation = "",
        choices = listOf("A", "B", "C", "D"),
        answer = setOf(0, 1)
      ),
      chapter = Chapter.EMPTY,
      selectedAnswers = listOf(0, 2),
      locked = true,
      randomizedChoices = (0 .. 3).zip(listOf("A", "B", "C", "D")),
    )

    // when

    // then
    assertThat(state.choices).isEqualTo(
      listOf(
        MultipleChoiceAnswer(0, "A", true, true),
        MultipleChoiceAnswer(1, "B", false, false),
        MultipleChoiceAnswer(2, "C", true, false),
        MultipleChoiceAnswer(3, "D", false, true),
      )
    )
  }
}