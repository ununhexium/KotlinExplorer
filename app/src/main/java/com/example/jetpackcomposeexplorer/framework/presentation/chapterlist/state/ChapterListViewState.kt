package com.example.jetpackcomposeexplorer.framework.presentation.chapterlist.state

import com.example.jetpackcomposeexplorer.framework.presentation.chapterlist.model.ChapterUi

data class ChapterListViewState(
    var chapters: List<ChapterUi>? = null,
    var chaptersInProgress: Int? = null,
)
