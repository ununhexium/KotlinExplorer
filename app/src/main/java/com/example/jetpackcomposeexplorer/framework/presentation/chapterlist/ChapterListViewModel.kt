package com.example.jetpackcomposeexplorer.framework.presentation.chapterlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.jetpackcomposeexplorer.business.interactor.abstraction.GetLessonsInProgress
import com.example.jetpackcomposeexplorer.framework.presentation.chapterlist.state.ChapterListStateEvent
import com.example.jetpackcomposeexplorer.framework.presentation.chapterlist.state.ChapterListViewState

//class
//ChapterListViewModel
//@Inject
//constructor (
//    val getLessonsInProgress: GetLessonsInProgress
//) : ViewModel() {
//  private val _stateEvent: MutableLiveData<ChapterListStateEvent> = MutableLiveData()
//  private val _viewState: MutableLiveData<ChapterListViewState> = MutableLiveData()
//
//  val viewState: LiveData<*>
//    get() = _viewState
//
//}
