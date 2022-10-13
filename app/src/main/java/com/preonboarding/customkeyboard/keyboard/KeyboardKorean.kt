package com.preonboarding.customkeyboard.keyboard

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Build
import android.os.Handler
import android.os.SystemClock
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputConnection
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.view.children
import com.myhome.rpgkeyboard.KeyboardInterationListener
import com.preonboarding.customkeyboard.R

class KeyboardKorean constructor(
    var context: Context,
    var layoutInflater: LayoutInflater,
    var keyboardInterationListener: KeyboardInterationListener
) {

    lateinit var koreanLayout: LinearLayout
    var isShift: Boolean = false
    var buttons: MutableList<Button> = mutableListOf<Button>()
    lateinit var hangulMaker: HangulMaker
    lateinit var sharedPreferences: SharedPreferences
    var inputConnection: InputConnection? = null

    val numpadText = listOf<String>("1", "2", "3", "4", "5", "6", "7", "8", "9", "0")
    val firstLineText = listOf<String>("ㅂ", "ㅈ", "ㄷ", "ㄱ", "ㅅ", "ㅛ", "ㅕ", "ㅑ", "ㅐ", "ㅔ")
    val secondLineText = listOf<String>("ㅁ", "ㄴ", "ㅇ", "ㄹ", "ㅎ", "ㅗ", "ㅓ", "ㅏ", "ㅣ")
    val thirdLineText = listOf<String>("Shift", "ㅋ", "ㅌ", "ㅊ", "ㅍ", "ㅠ", "ㅜ", "ㅡ", "Back")
    val fourthLineText = listOf<String>("1@#", "한/영", "?", "space", ".", "Enter")
    val myKeysText = ArrayList<List<String>>()
    val layoutLines = ArrayList<LinearLayout>()
    var downView: View? = null

    fun init() {
        koreanLayout = layoutInflater.inflate(R.layout.layout_keyboard, null) as LinearLayout
        hangulMaker = HangulMaker(inputConnection!!)

        sharedPreferences = context.getSharedPreferences("setting", Context.MODE_PRIVATE)

        val height = sharedPreferences.getInt("keyboardHeight", 150)
        val config = context.resources.configuration

        val numpadLine = koreanLayout.findViewById<LinearLayout>(
            R.id.numpad_line
        )
        val firstLine = koreanLayout.findViewById<LinearLayout>(
            R.id.first_line
        )
        val secondLine = koreanLayout.findViewById<LinearLayout>(
            R.id.second_line
        )
        val thirdLine = koreanLayout.findViewById<LinearLayout>(
            R.id.third_line
        )
        val fourthLine = koreanLayout.findViewById<LinearLayout>(
            R.id.fourth_line
        )

        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            firstLine.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                (height * 0.7).toInt()
            )
            secondLine.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                (height * 0.7).toInt()
            )
            thirdLine.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                (height * 0.7).toInt()
            )
        } else {
            firstLine.layoutParams =
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height)
            secondLine.layoutParams =
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height)
            thirdLine.layoutParams =
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height)
        }

        myKeysText.clear()
        myKeysText.add(numpadText)
        myKeysText.add(firstLineText)
        myKeysText.add(secondLineText)
        myKeysText.add(thirdLineText)
        myKeysText.add(fourthLineText)

        layoutLines.clear()
        layoutLines.add(numpadLine)
        layoutLines.add(firstLine)
        layoutLines.add(secondLine)
        layoutLines.add(thirdLine)
        layoutLines.add(fourthLine)
        setLayoutComponents()

    }

    fun getLayout(): LinearLayout {
        hangulMaker = HangulMaker(inputConnection!!)
        setLayoutComponents()
        return koreanLayout
    }


    fun modeChange() {
        if (isShift) {
            isShift = false
            for (button in buttons) {
                when (button.text.toString()) {
                    "Shift" -> {
                        button.setTextColor(R.color.black)
                    }
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
            for (button in buttons) {
                when (button.text.toString()) {
                    "Shift" -> {
                        button.setTextColor(R.color.purple_500)
                    }
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

    private fun getMyClickListener(actionButton: Button): View.OnClickListener {

        val clickListener = (View.OnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                inputConnection?.requestCursorUpdates(InputConnection.CURSOR_UPDATE_IMMEDIATE)
            }
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
                hangulMaker.clear()
            }
            when (actionButton.text.toString()) {

                else -> {
                    try {
                        hangulMaker.directlyCommit()
                        inputConnection?.commitText(actionButton.text.toString(), 1)
                    } catch (e: NumberFormatException) {
                        hangulMaker.commit(actionButton.text.toString().toCharArray()[0])
                    }
                    if (isShift) {
                        modeChange()
                    }
                }
            }
        })
        actionButton.setOnClickListener(clickListener)
        return clickListener
    }

    fun getOnTouchListener(clickListener: View.OnClickListener): View.OnTouchListener {
        val handler = Handler()
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

    private fun setLayoutComponents() {
        for (line in layoutLines.indices) {
            val children = layoutLines[line].children.toList()
            val myText = myKeysText[line]
            for (item in children.indices) {
                val actionButton = children[item].findViewById<Button>(R.id.key_button)
                var myOnClickListener: View.OnClickListener? = null
                when (myText[item]) {
                    "space" -> {
                        actionButton.text = myText[item]
                        myOnClickListener = getSpaceAction()
                        actionButton.setOnClickListener(myOnClickListener)
                        actionButton.setOnTouchListener(getOnTouchListener(myOnClickListener))
                        actionButton.setBackgroundResource(R.drawable.key_background)
                    }
                    "Back" -> {
                        actionButton.text = myText[item]
                        myOnClickListener = getDeleteAction()
                        actionButton.setOnClickListener(myOnClickListener)
                        actionButton.setOnTouchListener(getOnTouchListener(myOnClickListener))
                    }
                    "Shift" -> {
                        actionButton.text = myText[item]
                        myOnClickListener = getCapsAction()
                        actionButton.setOnClickListener(myOnClickListener)
                        actionButton.setBackgroundResource(R.drawable.key_background)
                    }
                    "Enter" -> {
                        actionButton.text = myText[item]
                        myOnClickListener = getEnterAction()
                        actionButton.setOnClickListener(myOnClickListener)
                        actionButton.setOnTouchListener(getOnTouchListener(myOnClickListener))
                        actionButton.setBackgroundResource(R.drawable.key_background)
                    }
                    "한/영" -> {
                        actionButton.text = myText[item]
                        buttons.add(actionButton)
                    }
                    "!@#" -> {
                        actionButton.text = myText[item]
                        buttons.add(actionButton)
                    }
                    else -> {
                        actionButton.text = myText[item]
                        buttons.add(actionButton)
                        myOnClickListener = getMyClickListener(actionButton)
                        actionButton.setOnTouchListener(getOnTouchListener(myOnClickListener))
                    }
                }
                children[item].setOnClickListener(myOnClickListener)
            }
        }
    }

    fun getSpaceAction(): View.OnClickListener {
        return View.OnClickListener {
            hangulMaker.commitSpace()
        }
    }

    fun getDeleteAction(): View.OnClickListener {
        return View.OnClickListener {
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
                hangulMaker.clear()
            } else {
                hangulMaker.delete()
            }
        }
    }

    fun getCapsAction(): View.OnClickListener {
        return View.OnClickListener {
            modeChange()
        }
    }

    fun getEnterAction(): View.OnClickListener {
        return View.OnClickListener {
            hangulMaker.directlyCommit()
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