package com.preonboarding.customkeyboard.keyboard

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.SystemClock
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputConnection
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.children
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.preonboarding.customkeyboard.KeyboardActionListener
import com.preonboarding.customkeyboard.Mode
import com.preonboarding.customkeyboard.R

class KoreanKeyBoard(
    private val context: Context,
    layoutInflater: LayoutInflater,
    listener: KeyboardActionListener
) : Keyboard(context, layoutInflater, listener) {

    private var isShiftOn: Boolean = false
    private val keysText = ArrayList<List<String>>()
    private val lineList = ArrayList<LinearLayout>()
    private val buttons = ArrayList<Button>()
    private lateinit var combiner: KoreaCombiner

    fun getLayout(): LinearLayout {
        combiner = KoreaCombiner(inputConnection, context)
        setLayoutComponents()
        return binding.layoutKeyboard
    }

    override fun init() {
        combiner = KoreaCombiner(inputConnection, context)
        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setLayoutParamsLandScape(binding.lineNumber)
            setLayoutParamsLandScape(binding.lineFirst)
            setLayoutParamsLandScape(binding.lineSecond)
            setLayoutParamsLandScape(binding.lineThird)
            setLayoutParamsLandScape(binding.lineLast)
        } else {
            setLayoutParams(binding.lineNumber)
            setLayoutParams(binding.lineFirst)
            setLayoutParams(binding.lineSecond)
            setLayoutParams(binding.lineThird)
            setLayoutParams(binding.lineLast)
        }

        keysText.apply {
            clear()
            add(context.resources.getStringArray(R.array.number).toList())
            add(context.resources.getStringArray(R.array.first_line_korea).toList())
            add(context.resources.getStringArray(R.array.second_line_korea).toList())
            add(context.resources.getStringArray(R.array.third_line_korea).toList())
            add(context.resources.getStringArray(R.array.last_line).toList())
        }
        lineList.apply {
            clear()
            add(binding.lineNumber)
            add(binding.lineFirst)
            add(binding.lineSecond)
            add(binding.lineThird)
            add(binding.lineLast)
        }

    }

    override fun changeMode() {
        if (isShiftOn) {
            isShiftOn = false
            binding.btnKeyShift.ivSpecialKey.setImageResource(R.drawable.ic_shift_off)
            for (button in buttons) {
                when (button.text.toString()) {
                    "ㅃ" -> {
                        button.text = "ㅂ"
                    }
                    "ㅉ" -> {
                        button.text = "ㅈ"
                    }
                    "ㄸ" -> {
                        button.text = "ㄷ"
                    }
                    "ㄲ" -> {
                        button.text = "ㄱ"
                    }
                    "ㅆ" -> {
                        button.text = "ㅅ"
                    }
                    "ㅒ" -> {
                        button.text = "ㅐ"
                    }
                    "ㅖ" -> {
                        button.text = "ㅔ"
                    }
                }
            }
        } else {
            isShiftOn = true
            binding.btnKeyShift.ivSpecialKey.setImageResource(R.drawable.ic_shift_on)
            for (button in buttons) {
                when (button.text.toString()) {
                    "ㅂ" -> {
                        button.text = "ㅃ"
                    }
                    "ㅈ" -> {
                        button.text = "ㅉ"
                    }
                    "ㄷ" -> {
                        button.text = "ㄸ"
                    }
                    "ㄱ" -> {
                        button.text = "ㄲ"
                    }
                    "ㅅ" -> {
                        button.text = "ㅆ"
                    }
                    "ㅐ" -> {
                        button.text = "ㅒ"
                    }
                    "ㅔ" -> {
                        button.text = "ㅖ"
                    }
                }
            }
        }
    }

    override fun setLayoutComponents() {
        for (i in lineList.indices) {
            val childrenButton = lineList[i].children.toList()
            val key = keysText[i]
            for (item in childrenButton.indices) {
                val button = childrenButton[item].findViewById<Button>(R.id.btn_key)
                val specialKey = childrenButton[item].findViewById<ImageView>(R.id.iv_special_key)
                var onClickListener: View.OnClickListener? = null
                when (key[item]) {
                    "Shift" -> {
                        setSpecialKey(specialKey, R.drawable.ic_shift_off, doShiftClick())
                        button.isEnabled = true
                        onClickListener = doShiftClick()
                    }
                    "DEL" -> {
                        setSpecialKey(specialKey, R.drawable.ic_backspace, doDeleteClick())
                        button.isEnabled = true
                        onClickListener = doDeleteClick()
                    }
                    "Space" -> {
                        setSpecialKey(specialKey, R.drawable.ic_space_bar, doSpaceClick())
                        button.isEnabled = true
                        onClickListener = doSpaceClick()
                    }
                    "Enter" -> {
                        setSpecialKey(specialKey, R.drawable.ic_enter, doEnterClick())
                        button.isEnabled = true
                        onClickListener = doEnterClick()
                    }
                    "Shortcut" -> {
                        //TODO 선택된 단축기로 텍스트 표기
                        button.setOnClickListener(doShortcutClick())
                        onClickListener = doShortcutClick()
                    }
                    "!#1" -> {
                        button.apply {
                            text = key[item]
                            setOnClickListener {
                                keyboardActionListener.changeMode(Mode.SYMBOL)
                            }
                            buttons.add(this)
                        }
                        onClickListener = object : View.OnClickListener {
                            override fun onClick(p0: View?) {
                                keyboardActionListener.changeMode(Mode.SYMBOL)
                            }
                        }

                    }
                    "한/영" -> {
                        button.apply {
                            text = key[item]
                            setOnClickListener {
                                keyboardActionListener.changeMode(Mode.ENGLISH)
                            }
                            buttons.add(this)
                        }
                        onClickListener = object : View.OnClickListener {
                            override fun onClick(p0: View?) {
                                keyboardActionListener.changeMode(Mode.ENGLISH)
                            }
                        }
                    }
                    else -> {
                        button.apply {
                            text = key[item]
                            buttons.add(this)
                            setOnClickListener(getOnClickListener(this))
                            onClickListener = getOnClickListener(this)
                        }
                    }
                }
                childrenButton[item].setOnClickListener(onClickListener)
            }
        }
    }

    private fun doShortcutClick(): View.OnClickListener {
        return View.OnClickListener {
            //TODO 단축기 세팅
        }
    }

    override fun doSpaceClick(): View.OnClickListener {
        return View.OnClickListener {
            combiner.commitSpace()
        }
    }

    override fun doEnterClick(): View.OnClickListener {
        return View.OnClickListener {
            combiner.directlyCommit()
            sendKeyEvent()
        }
    }

    override fun doShiftClick(): View.OnClickListener {
        return View.OnClickListener {
            changeMode()
        }
    }

    override fun doDeleteClick(): View.OnClickListener {
        return View.OnClickListener {
            val cursor = inputConnection?.getSelectedText(InputConnection.GET_TEXT_WITH_STYLES)
            cursor?.let {
                if (cursor.length >= 2) {
                    sendKeyEvent()
                    combiner.clear()
                } else {
                    combiner.delete()
                }
            }
        }
    }

    private fun getOnClickListener(button: Button): View.OnClickListener {
        return View.OnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                inputConnection?.requestCursorUpdates(InputConnection.CURSOR_UPDATE_IMMEDIATE)
            }
            val cursor = inputConnection?.getSelectedText(InputConnection.GET_TEXT_WITH_STYLES)
            cursor?.let {
                if (cursor.length >= 2) {
                    sendKeyEvent()
                    combiner.clear()
                }
            }
            runCatching {
                val tmpText = Integer.parseInt(button.text.toString())
                combiner.directlyCommit()
                inputConnection?.commitText(button.text.toString(), 1)
            }.onFailure {
                combiner.commit(button.text.toString().toCharArray().get(0))
            }
            if (isShiftOn) {
                changeMode()
            }
        }
    }

    private fun sendKeyEvent() {
        val eventTime = SystemClock.uptimeMillis()
        inputConnection?.apply {
            finishComposingText()
            sendKeyEvent(
                KeyEvent(
                    eventTime, eventTime,
                    KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL, 0, 0, 0, 0,
                    KeyEvent.FLAG_SOFT_KEYBOARD
                )
            )
            sendKeyEvent(
                KeyEvent(
                    SystemClock.uptimeMillis(), eventTime,
                    KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DEL, 0, 0, 0, 0,
                    KeyEvent.FLAG_SOFT_KEYBOARD
                )
            )
        }
    }

/*    private */

    private fun setSpecialKey(imageView: ImageView, drawable: Int, listener: View.OnClickListener) {
        imageView.apply {
            setImageResource(drawable)
            isVisible = true
            setOnClickListener(listener)
        }
    }
}