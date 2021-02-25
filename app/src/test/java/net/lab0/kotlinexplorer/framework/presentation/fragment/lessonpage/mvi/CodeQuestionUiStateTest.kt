package net.lab0.kotlinexplorer.framework.presentation.fragment.lessonpage.mvi

import com.google.common.truth.Truth.assertThat
import net.lab0.kotlinexplorer.business.domain.Chapter
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.codequestion.mvi.CodeQuestionUiState
import org.junit.jupiter.api.Test
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder as p

internal class CodeQuestionUiStateTest {
  @Test
  fun `truth is computed based on the string content, not the chosen indices`() {
    // given
    val page = LessonPage.CodeQuestionPage(
        "",
        "",
        "",
        "",
        answer = listOf("A", "B"),
        confusion = listOf("A"),
        choices = listOf("A", "B", "A")
    )
    val l = CodeQuestionUiState(
        pageIndex = 0,
        lessonPage = page,
        chapter = Chapter.EMPTY,
        selectedAnswers = listOf(2, 1),
    )

    // then
    assertThat(l.isCorrectAnswer).isTrue()
  }

  @Test
  fun `can tell which spots to focus when wrong answer`() {
    // given
    val page = LessonPage.CodeQuestionPage(
        "",
        "",
        "val ${p(0)} ${p(1)} ${p(2)}${p(3)}",
        "",
        answer = listOf("alpha", "=", "116", ";"),
        confusion = listOf("foo", ":="),
        choices = listOf("alpha", "=", "116", ";", "foo", ":=")
    )
    val state = CodeQuestionUiState(
        pageIndex = 0,
        lessonPage = page,
        chapter = Chapter.EMPTY,
        selectedAnswers = listOf(0, 5, 2, 4),
    )

    // when
    val locations = state.correctedAnswersLocations

    // then
    // 0123456789012345
    // val alpha = 116;
    assertThat(locations).isEqualTo(listOf(10 .. 10, 15 .. 15))
  }

  @Test
  fun `can tell which spots to focus when wrong answer 2`() {
    // given
    val page = LessonPage.CodeQuestionPage(
        "",
        "",
        """${p(0)} main${p(1)}${p(2)} ${p(3)}
  // next steps
${p(4)}
""",
        "",
        answer = listOf("fun", "(", ")", "{", "}"),
        confusion = listOf("[", "]"),
        choices = listOf("fun", "(", ")", "{", "}", "[", "]")
    )
    val state = CodeQuestionUiState(
        pageIndex = 0,
        lessonPage = page,
        chapter = Chapter.EMPTY,
        selectedAnswers = listOf(0, 3, 4, 5, 6),
    )

    // when
    val locations = state.correctedAnswersLocations

    // then
    // 0         10        20        30
    // 012345678901234567890123456789012345
    // fun main() {␤  // next steps␤}
    assertThat(locations).isEqualTo(
        listOf(
            8 .. 8,
            9 .. 9,
            11 .. 11,
            29 .. 29,
        )
    )
  }

  @Test
  fun `can tell which spots to focus when wrong answer and duplicated ids`() {
    // given
    val page = LessonPage.CodeQuestionPage(
        "",
        "",
        """${p(0)}Hello${p(0)}""",
        "",
        answer = listOf("\""),
        confusion = listOf("'"),
        choices = listOf("\"", "'")
    )
    val state = CodeQuestionUiState(
        pageIndex = 0,
        lessonPage = page,
        chapter = Chapter.EMPTY,
        selectedAnswers = listOf(1),
    )

    // when
    val locations = state.correctedAnswersLocations

    // then
    // 0123456
    // "Hello"
    assertThat(locations).containsExactly(0 .. 0, 6 .. 6)
  }

}
