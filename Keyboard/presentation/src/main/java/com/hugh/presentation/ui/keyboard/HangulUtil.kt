package com.hugh.presentation.ui.keyboard

import android.view.inputmethod.InputConnection

val firstConsonantArr: Array<String> by lazy {
    arrayOf("ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ", "ㄹ", "ㅁ", "ㅂ", "ㅃ",
        "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅉ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ")
}
val middleVowelArr: Array<String> by lazy {
    arrayOf("ㅏ", "ㅐ", "ㅑ", "ㅒ", "ㅓ", "ㅔ", "ㅕ", "ㅖ", "ㅗ", "ㅘ",
        "ㅙ", "ㅚ", "ㅛ", "ㅜ", "ㅝ", "ㅞ", "ㅟ", "ㅠ", "ㅡ", "ㅢ", "ㅣ")
}
val lastConsonantArr: Array<String> by lazy {
    arrayOf("", "ㄱ", "ㄲ", "ㄳ", "ㄴ", "ㄵ", "ㄶ", "ㄷ", "ㄹ", "ㄺ", "ㄻ", "ㄼ",
        "ㄽ", "ㄾ", "ㄿ", "ㅀ", "ㅁ", "ㅂ", "ㅄ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ")
}

enum class KRType {
    FIRST, MIDDLE, LAST, OTHERS
}

class HangulUtil {

    /*
    * 0 = 아무것도 없는 상황
    * 1 = 이전이 자음이고 초성인 상황 ㅂ
    * 2 = 이전이 자음이고 종성인 상황 밥
    * 3 = 이전이 모음인 상황 보
    * */
    private var state = 0
    private var firstConsonant = ""
    private var middleVowel = ""
    private var lastConsonant = ""

    private fun setState() {
        state = when {
            lastConsonant.isNotBlank() -> 2
            middleVowel.isNotBlank() -> 3
            firstConsonant.isNotBlank() -> 1
            else -> 0
        }
    }

    private fun getHangul(): String {
        val firstIdx = firstConsonantArr.indexOf(firstConsonant)
        val middleIdx = middleVowelArr.indexOf(middleVowel)
        val lastIdx = lastConsonantArr.indexOf(lastConsonant)
        val combinedLetter: Char = ((firstIdx * 21 + middleIdx) * 28 + lastIdx + 0xAC00).toChar()
        return if (firstConsonant.isBlank()) middleVowel
        else if (middleVowel.isBlank()) firstConsonant
        else combinedLetter.toString()
    }

    private fun clear() {
        firstConsonant = ""
        middleVowel = ""
        lastConsonant = ""
    }

    private fun recordLetter(letter: String) {
        when (letter.getTypeOfKR()) {
            KRType.FIRST -> {
                if (middleVowel.isNotBlank()) lastConsonant = letter
                else firstConsonant = letter
            }
            KRType.MIDDLE -> middleVowel = letter
            KRType.LAST -> lastConsonant = letter
            KRType.OTHERS -> {}
        }
    }

    fun delete(inputConnection: InputConnection) {
        if (lastConsonant.isNotBlank()) {
            val singleConsonants = lastConsonant.getSingleConsonants()
            lastConsonant = if (singleConsonants == lastConsonant) "" else singleConsonants[0].toString()
            inputConnection.setComposingText(getHangul(), 1)
        } else if (middleVowel.isNotBlank()) {
            val singleVowels = middleVowel.getSingleVowels()
            middleVowel = if (singleVowels == middleVowel) "" else singleVowels[0].toString()
            inputConnection.setComposingText(getHangul(), 1)
        } else if (firstConsonant.isNotBlank()) {
            firstConsonant = ""
            inputConnection.setComposingText(getHangul(), 1)
        } else {
            inputConnection.deleteSurroundingText(1, 0)
        }
        setState()
    }

    fun updateLetter(inputConnection: InputConnection, letter: String) {
        val letterType = letter.getTypeOfKR()
        if (letterType == KRType.OTHERS) { // "." ",", " ", "\n"
            inputConnection.finishComposingText()
            inputConnection.commitText(letter, 1)
            clear()
            return
        }
        when (state) {
            0 -> { //아무것도 없는 상황
                recordLetter(letter)
                inputConnection.setComposingText(getHangul(), 1)
            }
            1 -> { //이전이 자음이고 초성인 상황 "ㅂ"
                if (letterType != KRType.MIDDLE) {
                    //"ㅂ" -> "ㅂㅇ"
                    inputConnection.commitText(getHangul(), 1)
                    clear()
                }
                //"ㅂ" -> "비"
                recordLetter(letter)
                inputConnection.setComposingText(getHangul(), 1)
            }
            2 -> { //2 이전이 자음이고 종성인 상황 "갑"
                if (letterType == KRType.MIDDLE) {
                    val singleConsonants = lastConsonant.getSingleConsonants()
                    if (singleConsonants == lastConsonant) { //"갑" -> "가비"
                        val temp = lastConsonant
                        lastConsonant = ""
                        inputConnection.commitText(getHangul(), 1)
                        clear()
                        recordLetter(temp)
                    } else { //값 -> 갑시
                        lastConsonant = singleConsonants[0].toString()
                        inputConnection.commitText(getHangul(), 1)
                        clear()
                        recordLetter(singleConsonants[1].toString())
                    }
                    recordLetter(letter)
                    inputConnection.setComposingText(getHangul(), 1)
                } else {
                    val temp = letter.getDoubleConsonant(lastConsonant)
                    if (temp == letter) { //"갑" -> "값"
                        inputConnection.commitText(getHangul(), 1)
                        clear()
                    }
                    //"갑" -> "갑ㅁ"
                    recordLetter(temp)
                    inputConnection.setComposingText(getHangul(), 1)
                }
            }
            3 -> { //3 이전이 모음인 상황 "ㅜ"
                if (letterType == KRType.MIDDLE) {
                    val temp = letter.getDoubleVowel(middleVowel)
                    if (temp == letter) { //"ㅜ" -> "ㅞ"
                        inputConnection.commitText(getHangul(), 1)
                        clear()
                    }
                    //"ㅜ" -> "ㅜㅑ"
                    recordLetter(temp)
                } else {
                    if (firstConsonant.isEmpty()) { //"ㅜ" -> "ㅜㅇ"
                        inputConnection.commitText(getHangul(), 1)
                        clear()
                    }
                    //"우" -> "웅
                    recordLetter(letter)
                }
                inputConnection.setComposingText(getHangul(), 1)
            }
        }
        setState()
    }
}