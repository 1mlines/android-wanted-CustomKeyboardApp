package com.preonboarding.customkeyboard.presentation.keyboard

import android.view.inputmethod.InputConnection
import com.preonboarding.customkeyboard.presentation.keyboard.extension.isDoubleConsonantEnable
import com.preonboarding.customkeyboard.presentation.keyboard.extension.isDoubleVowelEnable
import javax.inject.Inject

class KoreaLanguageMaker @Inject constructor(
    private val inputConnection: InputConnection
){
//    fun commit(c: Char){
//        directlyCommit()
//    }

//    fun directlyCommit(){
//        inputConnection.commitText(toString(), 1)//입력부분
//    }

    private var choSung: Char = MIN_VALUE
    private var jungSung: Char = MIN_VALUE
    private var jongSung: Char = MIN_VALUE

    private var firstConsonant: Char = MIN_VALUE // 이중 자음 중 먼저 들어온 자음
    private var lastConsonant: Char = MIN_VALUE // 이중 자음 중 마지막으로 들어온 자음
    var firstVowel: Char = MIN_VALUE // 이중 모음 중 먼저 들어온 모음

    private val choSungList: List<Int> = listOf(0x3131, 0x3132, 0x3134, 0x3137, 0x3138, 0x3139, 0x3141,0x3142, 0x3143, 0x3145, 0x3146, 0x3147, 0x3148, 0x3149, 0x314a, 0x314b, 0x314c, 0x314d, 0x314e)
    private val jungSungList:List<Int> = listOf(0x314f, 0x3150, 0x3151, 0x3152, 0x3153, 0x3154, 0x3155, 0x3156, 0x3157, 0x3158, 0x3159, 0x315a, 0x315b, 0x315c, 0x315d, 0x315e, 0x315f, 0x3160, 0x3161, 0x3162, 0x3163)
    private val jongSungList:List<Int> = listOf(0x0000, 0x3131, 0x3132, 0x3133, 0x3134, 0x3135, 0x3136, 0x3137, 0x3139, 0x313a, 0x313b, 0x313c, 0x313d, 0x313e, 0x313f, 0x3140, 0x3141, 0x3142, 0x3144, 0x3145, 0x3146, 0x3147, 0x3148, 0x314a, 0x314b, 0x314c, 0x314d, 0x314e)

    private var inputState = InputState.NULL

    fun clear() {
        choSung = MIN_VALUE
        jungSung = MIN_VALUE
        jongSung = MIN_VALUE
        firstConsonant = MIN_VALUE
        lastConsonant = MIN_VALUE
        firstVowel = MIN_VALUE
    }

    fun makeHangul(): Char { // 한 글자 완성
        if (inputState == InputState.NULL) {
            return MIN_VALUE
        }
        if (inputState == InputState.CHO) {
            return choSung
        }
        val choIndex = choSungList.indexOf(choSung.code)
        val junIndex = jungSungList.indexOf(jungSung.code)
        val jonIndex = jongSungList.indexOf(jongSung.code)

        val makeResult = 0xAC00 + 28 * ((21 * choIndex) + junIndex) + jonIndex
        return makeResult.toChar()
    }

    fun commitHangul(c: Char) {
        if (choSungList.indexOf(c.code) < 0 && jungSungList.indexOf(c.code) < 0 && jongSungList.indexOf(c.code) < 0) {
            directCommitHangul()
            inputConnection.commitText(c.toString(), 1)
            return
        }

        when (inputState) {
            InputState.NULL -> {
                if (jungSungList.indexOf(c.code) >= 0) { // 중성
                    inputConnection.commitText(c.toString(), 1)
                    clear()
                } else { // 초성
                    inputState = InputState.CHO
                    choSung = c
                    inputConnection.setComposingText(choSung.toString(), 1) // 한글과 같이 조합 중인 텍스트를 입력받을 때마다 바로 알려주기
                }
            }

            InputState.CHO -> {
                if (choSungList.indexOf(c.code) >= 0) { // 초성
                    inputConnection.commitText(choSung.toString(), 1)
                    clear()
                    choSung = c
                    inputConnection.setComposingText(choSung.toString(), 1)
                } else { // 중성
                    inputState = InputState.CHOJUNG
                    jungSung = c
                    inputConnection.setComposingText(makeHangul().toString(), 1)
                }
            }

            InputState.CHOJUNG -> {
                if (jungSungList.indexOf(c.code) >= 0) { // 중성
                    if (doubleVowelEnable(c)) { // 이중 모음 O
                        inputConnection.setComposingText(makeHangul().toString(), 1)
                    } else { // 이중 모음 X
                        inputConnection.commitText(makeHangul().toString(), 1)
                        inputConnection.commitText(c.toString(), 1)
                        clear()
                        inputState = InputState.NULL
                    }
                } else if (jongSungList.indexOf(c.code) >= 0) { // 종성
                    jongSung = c
                    inputState = InputState.CHOJUNGJONG
                    inputConnection.setComposingText(makeHangul().toString(), 1)
                } else { // 초성
                    directCommitHangul()
                    choSung = c
                    inputState = InputState.CHO
                    inputConnection.setComposingText(makeHangul().toString(), 1)
                }
            }

            InputState.CHOJUNGJONG -> {
                if (jongSungList.indexOf(c.code) >= 0) { // 종성
                    if (doubleConsonantEnable(c)) { // 이중 자음 O
                        inputConnection.setComposingText(makeHangul().toString(), 1)
                    } else { // 이중 자음 X
                        inputConnection.commitText(makeHangul().toString(), 1)
                        clear()
                        inputState = InputState.CHO
                        choSung = c
                        inputConnection.setComposingText(choSung.toString(), 1)
                    }
                } else if (choSungList.indexOf(c.code) >= 0) { // 초성
                    inputConnection.commitText(makeHangul().toString(), 1)
                    inputState = InputState.CHO
                    clear()
                    choSung = c
                    inputConnection.setComposingText(choSung.toString(), 1)
                } else { // 중성
                    val temp: Char
                    if (lastConsonant == MIN_VALUE) { // 이중 자음 X
                        temp = jongSung
                        jongSung = MIN_VALUE
                        inputConnection.commitText(makeHangul().toString(), 1)
                    } else { // 이중 자음 O
                        temp = lastConsonant
                        jongSung = firstConsonant
                        inputConnection.commitText(makeHangul().toString(), 1)
                    }
                    inputState = InputState.CHOJUNG
                    clear()
                    choSung = temp
                    jungSung = c
                    inputConnection.setComposingText(makeHangul().toString(), 1)
                }
            }
        }
    }

    private fun doubleVowelEnable(c: Char): Boolean {
        val doubleVowel = jungSung.isDoubleVowelEnable(c)

        if (doubleVowel == ' ') return false
        else {
            firstVowel = jungSung
            jungSung = doubleVowel
            return true
        }
    }

    private fun doubleConsonantEnable(c: Char): Boolean {
        firstConsonant = jongSung
        lastConsonant = c
        val doubleConsonant = jongSung.isDoubleConsonantEnable(c)

        if (doubleConsonant == ' ') return false
        else {
            jongSung = doubleConsonant
            return true
        }
    }

    fun deleteHangul() {
        when(inputState) {
            InputState.NULL -> inputConnection.deleteSurroundingText(1, 0)

            InputState.CHO -> {
                choSung = MIN_VALUE
                inputState = InputState.NULL
                inputConnection.setComposingText("", 1)
                inputConnection.commitText("",1)
            }

            InputState.CHOJUNG -> {
                if (firstVowel != MIN_VALUE) { // 이중 모음 입력되었을 때 뒤에 입력된 모음 제거
                    jungSung = firstVowel
                    firstVowel = MIN_VALUE
                    inputConnection.setComposingText(makeHangul().toString(), 1)
                } else {
                    jungSung = MIN_VALUE
                    inputState = InputState.CHO
                    inputConnection.setComposingText(choSung.toString(), 1)
                }
            }

            InputState.CHOJUNGJONG -> {
                if (lastConsonant == MIN_VALUE) {
                    jongSung = MIN_VALUE
                    inputState = InputState.CHOJUNG
                } else {
                    jongSung = firstConsonant
                    firstConsonant = MIN_VALUE
                    lastConsonant = MIN_VALUE
                }
                inputConnection.setComposingText(makeHangul().toString(), 1)
            }
        }
    }

    fun directCommitHangul() {
        // 이동, 띄어쓰기, 한/영 키보드 변경과 같은 이벤트가 발생했을 때 setComposingText 상태의 문자를 commit
        if (inputState == InputState.NULL) {
            return
        }
        inputConnection.commitText(makeHangul().toString(), 1)
        inputState = InputState.NULL
        clear()
    }

    fun commitSpace() {
        directCommitHangul()
        inputConnection.commitText(" ", 1)
    }

    companion object {
        private const val MIN_VALUE = '\u0000'
    }
}