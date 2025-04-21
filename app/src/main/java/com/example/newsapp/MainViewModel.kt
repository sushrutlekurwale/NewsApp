package com.example.newsapp

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.usecases.AppEntryUsecases
import com.example.newsapp.presentation.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUsecases: AppEntryUsecases
): ViewModel(){

    var splashCondition by mutableStateOf(true)
        private set

    var startDestination = mutableStateOf(Route.AppStartNavigation.route)
        private set

    init {
        appEntryUsecases.readAppEntry().onEach { shouldStartFromHomeScreen ->
            if(shouldStartFromHomeScreen){
                startDestination.value = Route.NewsNavigation.route
            }else{
                startDestination.value = Route.AppStartNavigation.route
            }
            delay(300)
            splashCondition = false
            }.launchIn(viewModelScope)
    }

}