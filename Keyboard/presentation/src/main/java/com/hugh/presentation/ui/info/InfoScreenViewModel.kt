package com.hugh.presentation.ui.info

import androidx.lifecycle.ViewModel
import com.hugh.presentation.action.compose.InfoNavTarget
import com.hugh.presentation.action.compose.info.InfoAction
import com.hugh.presentation.action.compose.info.InfoActionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

/**
 * @Created by 김현국 2022/10/14
 */
@HiltViewModel
class InfoScreenViewModel @Inject constructor() : ViewModel(), InfoActionHandler {

    private val _navTarget = MutableSharedFlow<InfoNavTarget>(extraBufferCapacity = 1)
    val navTarget = _navTarget.asSharedFlow()

    override fun navigateAction(action: InfoAction) {
        when (action) {
            is InfoAction.NavigateKeyBoard -> {
                _navTarget.tryEmit(InfoNavTarget.KeyBoardTestScreen)
            }
        }
    }
}
