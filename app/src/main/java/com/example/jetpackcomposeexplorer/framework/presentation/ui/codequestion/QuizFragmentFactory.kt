package com.example.jetpackcomposeexplorer.framework.presentation.ui.codequestion

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.jetpackcomposeexplorer.business.data.course.abstraction.CourseRepository
import com.example.jetpackcomposeexplorer.framework.datasource.service.LessonDaoService
import javax.inject.Inject

class QuizFragmentFactory
@Inject
constructor(
    private val courseRepository: CourseRepository,
    private val lessonDaoService: LessonDaoService,
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            QuizFragment::class.java.name -> {
                val fragment = QuizFragment(courseRepository, lessonDaoService)
                fragment
            }

            else -> super.instantiate(classLoader, className)
        }
    }
}
