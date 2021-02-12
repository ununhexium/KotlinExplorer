package net.lab0.jetpackcomposeexplorer.framework.presentation.common

import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import net.lab0.jetpackcomposeexplorer.framework.presentation.fragment.chapterlist.ChapterListFragment
import net.lab0.jetpackcomposeexplorer.framework.presentation.fragment.lesson.LessonFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
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

        LessonFragment::class.java.name -> {
          LessonFragment(viewModelFactory)
        }

        else -> {
          super.instantiate(classLoader, className)
        }
      }
}
