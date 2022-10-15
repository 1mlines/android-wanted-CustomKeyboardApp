package com.hugh.presentation.ui.test

import androidx.lifecycle.ViewModel
import com.hugh.presentation.action.compose.TestNavTarget
import com.hugh.presentation.action.compose.test.TestAction
import com.hugh.presentation.action.compose.test.TestActionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

/**
 * @Created by 김현국 2022/10/14
 */
@HiltViewModel
class TestScreenViewModel @Inject constructor() : ViewModel(), TestActionHandler {

    private val _navTarget = MutableSharedFlow<TestNavTarget>(extraBufferCapacity = 1)
    val navTarget = _navTarget.asSharedFlow()
    override fun navigateAction(action: TestAction) {
        when (action) {
            is TestAction.GoBack -> {
                _navTarget.tryEmit(TestNavTarget.GoBack)
            }
            is TestAction.NavigationClipBoard -> {
                _navTarget.tryEmit(TestNavTarget.ClipBoardTestScreen)
            }
        }
    }
}
