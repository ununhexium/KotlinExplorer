package net.lab0.kotlinexplorer.framework.presentation.fragment.lessonpage.mvi

import com.google.common.truth.Truth.assertThat
import net.lab0.kotlinexplorer.business.domain.Chapter
import net.lab0.kotlinexplorer.business.domain.LessonPage
import org.junit.jupiter.api.Test

internal class LessonPageUiStateTest {
  @Test
  fun `truth is computed based on the string content, not the chosen indices`() {
    // given
    val l = LessonPageUiState(
        pageIndex = 0,
        lessonPage = LessonPage.CodeQuestionPage("", "", "", "", listOf("A", "B"), listOf("A")),
        chapter = Chapter.EMPTY,
        selectedAnswers = listOf(2, 1),
    )

    // then
    assertThat(l.isCorrectAnswer).isTrue()
  }
}
