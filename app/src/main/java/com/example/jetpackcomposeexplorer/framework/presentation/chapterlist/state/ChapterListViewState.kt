package com.example.jetpackcomposeexplorer.framework.presentation.chapterlist.state

import com.example.jetpackcomposeexplorer.business.domain.Chapter

data class ChapterListViewState(
    var chapters: List<Chapter>? = null,
    var chaptersInProgress: Int? = null,
)
