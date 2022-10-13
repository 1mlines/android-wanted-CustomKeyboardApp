package com.hugh.presentation.ui.keyboard

import android.inputmethodservice.InputMethodService
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.hugh.presentation.R

class KeyBoardService : InputMethodService() {

    lateinit var keyboardView: LinearLayout
    lateinit var keyboardFrame: FrameLayout
    lateinit var keyboardKorean: KeyboardKorean
    lateinit var keyboardClipboard: KeyboardClipboard
    //lateinit var keyboardEnglish:KeyboardEnglish


    val keyboardInterationListener = object : KeyboardInterationListener {
        override fun modechange(mode: Int) {
            currentInputConnection.finishComposingText()
            when(mode) {
                /*0 -> {
                    keyboardFrame.removeAllViews()
                    keyboardEnglish.inputConnection = currentInputConnection
                    keyboardFrame.addView(keyboardEnglish.getLayout())

                }*/
                1 -> {
                    keyboardFrame.removeAllViews()
                    keyboardKorean.inputConnection = currentInputConnection
                    keyboardFrame.addView(keyboardKorean.getLayout())
                }
                2 -> {
                    keyboardFrame.removeAllViews()
                    keyboardClipboard.inputConnection = currentInputConnection
                    keyboardFrame.addView(keyboardClipboard.getLayout())
                }
            }
        }

    }

    override fun onCreate() {
        super.onCreate()
        keyboardView = layoutInflater.inflate(R.layout.keyboard_view, null) as LinearLayout
        keyboardFrame = keyboardView.findViewById(R.id.keyboard_frame)
    }

    override fun onCreateInputView(): View {
        keyboardKorean = KeyboardKorean(applicationContext, layoutInflater, keyboardInterationListener)
        //keyboardEnglish = KeyboardEnglish(applicationContext, layoutInflater, keyboardInterationListener)
        keyboardClipboard = KeyboardClipboard(applicationContext, layoutInflater, keyboardInterationListener)

        keyboardKorean.inputConnection = currentInputConnection
        keyboardKorean.init()
        //keyboardEnglish.inputConnection = currentInputConnection
        //keyboardEnglish.init()

        keyboardClipboard.inputConnection = currentInputConnection
        keyboardClipboard.init()

        return keyboardView
    }

    override fun updateInputViewShown() {
        super.updateInputViewShown()
        currentInputConnection.finishComposingText()
        keyboardFrame.removeAllViews()
        keyboardInterationListener.modechange(1)
        /*if(currentInputEditorInfo.inputType == EditorInfo.TYPE_CLASS_NUMBER){
            keyboardFrame.removeAllViews()
            //keyboardFrame.addView(KeyboardNumpad.newInstance(applicationContext, layoutInflater, currentInputConnection, keyboardInterationListener))
        }
        else{
            keyboardInterationListener.modechange(1)
        }*/
    }
}