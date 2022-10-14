# 원티드 프리온보딩 안드로이드
# 2팀 CustomKeyBoardApp

## 팀원

<div align="center">
  <table style="font-weight : bold">
      <tr>
          <td align="center">
              <a href="https://github.com/tjrkdgnl">                 
                  <img alt="tjrkdgnl" src="https://avatars.githubusercontent.com/tjrkdgnl" width="80" />            
              </a>
          </td>
          <td align="center">
              <a href="https://github.com/dudwls901">                 
                  <img alt="gyurim" src="https://avatars.githubusercontent.com/u/66052467?v=4" width="80" />            
              </a>
          </td>
          <td align="center">
              <a href="https://github.com/014967 ">                 
                  <img alt="lsy524" src="https://avatars.githubusercontent.com/014967 " width="80" />            
              </a>
          </td>
          <td align="center">
              <a href="https://github.com/DavidKwon7">                 
                  <img alt="davidKwon7" src="https://avatars.githubusercontent.com/u/70066242?v=4" width="80" />            
              </a>
          </td>
      </tr>
      <tr>
          <td align="center">서강휘</td>
          <td align="center">김영진</td>
          <td align="center">김현국</td>
          <td align="center">권혁준</td>
      </tr>
  </table>
</div>

## 서강휘

## 김영진
### 맡은 역할
- 한글 오토마타 구현

https://user-images.githubusercontent.com/66052467/195747773-4810124c-7213-4e76-bf12-b11b5598238d.mov
### HangulUtil.kt
```kotlin
//종성
val lastConsonantArr: Array<String> by lazy {
    arrayOf("", "ㄱ", "ㄲ", "ㄳ", "ㄴ", "ㄵ", "ㄶ", "ㄷ", "ㄹ", "ㄺ", "ㄻ", "ㄼ",
        "ㄽ", "ㄾ", "ㄿ", "ㅀ", "ㅁ", "ㅂ", "ㅄ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ")
}

//입력 문자 타입
enum class KRType {
    FIRST, MIDDLE, LAST, OTHERS
}

/* 상태 정의, 초성, 중성, 종성 관리
* 0 = 아무것도 없는 상황
* 1 = 이전이 자음이고 초성인 상황 ㅂ
* 2 = 이전이 자음이고 종성인 상황 밥
* 3 = 이전이 모음인 상황 보
* */
private var state = 0
private var firstConsonant = ""
private var middleVowel = ""
private var lastConsonant = ""

//상태값 설정
private fun setState() {
    state = when {
        lastConsonant.isNotBlank() -> 2
        middleVowel.isNotBlank() -> 3
        firstConsonant.isNotBlank() -> 1
        else -> 0
    }
}

//문자 기록 (초성 or 중성 or 종성)
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

//한글 조합
private fun getHangul(): String {
    val firstIdx = firstConsonantArr.indexOf(firstConsonant)
    val middleIdx = middleVowelArr.indexOf(middleVowel)
    val lastIdx = lastConsonantArr.indexOf(lastConsonant)
    val combinedLetter: Char = ((firstIdx * 21 + middleIdx) * 28 + lastIdx + 0xAC00).toChar()
    return if (firstConsonant.isBlank()) middleVowel
    else if (middleVowel.isBlank()) firstConsonant
    else combinedLetter.toString()
}

// 문자 출력
fun updateLetter(inputConnection: InputConnection, letter: String) {
		
		val letterType = letter.getTypeOfKR()
		if (letterType == KRType.OTHERS) { // "." ",", " ", "\n"
		    inputConnection.finishComposingText()
		    inputConnection.commitText(letter, 1)
		    clear()
		    return
		}

		when (state) {
				3 -> { //3 이전이 모음인 상황 "ㅜ"
            if (letterType == KRType.MIDDLE) {
                val temp = letter.getDoubleVowel(middleVowel)
                if (temp == letter) { //"ㅜ" -> "ㅜㅑ"
                    inputConnection.commitText(getHangul(), 1)
                    clear()
                }
                //"ㅜ" -> "ㅞ"
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

// 문자 삭제
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

```
### KRCharacterExt.kt
```kotlin
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

```

## 김현국

## 권혁준


## Convention

### Branch Convention

```feat/<branch name>  ```

- e.g) ```feat/Base Architecture ```


### Commit convention

``` [prefix]: <commit content> ```

- e.g) ``` feat: DAO 개발완료 ```

- e.g) ``` fix: room crash 수정 ```

- e.g) ``` refactor: MVVM 아키텍처 구조 리팩토링 ```

### Issue Convention
``` [prefix] 작업할 내용 ```

- e.g) ``` [feat] base architecture 생성 ```
- e.g) ``` [fix] room crash 수정 ```
- e.g) ``` [refactor] Sensor구조 일부 수정 ```

- 브랜치를 생성하기 전, github issue를 생성해주세요.
- branch 명의 issue number는 해당 issue Number로 지정합니다.

### PR Convention
``` [Issue-#number] PR 내용 ```

- e.g) ``` [Issue-#7] Timer 추가 ``` 

-----
