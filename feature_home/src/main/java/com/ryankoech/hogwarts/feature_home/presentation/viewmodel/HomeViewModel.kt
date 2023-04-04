package com.ryankoech.hogwarts.feature_home.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryankoech.hogwarts.common.core.util.Resource
import com.ryankoech.hogwarts.common.presentation.utils.ScreenState
import com.ryankoech.hogwarts.feature_home.domain.usecase.GetCharactersUseCase
import com.ryankoech.hogwarts.feature_home.presentation.viewstate.HomeScreenViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _viewState = mutableStateOf(HomeScreenViewState())
    val viewState : State<HomeScreenViewState> = _viewState

    init {
        getCharacters()
    }

    private fun getCharacters() {
        getCharactersUseCase()
            .onEach { res ->
                when(res) {
                    is Resource.Error -> {
                        _viewState.value = _viewState.value.copy(
                            screenState = ScreenState.ERROR
                        )
                    }
                    is Resource.Loading -> {
                        _viewState.value = _viewState.value.copy(
                            screenState = ScreenState.LOADING
                        )
                    }
                    is Resource.Success -> {
                        _viewState.value = _viewState.value.copy(
                            screenState = ScreenState.SUCCESS,
                            characters = res.data!!
                        )
                    }
                }
            }.launchIn(viewModelScope)
    }

}