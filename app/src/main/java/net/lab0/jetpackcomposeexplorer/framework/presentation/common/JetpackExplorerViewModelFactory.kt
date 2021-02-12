package net.lab0.jetpackcomposeexplorer.framework.presentation.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.lab0.jetpackcomposeexplorer.business.interactor.abstraction.GetAllChapters
import net.lab0.jetpackcomposeexplorer.business.interactor.abstraction.GetLessonsInProgress
import net.lab0.jetpackcomposeexplorer.business.interactor.abstraction.SaveLessonProgress
import net.lab0.jetpackcomposeexplorer.framework.presentation.fragment.chapterlist.ChapterListViewModel
import net.lab0.jetpackcomposeexplorer.framework.presentation.fragment.lesson.LessonViewModel
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
