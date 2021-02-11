package com.example.jetpackcomposeexplorer.framework.presentation.chapterlist.state

import com.example.jetpackcomposeexplorer.business.domain.Chapter
import com.example.jetpackcomposeexplorer.business.domain.LessonProgress
import com.example.jetpackcomposeexplorer.mvi.UiData

data class ChapterListViewState(
    val chapters: List<Chapter>,
    val lessonsInProgress: List<LessonProgress>,
): UiData
