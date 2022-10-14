package com.preonboarding.customkeyboard.presentation.clipboard

import android.content.Context
import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputConnection
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.view.children
import com.preonboarding.customkeyboard.R
import com.preonboarding.customkeyboard.databinding.ViewKeyboardClipboardBinding
import com.preonboarding.customkeyboard.presentation.KeyboardActionListener
import com.preonboarding.customkeyboard.presentation.Mode

class KeyboardClipboard(
    private val context: Context,
    private val layoutInflater: LayoutInflater,
    private val keyboardListener: KeyboardActionListener,
    private val clipboardListener: ClipboardActionListener
) {

    private val height = 150
    private val config = context.resources.configuration

    private val binding = ViewKeyboardClipboardBinding.inflate(layoutInflater)
    private val actionButtons = mutableListOf<Button>()
    private val keysText = ArrayList<List<String>>()
    private val lineList = ArrayList<LinearLayout>()

    private val clipboardAdapter: ClipboardAdapter by lazy {
        ClipboardAdapter {
            clipboardListener.deleteClipData(it)
        }
    }

    var inputConnection: InputConnection? = null
        set(inputConnection) {
            field = inputConnection
        }


    fun init() {
        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setLayoutParamsLandScape(binding.lineKeyboardToolbar.llToolbar)
        } else {
            setLayoutParams(binding.lineKeyboardToolbar.llToolbar)
        }

        keysText.apply {
            clear()
            add(context.resources.getStringArray(R.array.toolbar).toList())
        }

        lineList.apply {
            clear()
            add(binding.lineKeyboardToolbar.llToolbar)
        }

        binding.rvClipboard.adapter = clipboardAdapter
    }

    fun getLayout(): LinearLayout {
        setLayoutComponents()
        return binding.layoutClipboard
    }

    private fun setLayoutParams(layout: LinearLayout) {
        layout.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            height
        )
    }

    private fun setLayoutParamsLandScape(layout: LinearLayout) {
        layout.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            (height * 0.7).toInt()
        )
    }

    private fun setLayoutComponents() {

        for (i in lineList.indices) {
            val children = lineList[i].children.toList()
            val toolbarText = keysText[i]
            for (item in children.indices) {
                val button = children[item].findViewById<Button>(R.id.btn_key)

                var keyboardActionListener: View.OnClickListener? = null
                when (toolbarText[item]) {
                    "키보드" -> {
                        button.text = toolbarText[item]
                        actionButtons.add(button)

                        keyboardActionListener = View.OnClickListener {
                            keyboardListener.changeMode(Mode.KOREA)
                        }

                        button.setOnClickListener(keyboardActionListener)
                    }

                    "클립보드" -> {
                        button.text = toolbarText[item]
                        actionButtons.add(button)

                        keyboardActionListener = View.OnClickListener {
                            keyboardListener.changeMode(Mode.CLIPBOARD)
                        }

                        button.setOnClickListener(keyboardActionListener)
                    }
                }
                children[item].setOnClickListener(keyboardActionListener)
            }
        }
    }
}