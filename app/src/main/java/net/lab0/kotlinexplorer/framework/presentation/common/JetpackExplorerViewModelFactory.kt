package net.lab0.kotlinexplorer.framework.presentation.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.lab0.kotlinexplorer.business.interactor.abstraction.GetAllChapters
import net.lab0.kotlinexplorer.business.interactor.abstraction.GetLessonsInProgress
import net.lab0.kotlinexplorer.business.interactor.abstraction.SaveLessonProgress
import net.lab0.kotlinexplorer.framework.presentation.fragment.chapterlist.ChapterListViewModel
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.mvi.LessonViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import net.lab0.kotlinexplorer.business.interactor.abstraction.SendLessonFeedback
import net.lab0.kotlinexplorer.business.interactor.abstraction.SendProblemReport
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.lessonfeedback.LessonFeedbackViewModel
import javax.inject.Inject


@FlowPreview
@ExperimentalCoroutinesApi
class JetpackExplorerViewModelFactory
@Inject
constructor(
    private val getAllChapters: GetAllChapters,
    private val getLessonsInProgress: GetLessonsInProgress,
    private val saveLessonProgress: SaveLessonProgress,
    private val sendLessonFeedback: SendLessonFeedback,
    private val sendProblemReport: SendProblemReport,
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
                    saveLessonProgress,
                    sendProblemReport,
                ) as T
            }

            LessonFeedbackViewModel::class.java -> {
                LessonFeedbackViewModel(
                    sendLessonFeedback
                ) as T
            }

            else -> {
                throw IllegalArgumentException("unknown model class $modelClass")
            }
        }
    }
}
