package com.hugh.presentation.ui.keyboard.clipBoard

import android.view.inputmethod.InputConnection
import com.hugh.data.repository.ClipBoardRepository
import com.hugh.model.ClipBoardData
import com.hugh.presentation.action.clipAction.ClipBoardAction
import com.hugh.presentation.action.clipAction.ClipBoardHandler
import com.hugh.presentation.ui.keyboard.HangulUtil
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow

/**
 * Created by 서강휘
 */


class ClipController(
    private val clipBoardRepository: ClipBoardRepository,
    private val inputConnection: InputConnection?,
    private val hangulUtil: HangulUtil,
    private val keyboardScope: CoroutineScope
) : ClipBoardHandler {

    val clipFlow = clipBoardRepository.getClipsFlow()

    override fun clipAction(action: ClipBoardAction) {
        when (action) {
            is ClipBoardAction.Copy -> {
                keyboardScope.launch {
                    clipBoardRepository.insertClipData(
                        ClipBoardData.EMPTY.copy(
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