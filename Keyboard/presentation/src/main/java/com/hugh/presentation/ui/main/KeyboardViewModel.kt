package com.hugh.presentation.ui.main

import androidx.lifecycle.ViewModel
import com.hugh.data.repository.KeyboardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class KeyboardViewModel @Inject constructor(
    private val keyboardRepository: KeyboardRepository
): ViewModel() {

}