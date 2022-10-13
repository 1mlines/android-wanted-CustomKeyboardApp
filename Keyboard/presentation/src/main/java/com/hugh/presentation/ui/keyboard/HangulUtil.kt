package com.hugh.presentation.ui.keyboard

import android.view.inputmethod.InputConnection

val firstConsonantArr = arrayOf("ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ", "ㄹ", "ㅁ", "ㅂ", "ㅃ",
    "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅉ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ")
val middleVowelArr = arrayOf("ㅏ", "ㅐ", "ㅑ", "ㅒ", "ㅓ", "ㅔ", "ㅕ", "ㅖ", "ㅗ", "ㅘ",
    "ㅙ", "ㅚ", "ㅛ", "ㅜ", "ㅝ", "ㅞ", "ㅟ", "ㅠ", "ㅡ", "ㅢ", "ㅣ")
val lastConsonantArr = arrayOf("", "ㄱ", "ㄲ", "ㄳ", "ㄴ", "ㄵ", "ㄶ", "ㄷ", "ㄹ", "ㄺ", "ㄻ", "ㄼ",
    "ㄽ", "ㄾ", "ㄿ", "ㅀ", "ㅁ", "ㅂ", "ㅄ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ")

enum class KRType {
    FIRST, MIDDLE, LAST
}

class HangulUtil {

    /*
    * 0 = 아무것도 없는 상황
    * 1 = 이전이 자음이고 초성인 상황 ㅂ
    * 2 = 이전이 자음이고 종성인 상황 ㅂ
    * 3 = 이전이 모음인 상황 ㅗ
    * */
    private var state = 0
    private var firstConsonant = ""
    private var middleVowel = ""
    private var lastConsonant = ""
    private var isFirstLetter = true

    private fun setState() {
        state = when {
            lastConsonant.isNotBlank() -> 2
            middleVowel.isNotBlank() -> 3
            firstConsonant.isNotBlank() -> 1
            else -> 0
        }
    }

    private fun getLetter(): String {
        val firstIdx = firstConsonantArr.indexOf(firstConsonant)
        val middleIdx = middleVowelArr.indexOf(middleVowel)
        val lastIdx = lastConsonantArr.indexOf(lastConsonant)
        val combinedLetter: Char = ((firstIdx * 21 + middleIdx) * 28 + lastIdx + 0xAC00).toChar()
//        Log.e("abc", "char : ${char} f : $firstLetter m : $middleLetter ll : $lastLetter")
        return if (firstConsonant.isBlank()) middleVowel
        else if (middleVowel.isBlank()) firstConsonant
        else combinedLetter.toString()
    }

    private fun clear() {
        firstConsonant = ""
        middleVowel = ""
        lastConsonant = ""
    }

    private fun recordValue(letter: String) {
        when (letter.getTypeOfKR()) {
            KRType.FIRST -> {
                if (middleVowel.isNotBlank()) lastConsonant = letter
                else firstConsonant = letter
            }
            KRType.MIDDLE -> middleVowel = letter
            KRType.LAST -> lastConsonant = letter
            else -> {}
        }
    }

    fun updateNum(inputConnection: InputConnection, num: Int) {
        inputConnection.finishComposingText()
        inputConnection.commitText(num.toString(), 1)
        clear()
    }

    fun updateHangul(inputConnection: InputConnection, cur: String) {
        val curType = cur.getTypeOfKR()
        when (state) {
            0 -> { //0 = 아무것도 없는 상황
                //바로 setComposing
                recordValue(cur)
                inputConnection.setComposingText(getLetter(), 1)
            }
            1 -> { //1 = 이전이 자음이고 초성인 상황 ㅂ
                //모음이 들어오면 현재 자리에서 결합 "비"
                if (curType != KRType.MIDDLE) {
                    //자음이 들어오면 바로 넘어감 "ㅂㅅ"
                    inputConnection.commitText(getLetter(), 1)
                    clear()
                }
                recordValue(cur)
                inputConnection.setComposingText(getLetter(), 1)
            }
            2 -> { //2 = 이전이 자음이고 종성인 상황 "갑"
                //모음이 들어오면 다음 거에서 결합 "가비"
                //값 -> 갑시
                if (curType == KRType.MIDDLE) {
                    val singleConsonants = lastConsonant.getSingleConsonants()
                    if (singleConsonants == lastConsonant) {
                        val temp = lastConsonant
                        lastConsonant = ""
                        inputConnection.commitText(getLetter(), 1)
                        clear()
                        recordValue(temp)
                    } else {
                        lastConsonant = singleConsonants[0].toString()
                        inputConnection.commitText(getLetter(), 1)
                        clear()
                        recordValue(singleConsonants[1].toString())
                    }
                    recordValue(cur)
                    inputConnection.setComposingText(getLetter(), 1)
                } else {
                    //자음이 들어오면 합칠 수 있으면 합치고 아니면 넘어감 "값", "갑ㅁ"
                    val tempCur = cur.getDoubleConsonant(lastConsonant)
                    if (tempCur == cur) {
                        inputConnection.commitText(getLetter(), 1)
                        clear()
                    }
                    recordValue(tempCur)
                    inputConnection.setComposingText(getLetter(), 1)
                }
            }
            3 -> { //4 = 이전이 모음인 상황 ㅗ
                //모음이 들어왔고 합성 가능하면 합치기 "ㅚ"
                //모음이 들어왔고 합성 불가면 넘기기 "ㅗㅡ"
                //일단 무조건 넘기기
                if (curType == KRType.MIDDLE) {
                    val tempCur = cur.getDoubleVowel(middleVowel)
                    if (tempCur == cur) {
                        inputConnection.commitText(getLetter(), 1)
                        clear()
                    }
                    recordValue(tempCur)
                } else {
                    //자음이 들어오면 "ㅗㅇ" 혹은 "옹"
                    if (firstConsonant.isEmpty()) {
                        inputConnection.commitText(getLetter(), 1)
                        clear()
                    }
                    recordValue(cur)
                }
                inputConnection.setComposingText(getLetter(), 1)
            }
        }
        setState()
    }
}