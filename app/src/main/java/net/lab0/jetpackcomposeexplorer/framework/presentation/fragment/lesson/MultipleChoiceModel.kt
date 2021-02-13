package net.lab0.jetpackcomposeexplorer.framework.presentation.fragment.lesson

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import net.lab0.jetpackcomposeexplorer.business.domain.LessonPage
import net.lab0.jetpackcomposeexplorer.findAndEdit
import net.lab0.jetpackcomposeexplorer.framework.presentation.components.code.Answer
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

  val answers: MutableState<List<Answer>> = mutableStateOf(choices)
  val showAnswer = mutableStateOf(false)

  fun toggle(answer: Answer) {
    answers.findAndEdit(answer) {
      println("Before: $it")
      it.copy(used = !it.used).also { println("After: $it") }
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
