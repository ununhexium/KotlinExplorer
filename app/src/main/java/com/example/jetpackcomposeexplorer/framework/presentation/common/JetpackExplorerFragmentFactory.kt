package com.example.jetpackcomposeexplorer.framework.presentation.common

import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackcomposeexplorer.framework.presentation.chapterlist.ChapterListFragment
import com.example.jetpackcomposeexplorer.framework.presentation.ui.codequestion.QuizFragment
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

        QuizFragment::class.java.name -> {
          QuizFragment(viewModelFactory)
        }

        else -> {
          super.instantiate(classLoader, className)
        }
      }
}