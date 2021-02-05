package com.example.jetpackcomposeexplorer.framework.presentation.chapterlist.state

import com.example.jetpackcomposeexplorer.framework.presentation.chapterlist.model.Chapter

data class ChapterListViewState(
    var chapters: List<Chapter>? = null,
)
