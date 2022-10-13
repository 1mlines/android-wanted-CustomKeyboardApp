package com.hugh.presentation.ui.keyboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputConnection
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.children
import com.hugh.presentation.R

class KeyboardClipboard constructor(
    var context: Context,
    var layoutInflater: LayoutInflater,
    var keyboardInterationListener: KeyboardInterationListener
){
    lateinit var clipLayout: LinearLayout
    var buttons:MutableList<Button> = mutableListOf<Button>()

    var inputConnection: InputConnection? = null
        set(inputConnection){
            field = inputConnection
        }

    val menupadText = listOf<String>("Home", "Clip")
    val myKeysText = ArrayList<List<String>>()
    val layoutLines = ArrayList<LinearLayout>()

    fun init() {
        clipLayout = layoutInflater.inflate(R.layout.keyboard_clipboard, null) as LinearLayout

        val menupadLine = clipLayout.findViewById<LinearLayout>(
            R.id.menupad_line
        )

        myKeysText.clear()
        myKeysText.add(menupadText)

        layoutLines.clear()
        layoutLines.add(menupadLine)

        setLayoutComponents()

    }

    fun getLayout():LinearLayout{
        setLayoutComponents()
        return clipLayout
    }

    private fun setLayoutComponents(){
        for(line in layoutLines.indices){
            val children = layoutLines[line].children.toList()
            val myText = myKeysText[line]
            var longClickIndex = 0
            for(item in children.indices){
                val actionButton = children[item].findViewById<Button>(R.id.key_button)
                val spacialKey = children[item].findViewById<ImageView>(R.id.spacial_key)
                var myOnClickListener: View.OnClickListener? = null
                when(myText[item]){
                    "Home" -> {
                        actionButton.text = myText[item]
                        buttons.add(actionButton)
                        myOnClickListener = object : View.OnClickListener{
                            override fun onClick(p0: View?) {
                                keyboardInterationListener.modechange(1)
                            }
                        }
                        actionButton.setOnClickListener(myOnClickListener)
                    }


                }
                children[item].setOnClickListener(myOnClickListener)
            }
        }
    }
}