package com.hugh.presentation.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hugh.data.repository.ClipBoardRepository
import com.hugh.model.ClipBoardData
import com.hugh.presentation.action.ClipBoardHandler
import com.hugh.presentation.action.ClipBoardState
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

    private val _copyFlow = MutableStateFlow(ClipBoardState.Copy(""))
    val copyFlow = _copyFlow
        .asStateFlow()
        .filter { it.text.isNotEmpty() }

    override fun clipAction(state: ClipBoardState) {
        when (state) {
            is ClipBoardState.Copy -> {
                copyClipData(state.text)
            }
            is ClipBoardState.Insert -> {
                insertClipData(state.text)
            }
            is ClipBoardState.Delete -> {
                deleteClipData(state.id)
            }
        }
    }

    private fun copyClipData(clip: String) {
        viewModelScope.launch {
            _copyFlow.emit(ClipBoardState.Copy(text = clip))
        }
    }

    private fun insertClipData(data: String) {
        viewModelScope.launch {
            clipBoardRepository.insertClipData(ClipBoardData.EMPTY.copy(text = data))
        }
    }

    private fun deleteClipData(id: Long) {
        viewModelScope.launch {
            clipBoardRepository.deleteClipData(id)
        }
    }
}