package net.lab0.kotlinexplorer.framework.presentation.fragment.lessonpage.mvi

import net.lab0.kotlinexplorer.framework.presentation.fragment.lessonpage.Answer
import net.lab0.kotlinexplorer.mvi.UiState

data class LessonPageUiState(
    val page: Int,
    val choices: List<Answer> = listOf(),
    val selectedAnswers: List<Answer> = listOf(),
): UiState
