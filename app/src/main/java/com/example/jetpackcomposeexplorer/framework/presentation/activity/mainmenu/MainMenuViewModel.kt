package com.example.jetpackcomposeexplorer.framework.presentation.activity.mainmenu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.jetpackcomposeexplorer.framework.presentation.activity.mainmenu.state.MainMenuStateEvent
import com.example.jetpackcomposeexplorer.framework.presentation.activity.mainmenu.state.MainMenuStateEvent.None
import com.example.jetpackcomposeexplorer.framework.presentation.activity.mainmenu.state.MainMenuStateEvent.SelectNextChapterEvent
import com.example.jetpackcomposeexplorer.framework.presentation.activity.mainmenu.state.MainMenuViewState
import com.example.jetpackcomposeexplorer.utils.AbsentLiveData
import com.example.jetpackcomposeexplorer.utils.DataState

class MainMenuViewModel : ViewModel(){

//    private val _stateEvent: MutableLiveData<MainMenuStateEvent> = MutableLiveData()
//    private val _viewState: MutableLiveData<MainMenuViewState> = MutableLiveData()
//
//    val viewState: LiveData<MainMenuViewState>
//        get() = _viewState
//
//
//    val dataState: LiveData<DataState<MainMenuViewState>> = Transformations
//        .switchMap(_stateEvent){stateEvent ->
//            stateEvent?.let {
//                handleStateEvent(stateEvent)
//            }
//        }
//
//    fun handleStateEvent(stateEvent: MainMenuStateEvent): LiveData<DataState<MainMenuViewState>>{
//        println("DEBUG: New StateEvent detected: $stateEvent")
//        when(stateEvent){
//            is SelectNextChapterEvent -> {
//
//            }
//            is None ->{
//                return AbsentLiveData.create()
//            }
//        }
//    }
//
//    fun setBlogListData(blogPosts: List<BlogPost>){
//        val update = getCurrentViewStateOrNew()
//        update.blogPosts = blogPosts
//        _viewState.value = update
//    }
//
//    fun setUser(user: User){
//        val update = getCurrentViewStateOrNew()
//        update.user = user
//        _viewState.value = update
//    }
//
//    fun getCurrentViewStateOrNew(): MainMenuViewState {
//        val value = viewState.value?.let{
//            it
//        }?: MainMenuViewState()
//        return value
//    }
//
//    fun setStateEvent(event: MainMenuStateEvent){
//        val state: MainMenuStateEvent
//        state = event
//        _stateEvent.value = state
//    }
}
