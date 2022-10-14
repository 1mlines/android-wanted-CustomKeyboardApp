package com.hugh.presentation.ui.keyboard

fun String.getTypeOfKR(): KRType = when (this.codePointAt(0)) {
    in firstConsonantArr[0].codePointAt(0)..firstConsonantArr[firstConsonantArr.lastIndex].codePointAt(0) -> {
        KRType.FIRST
    }
    in middleVowelArr[0].codePointAt(0)..middleVowelArr[middleVowelArr.lastIndex].codePointAt(0) -> {
        KRType.MIDDLE
    }
    in lastConsonantArr[1].codePointAt(0)..lastConsonantArr[lastConsonantArr.lastIndex].codePointAt(0) -> {
        KRType.LAST
    }
    else -> KRType.OTHERS
}

fun String.getDoubleVowel(vowel: String): String = when {
    vowel == "ㅗ" && this == "ㅣ" -> "ㅚ"
    vowel == "ㅗ" && this == "ㅐ" -> "ㅙ"
    vowel == "ㅗ" && this == "ㅏ" -> "ㅘ"
    vowel == "ㅜ" && this == "ㅣ" -> "ㅟ"
    vowel == "ㅜ" && this == "ㅔ" -> "ㅞ"
    vowel == "ㅜ" && this == "ㅓ" -> "ㅝ"
    vowel == "ㅡ" && this == "ㅣ" -> "ㅢ"
    else -> this
}


fun String.getDoubleConsonant(consonant: String): String = when {
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


fun String.getSingleConsonants(): String = when (this) {
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

fun String.getSingleVowels(): String = when (this) {
    "ㅚ" -> "ㅗㅣ"
    "ㅙ" -> "ㅗㅐ"
    "ㅘ" -> "ㅗㅏ"
    "ㅟ" -> "ㅜㅣ"
    "ㅞ" -> "ㅜㅔ"
    "ㅝ" -> "ㅜㅓ"
    "ㅢ" -> "ㅡㅣ"
    else -> this
}

