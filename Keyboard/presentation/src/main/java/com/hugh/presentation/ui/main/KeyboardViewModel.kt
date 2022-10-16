package com.hugh.presentation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hugh.data.repository.ClipBoardRepository
import com.hugh.model.ClipBoardData
import com.hugh.presentation.action.clipAction.ClipBoardHandler
import com.hugh.presentation.action.clipAction.ClipBoardAction
import com.hugh.presentation.action.clipAction.ClipState
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

    private val _copyFlow = MutableStateFlow(
        ClipState.Clip(text = "")
    )
    val copyFlow = _copyFlow
        .asStateFlow()
        .filter { it.text.isNotEmpty() }

    override fun clipAction(action: ClipBoardAction) {
        when (action) {
            is ClipBoardAction.Copy -> {
                copyClipData(action.text)
            }
            is ClipBoardAction.Delete -> {
                deleteClipData(action.id)
            }
            is ClipBoardAction.Paste -> TODO()
        }
    }

    private fun copyClipData(clip: String) {
        viewModelScope.launch {
            _copyFlow.emit(ClipState.Clip(text = clip))
        }
    }

    private fun deleteClipData(id: Long) {
        viewModelScope.launch {
            clipBoardRepository.deleteClipData(id)
        }
    }

    fun insertClipData(state: ClipState.Clip) {
        viewModelScope.launch {
            clipBoardRepository.insertClipData(
                ClipBoardData.EMPTY.copy(text = state.text)
            )
        }
    }
}