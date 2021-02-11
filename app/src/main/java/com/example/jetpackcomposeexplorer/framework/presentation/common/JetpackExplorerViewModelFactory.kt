package com.example.jetpackcomposeexplorer.framework.presentation.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackcomposeexplorer.business.interactor.abstraction.GetAllChapters
import com.example.jetpackcomposeexplorer.business.interactor.abstraction.GetLessonsInProgress
import com.example.jetpackcomposeexplorer.business.interactor.abstraction.SaveLessonProgress
import com.example.jetpackcomposeexplorer.framework.presentation.chapterlist.ChapterListViewModel
import com.example.jetpackcomposeexplorer.framework.presentation.lesson.LessonViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject


@FlowPreview
@ExperimentalCoroutinesApi
class JetpackExplorerViewModelFactory
@Inject
constructor(
    private val getAllChapters: GetAllChapters,
    private val getLessonsInProgress: GetLessonsInProgress,
    private val saveLessonProgress: SaveLessonProgress,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when(modelClass){

            ChapterListViewModel::class.java -> {
                ChapterListViewModel(
                    getLessonsInProgress,
                    getAllChapters
                ) as T
            }

            LessonViewModel::class.java -> {
                LessonViewModel(
                    saveLessonProgress
                ) as T
            }

            else -> {
                throw IllegalArgumentException("unknown model class $modelClass")
            }
        }
    }
}
