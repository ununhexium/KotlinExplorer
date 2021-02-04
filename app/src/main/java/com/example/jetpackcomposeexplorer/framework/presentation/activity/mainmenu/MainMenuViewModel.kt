package com.example.jetpackcomposeexplorer.framework.presentation.activity.mainmenu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.jetpackcomposeexplorer.business.data.course.kotlin.KotlinTheme
import com.example.jetpackcomposeexplorer.business.interactors.ChapterBrowser
import com.example.jetpackcomposeexplorer.framework.presentation.activity.mainmenu.state.MainMenuStateEvent
import com.example.jetpackcomposeexplorer.framework.presentation.activity.mainmenu.state.MainMenuStateEvent.None
import com.example.jetpackcomposeexplorer.framework.presentation.activity.mainmenu.state.MainMenuStateEvent.SelectNextChapterEvent
import com.example.jetpackcomposeexplorer.framework.presentation.activity.mainmenu.state.MainMenuViewState
import com.example.jetpackcomposeexplorer.utils.AbsentLiveData
import com.example.jetpackcomposeexplorer.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class MainMenuViewModel
@Inject
constructor(private val chapterBrowser: ChapterBrowser) : ViewModel() {

  private val _stateEvent: MutableLiveData<MainMenuStateEvent> = MutableLiveData()
  private val _viewState: MutableLiveData<MainMenuViewState> = MutableLiveData(
      MainMenuViewState(KotlinTheme.modules.first().chapters.first())
  )

  val viewState: LiveData<MainMenuViewState>
    get() = _viewState


  val dataState: Flow<DataState<MainMenuViewState>> = Transformations
      .switchMap(_stateEvent) { stateEvent ->
        stateEvent?.let {
          handleStateEvent(stateEvent)
        }
      }

  fun handleStateEvent(stateEvent: MainMenuStateEvent): Flow<DataState<MainMenuViewState>> {
    println("DEBUG: New StateEvent detected: $stateEvent")
    when (stateEvent) {
      is SelectNextChapterEvent -> {
        return _viewState.value?.let {
          chapterBrowser.getNextChapter(_viewState.value)
        }
      }
      is None -> {
        return AbsentLiveData.create()
      }
    }
  }

  fun setBlogListData(blogPosts: List<BlogPost>) {
    val update = getCurrentViewStateOrNew()
    update.blogPosts = blogPosts
    _viewState.value = update
  }

  fun setUser(user: User) {
    val update = getCurrentViewStateOrNew()
    update.user = user
    _viewState.value = update
  }

  fun getCurrentViewStateOrNew(): MainMenuViewState {
    val value = viewState.value?.let {
      it
    } ?: MainMenuViewState()
    return value
  }

  fun setStateEvent(event: MainMenuStateEvent) {
    val state: MainMenuStateEvent
    state = event
    _stateEvent.value = state
  }
}
