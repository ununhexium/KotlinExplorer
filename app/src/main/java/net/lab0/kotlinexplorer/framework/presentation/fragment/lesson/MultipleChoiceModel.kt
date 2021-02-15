package net.lab0.kotlinexplorer.framework.presentation.fragment.lesson

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.framework.presentation.components.code.Answer
import org.commonmark.parser.Parser

class MultipleChoiceModel(
    question: String,
    val explanation: String,
    val choices: List<Answer>,
    val answerValidator: (List<Answer>) -> Boolean,
) {
  constructor(page: LessonPage.MultipleChoice, shuffleAnswers: Boolean = true) : this(
      page.question,
      page.explanation,
      page.choices
          .mapIndexed { index, s -> Answer(index, s, false) }
          .let { if (shuffleAnswers) it.shuffled() else it },
      { answers -> page.answer == answers.map { it.id }.toSet() }
  )

  val questionMarkdown = Parser.builder().build().parse(question)
  val explanationMarkdown by lazy {
    Parser.builder().build().parse(
        explanation
    )
  }

  private val _answers: MutableState<List<Answer>> = mutableStateOf(choices)
  val answers: State<List<Answer>> = _answers

  val showAnswer = mutableStateOf(false)

  fun toggle(changedAnswer: Answer) {
    _answers.value = _answers.value.map { answer ->
      if (answer == changedAnswer) {
        answer.copy(used = !answer.used)
      } else answer
    }
    println("After edit" + answers.value)
  }

  fun isCorrectAnswer(): Boolean {
    return answerValidator(answers.value.filter { it.used })
  }

  fun validate() {
    showAnswer.value = true
  }
}
