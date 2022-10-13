package com.hugh.presentation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hugh.data.repository.ClipBoardRepository
import com.hugh.model.ClipBoardState
import com.hugh.presentation.action.ClipBoardHandler
import com.hugh.presentation.action.ClipBoardAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KeyboardViewModel @Inject constructor(
    private val clipBoardRepository: ClipBoardRepository
) : ViewModel(), ClipBoardHandler {

    private val _copyFlow = MutableStateFlow(ClipBoardState.EMPTY)
    val copyFlow = _copyFlow
        .asStateFlow()
        .filter { it.text.isNotEmpty() }

    override fun clipAction(action: ClipBoardAction) {
        when (action) {
            is ClipBoardAction.Copy -> {
                copyClipData(action.text)
            }
            is ClipBoardAction.Insert -> {
                insertClipData(action.state)
            }
            is ClipBoardAction.Delete -> {
                deleteClipData(action.id)
            }
        }
    }

    private fun copyClipData(clip: String) {
        viewModelScope.launch {
            _copyFlow.emit(ClipBoardState.EMPTY.copy(text = clip))
        }
    }

    private fun insertClipData(state: ClipBoardState) {
        viewModelScope.launch {
            clipBoardRepository.insertClipData(state)
        }
    }

    private fun deleteClipData(id: Long) {
        viewModelScope.launch {
            clipBoardRepository.deleteClipData(ClipBoardState.EMPTY.copy(id = id))
        }
    }
}