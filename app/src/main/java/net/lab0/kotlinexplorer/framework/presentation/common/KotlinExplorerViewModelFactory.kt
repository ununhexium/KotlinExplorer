package net.lab0.kotlinexplorer.framework.presentation.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import net.lab0.kotlinexplorer.business.interactor.abstraction.GetAllChapters
import net.lab0.kotlinexplorer.business.interactor.abstraction.GetLessonsInProgress
import net.lab0.kotlinexplorer.business.interactor.abstraction.ReloadLessonFeedback
import net.lab0.kotlinexplorer.business.interactor.abstraction.RequestExtraLessons
import net.lab0.kotlinexplorer.business.interactor.abstraction.SaveLessonProgress
import net.lab0.kotlinexplorer.business.interactor.abstraction.SendLessonFeedback
import net.lab0.kotlinexplorer.business.interactor.abstraction.SendProblemReport
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.lessonfeedback.LessonFeedbackViewModel
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.mvi.LessonViewModel
import net.lab0.kotlinexplorer.framework.presentation.activity.profile.UserProfileViewModel
import net.lab0.kotlinexplorer.framework.presentation.activity.welcome.login.LoginViewModel
import net.lab0.kotlinexplorer.framework.presentation.fragment.chapterlist.ChapterListViewModel
import javax.inject.Inject


@FlowPreview
@ExperimentalCoroutinesApi
class KotlinExplorerViewModelFactory
@Inject
constructor(
  private val getAllChapters: GetAllChapters,
  private val getLessonsInProgress: GetLessonsInProgress,
  private val saveLessonProgress: SaveLessonProgress,
  private val sendLessonFeedback: SendLessonFeedback,
  private val reloadLessonFeedback: ReloadLessonFeedback,
  private val sendProblemReport: SendProblemReport,
  private val requestExtraLessons: RequestExtraLessons,
  private val firebaseAuth: FirebaseAuth,
) : ViewModelProvider.Factory {

  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    return when (modelClass) {

      ChapterListViewModel::class.java -> {
        ChapterListViewModel(
          getLessonsInProgress,
          getAllChapters,
        ) as T
      }

      LessonViewModel::class.java -> {
        LessonViewModel(
          saveLessonProgress,
          sendProblemReport,
          requestExtraLessons,
        ) as T
      }

      LessonFeedbackViewModel::class.java -> {
        LessonFeedbackViewModel(
          sendLessonFeedback,
          reloadLessonFeedback,
        ) as T
      }

      LoginViewModel::class.java -> {
        LoginViewModel(
          firebaseAuth
        ) as T
      }

      UserProfileViewModel::class.java -> {
        UserProfileViewModel(
          firebaseAuth
        ) as T
      }

      else -> {
        throw IllegalArgumentException("unknown model class $modelClass")
      }
    }
  }
}
