package com.preonboarding.customkeyboard.keyboard

import android.content.Context
import android.view.inputmethod.InputConnection
import com.preonboarding.customkeyboard.R

class KoreaCombiner(
    private val inputConnection: InputConnection?,
    private val context: Context
) {
    private var chosung: Char = '\u0000'
    private var jungsung: Char = '\u0000'
    private var jongsung: Char = '\u0000'
    private var jonFlag: Char = '\u0000'
    private var doubleJonFlag: Char = '\u0000'
    var junFlag: Char = '\u0000'

    private val chos: List<Int> = listOf(
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
    private val juns: List<Int> =listOf(
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
    private val jons: List<Int> = listOf(
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
     * 0: ""
     * 1: 모음
     * 2: 모음 + 자음
     * 3: 모음 + 자음 + 모읍립력
     */
    private var state = 0

    fun clear() {
        chosung = '\u0000'
        jungsung = '\u0000'
        jongsung = '\u0000'
        jonFlag = '\u0000'
        doubleJonFlag = '\u0000'
        junFlag = '\u0000'
    }

    fun delete() {
        when (state) {
            0 -> {
                inputConnection?.deleteSurroundingText(1, 0)
            }
            1 -> {
                chosung = '\u0000'
                state = 0
                inputConnection?.setComposingText("", 1)
                inputConnection?.commitText("", 1)
            }
            2 -> {
                if (junFlag != '\u0000') {
                    jungsung = junFlag
                    junFlag = '\u0000'
                    state = 2
                    inputConnection?.setComposingText(makeHangul().toString(), 1)
                } else {
                    jungsung = '\u0000'
                    junFlag = '\u0000'
                    state = 1
                    inputConnection?.setComposingText(chosung.toString(), 1)
                }
            }
            3 -> {
                if (doubleJonFlag == '\u0000') {
                    jongsung = '\u0000'
                    state = 2
                } else {
                    jongsung = jonFlag
                    jonFlag = '\u0000'
                    doubleJonFlag = '\u0000'
                    state = 3
                }
                inputConnection?.setComposingText(makeHangul().toString(), 1)
            }
        }
    }

    private fun makeHangul(): Char {
        if (state == 0) {
            return '\u0000'
        }
        if (state == 1) {
            return chosung
        }
        val choIndex = chos.indexOf(chosung.code)
        val junIndex = juns.indexOf(jungsung.code)
        val jonIndex = jons.indexOf(jongsung.code)

        val result = 0xAC00 + 28 * 21 * choIndex + 28 * junIndex + jonIndex
        return result.toChar()
    }

    fun commitSpace() {
        directlyCommit()
        inputConnection?.commitText(" ", 1)
    }

    fun directlyCommit() {
        if (state == 0) {
            return
        }
        inputConnection?.commitText(makeHangul().toString(), 1)
        state = 0
        clear()
    }

    fun commit(c: Char) {
        if (chos.indexOf(c.code) < 0 && juns.indexOf(c.code) < 0 && jons.indexOf(c.code) < 0) {
            directlyCommit()
            inputConnection?.commitText(c.toString(), 1)
            return
        }
        when (state) {
            0 -> {
                if (juns.indexOf(c.code) >= 0) {
                    inputConnection?.commitText(c.toString(), 1)
                    clear()
                } else {
                    state = 1
                    chosung = c
                    inputConnection?.setComposingText(chosung.toString(), 1)
                }
            }
            1 -> {
                if (chos.indexOf(c.code) >= 0) {
                    inputConnection?.commitText(chosung.toString(), 1)
                    clear()
                    chosung = c
                    inputConnection?.setComposingText(chosung.toString(), 1)
                } else {
                    state = 2
                    jungsung = c
                    inputConnection?.setComposingText(makeHangul().toString(), 1)
                }
            }
            2 -> {
                if (juns.indexOf(c.code) >= 0) {
                    if (isDoubleJunEnable(c)) {
                        inputConnection?.setComposingText(makeHangul().toString(), 1)
                    } else {
                        inputConnection?.apply {
                            commitText(makeHangul().toString(), 1)
                            commitText(c.toString(), 1)
                        }
                        clear()
                        state = 0
                    }
                } else if (jons.indexOf(c.code) >= 0) {
                    jongsung = c
                    inputConnection?.setComposingText(makeHangul().toString(), 1)
                    state = 3
                } else {
                    directlyCommit()
                    chosung = c
                    state = 1
                    inputConnection?.setComposingText(makeHangul().toString(), 1)
                }
            }
            3 -> {
                if (jons.indexOf(c.code) >= 0) {
                    if (isDoubleJonEnable(c)) {
                        inputConnection?.setComposingText(makeHangul().toString(), 1)
                    } else {
                        inputConnection?.commitText(makeHangul().toString(), 1)
                        clear()
                        state = 1
                        chosung = c
                        inputConnection?.setComposingText(chosung.toString(), 1)
                    }

                } else if (chos.indexOf(c.code) >= 0) {
                    inputConnection?.commitText(makeHangul().toString(), 1)
                    state = 1
                    clear()
                    chosung = c
                    inputConnection?.setComposingText(chosung.toString(), 1)
                } else {
                    var temp: Char = '\u0000'
                    if (doubleJonFlag == '\u0000') {
                        temp = jongsung
                        jongsung = '\u0000'
                        inputConnection?.commitText(makeHangul().toString(), 1)
                    } else {
                        temp = doubleJonFlag
                        jongsung = jonFlag
                        inputConnection?.commitText(makeHangul().toString(), 1)
                    }
                    state = 2
                    clear()
                    chosung = temp
                    jungsung = c
                    inputConnection?.setComposingText(makeHangul().toString(), 1)
                }
            }
        }
    }

    private fun isDoubleJonEnable(c: Char): Boolean {
        when (jungsung) {
            'ㅗ' -> {
                if (c == 'ㅏ') {
                    junFlag = jungsung
                    jungsung = 'ㅘ'
                    return true
                }
                if (c == 'ㅐ') {
                    junFlag = jungsung
                    jungsung = 'ㅙ'
                    return true
                }
                if (c == 'ㅣ') {
                    junFlag = jungsung
                    jungsung = 'ㅚ'
                    return true
                }
                return false
            }
            'ㅜ' -> {
                if (c == 'ㅓ') {
                    junFlag = jungsung
                    jungsung = 'ㅝ'
                    return true
                }
                if (c == 'ㅔ') {
                    junFlag = jungsung
                    jungsung = 'ㅞ'
                    return true
                }
                if (c == 'ㅣ') {
                    junFlag = jungsung
                    jungsung = 'ㅟ'
                    return true
                }
                return false
            }
            'ㅡ' -> {
                if (c == 'ㅣ') {
                    junFlag = jungsung
                    jungsung = 'ㅢ'
                    return true
                }
                return false
            }
            else -> {
                return false
            }
        }
    }

    private fun isDoubleJunEnable(c: Char): Boolean {
        jonFlag = jongsung
        doubleJonFlag = c
        when (jongsung) {
            'ㄱ' -> {
                if (c == 'ㅅ') {
                    jongsung = 'ㄳ'
                    return true
                }
                return false
            }
            'ㄴ' -> {
                if (c == 'ㅈ') {
                    jongsung = 'ㄵ'
                    return true
                }
                if (c == 'ㅎ') {
                    jongsung = 'ㄶ'
                    return true
                }
                return false
            }
            'ㄹ' -> {
                if (c == 'ㄱ') {
                    jongsung = 'ㄺ'
                    return true
                }
                if (c == 'ㅁ') {
                    jongsung = 'ㄻ'
                    return true
                }
                if (c == 'ㅂ') {
                    jongsung = 'ㄼ'
                    return true
                }
                if (c == 'ㅅ') {
                    jongsung = 'ㄽ'
                    return true
                }
                if (c == 'ㅌ') {
                    jongsung = 'ㄾ'
                    return true
                }
                if (c == 'ㅍ') {
                    jongsung = 'ㄿ'
                    return true
                }
                if (c == 'ㅎ') {
                    jongsung = 'ㅀ'
                    return true
                }
                return false
            }
            'ㅂ' -> {
                if (c == 'ㅅ') {
                    jongsung = 'ㅄ'
                    return true
                }
                return false
            }
            else -> {
                return false
            }
        }
    }

    fun isJungsungAvailable(): Boolean {
        if (jungsung == 'ㅙ' || jungsung == 'ㅞ' || jungsung == 'ㅢ' || jungsung == 'ㅐ' || jungsung == 'ㅔ' || jungsung == 'ㅛ' || jungsung == 'ㅒ' || jungsung == 'ㅖ') {
            return false
        }
        return true
    }

    fun isDoubleJunsung(): Boolean {
        if (jungsung == 'ㅙ' || jungsung == 'ㅞ' || jungsung == 'ㅚ' || jungsung == 'ㅝ' || jungsung == 'ㅟ' || jungsung == 'ㅘ' || jungsung == 'ㅢ') {
            return true
        }
        return false
    }
}
