package com.example.jetpackcomposeexplorer.framework.presentation.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.jetpackcomposeexplorer.business.course.Finder
import com.example.jetpackcomposeexplorer.business.course.FinderImpl
import com.example.jetpackcomposeexplorer.business.course.data.kotlin.KotlinTheme
import com.example.jetpackcomposeexplorer.business.course.data.kotlin.module1.Module1
import com.example.jetpackcomposeexplorer.framework.presentation.components.frame.ChapterCardData
import com.example.jetpackcomposeexplorer.framework.presentation.components.frame.ChapterList
import com.example.jetpackcomposeexplorer.framework.presentation.components.frame.ExploreDrawer
import com.example.jetpackcomposeexplorer.framework.presentation.components.frame.LessonListItemData

class HomeFragment : Fragment() {
  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?,
  ): View {
    // TODO: di
    val lessonFinder: Finder = FinderImpl(KotlinTheme)

    return ComposeView(requireContext()).apply {
      setContent {
        val scaffoldState = rememberScaffoldState()

        Scaffold(
            drawerContent = {
              ExploreDrawer(
//                nav = findNavController(),
                  onSelection = {
                    scaffoldState.drawerState.close()
                  },
              )
            }
        ) {
          ChapterList(
              chapters = lessonFinder.findChaptersInModule(Module1).map {
                ChapterCardData(
                    it.title,
                    0f,
                    lessonFinder.findLessonsInChapter(it).map { lesson ->
                      LessonListItemData(lesson.id, lesson.title, false)
                    }
                )
              },
              onPlay = { lessonId ->
                val action = HomeFragmentDirections.actionHomeFragmentToQuizFragment(lessonId)
                findNavController().navigate(action)
              }
          )
        }
      }
    }
  }
}
