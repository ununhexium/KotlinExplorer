package net.lab0.kotlinexplorer.framework.presentation.common

import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import net.lab0.kotlinexplorer.framework.presentation.fragment.chapterlist.ChapterListFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.codequestion.CodeQuestionPageFragment
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.lessonfeedback.LessonFeedbackFragment
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
class JetpackExplorerFragmentFactory
@Inject
constructor(
    private val viewModelFactory: ViewModelProvider.Factory,
) : FragmentFactory() {

  override fun instantiate(classLoader: ClassLoader, className: String) =

      when (className) {
        ChapterListFragment::class.java.name -> {
          ChapterListFragment(viewModelFactory)
        }

        LessonFeedbackFragment::class.java.name -> {
          LessonFeedbackFragment(viewModelFactory)
        }

        CodeQuestionPageFragment::class.java.name -> {
          CodeQuestionPageFragment(viewModelFactory)
        }

        else -> {
          super.instantiate(classLoader, className)
        }
      }
}
