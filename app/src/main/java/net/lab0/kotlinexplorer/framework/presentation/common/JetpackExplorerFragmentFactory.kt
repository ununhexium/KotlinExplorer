package net.lab0.kotlinexplorer.framework.presentation.common

import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import net.lab0.kotlinexplorer.business.interactor.abstraction.RequestExtraLessons
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.InfoPageFragment
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.LessonFirstPageFragment
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.codequestion.CodeQuestionFragment
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.lessonfeedback.LessonFeedbackFragment
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.multiplechoice.MultipleChoiceFragment
import net.lab0.kotlinexplorer.framework.presentation.activity.morelessons.ExtraContentFragment
import net.lab0.kotlinexplorer.framework.presentation.activity.profile.UserProfileOverviewFragment
import net.lab0.kotlinexplorer.framework.presentation.activity.welcome.login.LoginFragment
import net.lab0.kotlinexplorer.framework.presentation.fragment.chapterlist.ChapterListFragment
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
class JetpackExplorerFragmentFactory
@Inject
constructor(
  private val viewModelFactory: ViewModelProvider.Factory,
  private val requestExtraLessons: RequestExtraLessons,
) : FragmentFactory() {

  override fun instantiate(classLoader: ClassLoader, className: String) =

    when (className) {
      ChapterListFragment::class.java.name -> {
        ChapterListFragment(viewModelFactory)
      }

      CodeQuestionFragment::class.java.name -> {
        CodeQuestionFragment(viewModelFactory)
      }

      ExtraContentFragment::class.java.name -> {
        ExtraContentFragment(requestExtraLessons)
      }

      InfoPageFragment::class.java.name -> {
        InfoPageFragment(viewModelFactory)
      }

      LessonFeedbackFragment::class.java.name -> {
        LessonFeedbackFragment(viewModelFactory)
      }

      LessonFirstPageFragment::class.java.name -> {
        LessonFirstPageFragment(viewModelFactory)
      }

      LoginFragment::class.java.name -> {
        LoginFragment(viewModelFactory)
      }

      MultipleChoiceFragment::class.java.name -> {
        MultipleChoiceFragment(viewModelFactory)
      }

      UserProfileOverviewFragment::class.java.name -> {
        UserProfileOverviewFragment(viewModelFactory)
      }

      else -> {
        super.instantiate(classLoader, className)
      }
    }
}
