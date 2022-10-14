package com.hugh.presentation.action.keyboard

sealed class KeyBoardAction() {

    object Space: KeyBoardAction()
    object Delete: KeyBoardAction()
    object CAPS: KeyBoardAction()

}