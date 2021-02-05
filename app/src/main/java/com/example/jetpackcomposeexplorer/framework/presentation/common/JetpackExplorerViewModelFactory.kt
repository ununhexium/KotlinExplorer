package com.example.jetpackcomposeexplorer.framework.presentation.common

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackcomposeexplorer.business.interactor.abstraction.GetLessonsInProgress
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview


@FlowPreview
@ExperimentalCoroutinesApi
class JetpackExplorerViewModelFactory
constructor(
    private val getLessonsInProgress: GetLessonsInProgress,
    private val editor: SharedPreferences.Editor,
    private val sharedPreferences: SharedPreferences
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when(modelClass){

//            ChapterListViewModel::class.java -> {
//                ChapterListViewModel(
//                    getLessonsInProgress
//                ) as T
//            }

            else -> {
                throw IllegalArgumentException("unknown model class $modelClass")
            }
        }
    }
}
