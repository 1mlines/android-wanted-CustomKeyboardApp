package com.preonboarding.customkeyboard.presentation.keyboard.extension

fun Char.isDoubleConsonantEnable(c: Char): Char {
    when(this) {
        'ㄱ' -> {
            if (c == 'ㅅ') return 'ㄳ'
            return ' '
        }

        'ㄴ' -> {
            if (c == 'ㅈ') return 'ㄵ'
            if (c == 'ㅎ') return 'ㄶ'
            return ' '
        }

        'ㄹ' -> {
            if (c == 'ㄱ') return 'ㄺ'
            if (c == 'ㅁ') return 'ㄻ'
            if (c == 'ㅂ') return 'ㄼ'
            if (c == 'ㅅ') return 'ㄽ'
            if (c == 'ㅌ') return 'ㄾ'
            if (c == 'ㅍ') return 'ㄿ'
            if (c == 'ㅎ') return 'ㅀ'
            return ' '
        }

        'ㅂ' -> {
            if (c == 'ㅅ') return 'ㅄ'
            return ' '
        }

        else -> return ' '
    }
}

fun Char.isDoubleVowelEnable(c: Char): Char {
    when(this) {
        'ㅗ' -> {
            if (c == 'ㅏ') return 'ㅘ'
            if (c == 'ㅐ') return 'ㅙ'
            if (c == 'ㅣ') return 'ㅚ'
            return ' '
        }

        'ㅜ' -> {
            if (c == 'ㅓ') return 'ㅝ'
            if (c == 'ㅔ') return 'ㅞ'
            if (c == 'ㅣ') return 'ㅟ'
            return ' '
        }

        'ㅡ' -> {
            if (c == 'ㅣ') return 'ㅢ'
            return ' '
        }

        else -> return ' '
    }
}