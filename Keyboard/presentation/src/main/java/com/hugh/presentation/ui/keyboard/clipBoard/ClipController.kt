package com.hugh.presentation.ui.keyboard.clipBoard

import android.view.inputmethod.InputConnection
import com.hugh.data.repository.ClipBoardRepository
import com.hugh.model.ClipBoardData
import com.hugh.presentation.action.clipAction.ClipBoardAction
import com.hugh.presentation.action.clipAction.ClipBoardHandler
import com.hugh.presentation.action.clipAction.ClipState
import com.hugh.presentation.ui.keyboard.HangulUtil
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/**
 * Created by 서강휘
 */


@OptIn(FlowPreview::class)
class ClipController(
    private val clipBoardRepository: ClipBoardRepository,
    private val inputConnection: InputConnection?,
    private val hangulUtil: HangulUtil,
    private val keyboardScope: CoroutineScope
) : ClipBoardHandler {

    val clipFlow = clipBoardRepository.getClipsFlow()

    private val insertFlow = MutableSharedFlow<ClipState.Clip>()

    init {
        keyboardScope.launch {
            insertFlow
                .debounce(300)
                .filterIsInstance<ClipState.Clip>()
                .filter { clip -> clip.text.isNotEmpty() }
                .collect { state ->
                    clipBoardRepository.insertClipData(
                        ClipBoardData.EMPTY.copy(
                            text = state.text
                        )
                    )
                }
        }
    }

    override fun clipAction(action: ClipBoardAction) {
        when (action) {
            is ClipBoardAction.Copy -> {
                keyboardScope.launch {
                    insertFlow.emit(
                        ClipState.Clip(
                            text = action.text
                        )
                    )
                }
            }
            is ClipBoardAction.Paste -> {
                inputConnection?.let { input ->
                    hangulUtil.updateLetter(
                        input,
                        action.text
                    )
                }
            }
            is ClipBoardAction.Delete -> {
                keyboardScope.launch {
                    clipBoardRepository.deleteClipData(action.id)
                }
            }
        }
    }
}