package com.preonboarding.customkeyboard.keyboard

import android.content.Context
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.util.TypedValue
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputConnection
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.children
import com.preonboarding.customkeyboard.R
import com.preonboarding.customkeyboard.databinding.LayoutKeyboardBinding

class Keyboard constructor(
    private val context: Context,
    private var layoutInflater: LayoutInflater
) {

    lateinit var binding: LayoutKeyboardBinding
    var isShift: Boolean = false
    var buttons: MutableList<TextView> = mutableListOf<TextView>()
    lateinit var koreanAutomata: KoreanAutomata
    var inputConnection: InputConnection? = null
    var downView: View? = null
    private val textsList = ArrayList<List<String>>()
    private val linearLayoutList = ArrayList<LinearLayout>()

    fun init() {
        binding = LayoutKeyboardBinding.inflate(layoutInflater)
        koreanAutomata = KoreanAutomata(inputConnection!!)

        val numpadText = listOf<String>("1", "2", "3", "4", "5", "6", "7", "8", "9", "0")
        val firstLineText = listOf<String>("ㅂ", "ㅈ", "ㄷ", "ㄱ", "ㅅ", "ㅛ", "ㅕ", "ㅑ", "ㅐ", "ㅔ")
        val secondLineText = listOf<String>("ㅁ", "ㄴ", "ㅇ", "ㄹ", "ㅎ", "ㅗ", "ㅓ", "ㅏ", "ㅣ")
        val thirdLineText = listOf<String>("Shift", "ㅋ", "ㅌ", "ㅊ", "ㅍ", "ㅠ", "ㅜ", "ㅡ", "Back")
        val fourthLineText = listOf<String>("1@#", "한/영", "?", "space", ".", "Enter")

        textsList.clear()
        textsList.add(numpadText)
        textsList.add(firstLineText)
        textsList.add(secondLineText)
        textsList.add(thirdLineText)
        textsList.add(fourthLineText)

        val numpadLine = binding.numpadLine
        val firstLine = binding.firstLine
        val secondLine = binding.secondLine
        val thirdLine = binding.thirdLine
        val fourthLine = binding.fourthLine

        linearLayoutList.clear()
        linearLayoutList.add(numpadLine)
        linearLayoutList.add(firstLine)
        linearLayoutList.add(secondLine)
        linearLayoutList.add(thirdLine)
        linearLayoutList.add(fourthLine)

        setLayoutComponents()
    }

    fun getLayout(): LinearLayout {
        koreanAutomata = KoreanAutomata(inputConnection!!)
        setLayoutComponents()
        return binding.root
    }

    private fun setLayoutComponents() {
        for (i in linearLayoutList.indices) {
            val keyButtons = linearLayoutList[i].children.toList()
            val keyTexts = textsList[i]
            for (j in keyButtons.indices) {
                val keyButton = keyButtons[j].findViewById<TextView>(R.id.textview_itemkeyboard)
                val keyText = keyTexts[j]
                var myOnClickListener: View.OnClickListener? = null
                when (keyText) {
                    "space" -> {
                        keyButton.text = keyText
                        myOnClickListener = getSpaceAction()
                        keyButton.setOnClickListener(myOnClickListener)
                        keyButton.setOnTouchListener(getOnTouchListener(myOnClickListener))
                    }
                    "Back" -> {
                        keyButton.text = keyText
                        myOnClickListener = getDeleteAction()
                        keyButton.setOnClickListener(myOnClickListener)
                        keyButton.setOnTouchListener(getOnTouchListener(myOnClickListener))
                    }
                    "Shift" -> {
                        keyButton.text = keyText
                        myOnClickListener = getShiftAction()
                        keyButton.setOnClickListener(myOnClickListener)
                    }
                    "Enter" -> {
                        keyButton.text = keyText
                        myOnClickListener = getEnterAction()
                        keyButton.setOnClickListener(myOnClickListener)
                        keyButton.setOnTouchListener(getOnTouchListener(myOnClickListener))
                    }
                    "한/영" -> {
                        keyButton.text = keyText
                        buttons.add(keyButton)
                    }
                    "1@#" -> {
                        keyButton.text = keyText
                        buttons.add(keyButton)
                    }
                    else -> {
                        keyButton.text = keyText
                        keyButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18F)
                        buttons.add(keyButton)
                        myOnClickListener = getMyClickListener(keyButton)
                        keyButton.setOnTouchListener(getOnTouchListener(myOnClickListener))
                    }
                }
                keyButtons[j].setOnClickListener(myOnClickListener)
            }
        }
    }

    private fun keyChange() {
        if (isShift) {
            isShift = false
            binding.actionKeyShift.textviewItemkeyboard.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.black
                )
            )
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
            isShift = true
            binding.actionKeyShift.textviewItemkeyboard.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.keyboardPressed
                )
            )
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

    private fun getMyClickListener(actionButton: TextView): View.OnClickListener {

        val clickListener = (View.OnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                inputConnection?.requestCursorUpdates(InputConnection.CURSOR_UPDATE_IMMEDIATE)
            }
            val cursorcs: CharSequence? =
                inputConnection?.getSelectedText(InputConnection.GET_TEXT_WITH_STYLES)
            if (cursorcs != null && cursorcs.length >= 2) {

                val eventTime = SystemClock.uptimeMillis()
                inputConnection?.finishComposingText()
                inputConnection?.sendKeyEvent(
                    KeyEvent(
                        eventTime, eventTime,
                        KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL, 0, 0, 0, 0,
                        KeyEvent.FLAG_SOFT_KEYBOARD
                    )
                )
                inputConnection?.sendKeyEvent(
                    KeyEvent(
                        SystemClock.uptimeMillis(), eventTime,
                        KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DEL, 0, 0, 0, 0,
                        KeyEvent.FLAG_SOFT_KEYBOARD
                    )
                )
                koreanAutomata.clear()
            }
            try {
                val myText = Integer.parseInt(actionButton.text.toString())
                koreanAutomata.directlyCommit()
                inputConnection?.commitText(actionButton.text.toString(), 1)
            } catch (e: NumberFormatException) {
                koreanAutomata.commit(actionButton.text.toString().toCharArray().get(0))
            }
            if (isShift) {
                keyChange()
            }
        })
        actionButton.setOnClickListener(clickListener)
        return clickListener
    }

    fun getOnTouchListener(clickListener: View.OnClickListener): View.OnTouchListener {
        val handler = Handler(Looper.getMainLooper())
        val initialInterval = 500
        val normalInterval = 100
        val handlerRunnable = object : Runnable {
            override fun run() {
                handler.postDelayed(this, normalInterval.toLong())
                clickListener.onClick(downView)
            }
        }
        val onTouchListener = object : View.OnTouchListener {
            override fun onTouch(view: View?, motionEvent: MotionEvent?): Boolean {
                when (motionEvent?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        handler.removeCallbacks(handlerRunnable)
                        handler.postDelayed(handlerRunnable, initialInterval.toLong())
                        downView = view!!
                        clickListener.onClick(view)
                        return true
                    }
                    MotionEvent.ACTION_UP -> {
                        handler.removeCallbacks(handlerRunnable)
                        downView = null
                        return true
                    }
                    MotionEvent.ACTION_CANCEL -> {
                        handler.removeCallbacks(handlerRunnable)
                        downView = null
                        return true
                    }
                }
                return false
            }
        }

        return onTouchListener
    }

    fun getSpaceAction(): View.OnClickListener {
        return View.OnClickListener {
            koreanAutomata.commitSpace()
        }
    }

    fun getDeleteAction(): View.OnClickListener {
        return View.OnClickListener {
            val cursors: CharSequence? =
                inputConnection?.getSelectedText(InputConnection.GET_TEXT_WITH_STYLES)
            if (cursors != null && cursors.length >= 2) {

                val eventTime = SystemClock.uptimeMillis()
                inputConnection?.finishComposingText()
                inputConnection?.sendKeyEvent(
                    KeyEvent(
                        eventTime, eventTime,
                        KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL, 0, 0, 0, 0,
                        KeyEvent.FLAG_SOFT_KEYBOARD
                    )
                )
                inputConnection?.sendKeyEvent(
                    KeyEvent(
                        SystemClock.uptimeMillis(), eventTime,
                        KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DEL, 0, 0, 0, 0,
                        KeyEvent.FLAG_SOFT_KEYBOARD
                    )
                )
                koreanAutomata.clear()
            } else {
                koreanAutomata.delete()
            }
        }
    }

    fun getShiftAction(): View.OnClickListener {
        return View.OnClickListener {
            keyChange()
        }
    }

    fun getEnterAction(): View.OnClickListener {
        return View.OnClickListener {
            koreanAutomata.directlyCommit()
            val eventTime = SystemClock.uptimeMillis()
            inputConnection?.sendKeyEvent(
                KeyEvent(
                    eventTime, eventTime,
                    KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER, 0, 0, 0, 0,
                    KeyEvent.FLAG_SOFT_KEYBOARD
                )
            )
            inputConnection?.sendKeyEvent(
                KeyEvent(
                    SystemClock.uptimeMillis(), eventTime,
                    KeyEvent.ACTION_UP, KeyEvent.KEYCODE_ENTER, 0, 0, 0, 0,
                    KeyEvent.FLAG_SOFT_KEYBOARD
                )
            )
        }
    }

}