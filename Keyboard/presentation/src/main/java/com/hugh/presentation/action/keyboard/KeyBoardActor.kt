package com.hugh.presentation.action.keyboard


class KeyBoardActor(private val keyBoardHandler: KeyBoardHandler) {

    fun spaceText() {
        keyBoardHandler.keyboardAction(KeyBoardAction.Space)
    }

    fun deleteText() {
        keyBoardHandler.keyboardAction(KeyBoardAction.Delete)
    }

    fun capsText() {
        keyBoardHandler.keyboardAction(KeyBoardAction.CAPS)
    }
}