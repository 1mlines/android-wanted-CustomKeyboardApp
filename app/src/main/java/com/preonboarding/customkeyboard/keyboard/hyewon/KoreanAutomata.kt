package com.preonboarding.customkeyboard.keyboard.hyewon

import android.view.inputmethod.InputConnection

class KoreanAutomata(private val inputConnection: InputConnection){
    private var choSeong: Char = '\u0000'
    private var jungSeong: Char = '\u0000'
    private var jongSeong: Char = '\u0000'
    private var jongFlag: Char = '\u0000'
    private var doubleJongFlag: Char = '\u0000'
    private var jungFlag: Char = '\u0000'

    private val choSeongList: List<Int> = listOf(
        0x3131,
        0x3132,
        0x3134,
        0x3137,
        0x3138,
        0x3139,
        0x3141,
        0x3142,
        0x3143,
        0x3145,
        0x3146,
        0x3147,
        0x3148,
        0x3149,
        0x314a,
        0x314b,
        0x314c,
        0x314d,
        0x314e
    )
    private val jungSeongList: List<Int> = listOf(
        0x314f,
        0x3150,
        0x3151,
        0x3152,
        0x3153,
        0x3154,
        0x3155,
        0x3156,
        0x3157,
        0x3158,
        0x3159,
        0x315a,
        0x315b,
        0x315c,
        0x315d,
        0x315e,
        0x315f,
        0x3160,
        0x3161,
        0x3162,
        0x3163
    )
    private val jongSeongList: List<Int> = listOf(
        0x0000,
        0x3131,
        0x3132,
        0x3133,
        0x3134,
        0x3135,
        0x3136,
        0x3137,
        0x3139,
        0x313a,
        0x313b,
        0x313c,
        0x313d,
        0x313e,
        0x313f,
        0x3140,
        0x3141,
        0x3142,
        0x3144,
        0x3145,
        0x3146,
        0x3147,
        0x3148,
        0x314a,
        0x314b,
        0x314c,
        0x314d,
        0x314e
    )

    /**
     * 0:""
     * 1: 모음 입력상태
     * 2: 모음 + 자음 입력상태
     * 3: 모음 + 자음 + 모음입력상태(초 중 종성)
     * 초성과 종성에 들어갈 수 있는 문자가 다르기 때문에 필요에 맞게 수정이 필요함.(chos != jons)
     */
    private var state = SyllableState.Empty

    fun clear() {
        choSeong = '\u0000'
        jungSeong = '\u0000'
        jongSeong = '\u0000'
        jongFlag = '\u0000'
        doubleJongFlag = '\u0000'
        jungFlag = '\u0000'
    }

    private fun makeHan(): Char {
        if (state == SyllableState.Empty) {
            return '\u0000'
        }
        if (state == SyllableState.ChoSeong) {
            return choSeong
        }
        val choIndex = choSeongList.indexOf(choSeong.code)
        val jungIndex = jungSeongList.indexOf(jungSeong.code)
        val jongIndex = jongSeongList.indexOf(jongSeong.code)

        val makeResult = 0xAC00 + 28 * 21 * (choIndex) + 28 * (jungIndex) + jongIndex

        return makeResult.toChar()
    }

    fun commit(c: Char) {
        if (choSeongList.indexOf(c.code) < 0 && jungSeongList.indexOf(c.code) < 0 && jongSeongList.indexOf(c.code) < 0) {
            directlyCommit()
            inputConnection.commitText(c.toString(), 1)
            return
        }
        when (state) {
            SyllableState.Empty -> {
                if (jungSeongList.indexOf(c.code) >= 0) {
                    inputConnection.commitText(c.toString(), 1)
                    clear()
                } else {//초성일 경우
                    state = SyllableState.ChoSeong
                    choSeong = c
                    inputConnection.setComposingText(choSeong.toString(), 1)
                }
            }
            SyllableState.ChoSeong -> {
                if (choSeongList.indexOf(c.code) >= 0) {
                    inputConnection.commitText(choSeong.toString(), 1)
                    clear()
                    choSeong = c
                    inputConnection.setComposingText(choSeong.toString(), 1)
                } else {//중성일 경우
                    state = SyllableState.ChoJungSeong
                    jungSeong = c
                    inputConnection.setComposingText(makeHan().toString(), 1)
                }
            }
            SyllableState.ChoJungSeong -> {
                if (jungSeongList.indexOf(c.code) >= 0) {
                    if (doubleJunEnable(c)) {
                        inputConnection.setComposingText(makeHan().toString(), 1)
                    } else {
                        inputConnection.commitText(makeHan().toString(), 1)
                        inputConnection.commitText(c.toString(), 1)
                        clear()
                        state = SyllableState.Empty
                    }
                } else if (jongSeongList.indexOf(c.code) >= 0) {//종성이 들어왔을 경우
                    jongSeong = c
                    inputConnection.setComposingText(makeHan().toString(), 1)
                    state = SyllableState.ChoJungJongSeong
                } else {
                    directlyCommit()
                    choSeong = c
                    state = SyllableState.ChoSeong
                    inputConnection.setComposingText(makeHan().toString(), 1)
                }
            }
            SyllableState.ChoJungJongSeong -> {
                if (jongSeongList.indexOf(c.code) >= 0) {
                    if (doubleJonEnable(c)) {
                        inputConnection.setComposingText(makeHan().toString(), 1)
                    } else {
                        inputConnection.commitText(makeHan().toString(), 1)
                        clear()
                        state = SyllableState.ChoSeong
                        choSeong = c
                        inputConnection.setComposingText(choSeong.toString(), 1)
                    }

                } else if (choSeongList.indexOf(c.code) >= 0) {
                    inputConnection.commitText(makeHan().toString(), 1)
                    state = SyllableState.ChoSeong
                    clear()
                    choSeong = c
                    inputConnection.setComposingText(choSeong.toString(), 1)
                } else {//중성이 들어올 경우
                    val temp: Char
                    if (doubleJongFlag == '\u0000') {
                        temp = jongSeong
                        jongSeong = '\u0000'
                        inputConnection.commitText(makeHan().toString(), 1)
                    } else {
                        temp = doubleJongFlag
                        jongSeong = jongFlag
                        inputConnection.commitText(makeHan().toString(), 1)
                    }
                    state = SyllableState.ChoJungSeong
                    clear()
                    choSeong = temp
                    jungSeong = c
                    inputConnection.setComposingText(makeHan().toString(), 1)
                }
            }
        }
    }

    fun commitSpace() {
        directlyCommit()
        inputConnection.commitText(" ", 1)
    }

    fun directlyCommit() {
        if (state == SyllableState.Empty) {
            return
        }
        inputConnection.commitText(makeHan().toString(), 1)
        state = SyllableState.Empty
        clear()
    }

    fun delete() {
        when (state) {
            SyllableState.Empty -> {
                inputConnection.deleteSurroundingText(1, 0)
            }
            SyllableState.ChoSeong -> {
                choSeong = '\u0000'
                state = SyllableState.Empty
                inputConnection.setComposingText("", 1)
                inputConnection.commitText("", 1)
            }
            SyllableState.ChoJungSeong -> {
                if (jungFlag != '\u0000') {
                    jungSeong = jungFlag
                    jungFlag = '\u0000'
                    state = SyllableState.ChoJungSeong
                    inputConnection.setComposingText(makeHan().toString(), 1)
                } else {
                    jungSeong = '\u0000'
                    jungFlag = '\u0000'
                    state = SyllableState.ChoSeong
                    inputConnection.setComposingText(choSeong.toString(), 1)
                }
            }
            SyllableState.ChoJungJongSeong -> {
                if (doubleJongFlag == '\u0000') {
                    jongSeong = '\u0000'
                    state = SyllableState.ChoJungSeong
                } else {
                    jongSeong = jongFlag
                    jongFlag = '\u0000'
                    doubleJongFlag = '\u0000'
                    state = SyllableState.ChoJungJongSeong
                }
                inputConnection.setComposingText(makeHan().toString(), 1)
            }
        }
    }

    private fun doubleJunEnable(c: Char): Boolean {
        when (jungSeong) {
            'ㅗ' -> {
                if (c == 'ㅏ') {
                    jungFlag = jungSeong
                    jungSeong = 'ㅘ'
                    return true
                }
                if (c == 'ㅐ') {
                    jungFlag = jungSeong
                    jungSeong = 'ㅙ'
                    return true
                }
                if (c == 'ㅣ') {
                    jungFlag = jungSeong
                    jungSeong = 'ㅚ'
                    return true
                }
                return false
            }
            'ㅜ' -> {
                if (c == 'ㅓ') {
                    jungFlag = jungSeong
                    jungSeong = 'ㅝ'
                    return true
                }
                if (c == 'ㅔ') {
                    jungFlag = jungSeong
                    jungSeong = 'ㅞ'
                    return true
                }
                if (c == 'ㅣ') {
                    jungFlag = jungSeong
                    jungSeong = 'ㅟ'
                    return true
                }
                return false
            }
            'ㅡ' -> {
                if (c == 'ㅣ') {
                    jungFlag = jungSeong
                    jungSeong = 'ㅢ'
                    return true
                }
                return false
            }
            else -> {
                return false
            }
        }
    }

    private fun doubleJonEnable(c: Char): Boolean {
        jongFlag = jongSeong
        doubleJongFlag = c
        when (jongSeong) {
            'ㄱ' -> {
                if (c == 'ㅅ') {
                    jongSeong = 'ㄳ'
                    return true
                }
                return false
            }
            'ㄴ' -> {
                if (c == 'ㅈ') {
                    jongSeong = 'ㄵ'
                    return true
                }
                if (c == 'ㅎ') {
                    jongSeong = 'ㄶ'
                    return true
                }
                return false
            }
            'ㄹ' -> {
                if (c == 'ㄱ') {
                    jongSeong = 'ㄺ'
                    return true
                }
                if (c == 'ㅁ') {
                    jongSeong = 'ㄻ'
                    return true
                }
                if (c == 'ㅂ') {
                    jongSeong = 'ㄼ'
                    return true
                }
                if (c == 'ㅅ') {
                    jongSeong = 'ㄽ'
                    return true
                }
                if (c == 'ㅌ') {
                    jongSeong = 'ㄾ'
                    return true
                }
                if (c == 'ㅍ') {
                    jongSeong = 'ㄿ'
                    return true
                }
                if (c == 'ㅎ') {
                    jongSeong = 'ㅀ'
                    return true
                }
                return false
            }
            'ㅂ' -> {
                if (c == 'ㅅ') {
                    jongSeong = 'ㅄ'
                    return true
                }
                return false
            }
            else -> {
                return false
            }
        }
    }
}