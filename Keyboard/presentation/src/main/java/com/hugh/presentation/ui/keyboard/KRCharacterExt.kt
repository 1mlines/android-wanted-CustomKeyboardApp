package com.hugh.presentation.ui.keyboard

fun String.getTypeOfKR(): KRType? {
    return when (this.codePointAt(0)) {
        in firstConsonantArr[0].codePointAt(0)..firstConsonantArr[firstConsonantArr.lastIndex].codePointAt(0) -> {
            KRType.FIRST
        }
        in middleVowelArr[0].codePointAt(0)..middleVowelArr[middleVowelArr.lastIndex].codePointAt(0) -> {
            KRType.MIDDLE
        }
        in lastConsonantArr[1].codePointAt(0)..lastConsonantArr[lastConsonantArr.lastIndex].codePointAt(0) -> {
            KRType.LAST
        }
        else -> {
            println("잘못된 문자입니다.")
            null
        }
    }
}

fun String.getEachIndex(): Triple<Int, Int, Int> {
    //조합 : (초성 * 21 + 중성) * 28 + 종성 + 0xAC00
    //초성 : ((문자유니코드 - 0xAC00)/28)/21
    //중성 : (문자유니코드 - 0xAC00) / 28 % 21
    //종성 : (문자유니코드 - 0xAC00) % 28
    val letterCode = this.codePointAt(0) - 0xAC00
    val first = letterCode / 28 / 21
    val middle = letterCode / 28 % 21
    val last = letterCode % 28
    return Triple(first, middle, last)
}

fun String.getDoubleVowel(vowel: String): String {
    //ㅚ ㅙ ㅘ
    // ㅟ ㅞ ㅝ
    // ㅢ
    return when {
        vowel == "ㅗ" && this == "ㅣ" -> "ㅚ"
        vowel == "ㅗ" && this == "ㅐ" -> "ㅙ"
        vowel == "ㅗ" && this == "ㅏ" -> "ㅘ"
        vowel == "ㅜ" && this == "ㅣ" -> "ㅟ"
        vowel == "ㅜ" && this == "ㅔ" -> "ㅞ"
        vowel == "ㅜ" && this == "ㅓ" -> "ㅝ"
        vowel == "ㅡ" && this == "ㅣ" -> "ㅢ"
        else -> this
    }
}

fun String.getDoubleConsonant(consonant: String): String {
    return when {
        consonant == "ㄱ" && this == "ㅅ" -> "ㄳ"
        consonant == "ㄴ" && this == "ㅈ" -> "ㄵ"
        consonant == "ㄴ" && this == "ㅎ" -> "ㄶ"
        consonant == "ㄹ" && this == "ㄱ" -> "ㄺ"
        consonant == "ㄹ" && this == "ㅁ" -> "ㄻ"
        consonant == "ㄹ" && this == "ㅂ" -> "ㄼ"
        consonant == "ㄹ" && this == "ㅅ" -> "ㄽ"
        consonant == "ㄹ" && this == "ㅌ" -> "ㄾ"
        consonant == "ㄹ" && this == "ㅍ" -> "ㄿ"
        consonant == "ㄹ" && this == "ㅎ" -> "ㅀ"
        consonant == "ㅂ" && this == "ㅅ" -> "ㅄ"
        else -> this
    }
}

fun String.getSingleConsonants(): String {
    return when (this) {
        "ㄳ" -> "ㄱㅅ"
        "ㄵ" -> "ㄴㅈ"
        "ㄶ" -> "ㄴㅎ"
        "ㄺ" -> "ㄹㄱ"
        "ㄻ" -> "ㄹㅁ"
        "ㄼ" -> "ㄹㅂ"
        "ㄽ" -> "ㄹㅅ"
        "ㄾ" -> "ㄹㅌ"
        "ㄿ" -> "ㄹㅍ"
        "ㅀ" -> "ㄹㅎ"
        "ㅄ" -> "ㅂㅅ"
        else -> this
    }
}
