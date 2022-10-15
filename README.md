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
### 맡은 역할
- 아키텍처 설계
- 클립보드 구현

### 아키텍처 구조
![image](https://user-images.githubusercontent.com/45396949/195513569-51892f24-703c-4632-8ce2-3b3639cd84b2.png)

- Domain 영역을 구현할 필요가 없어서 Data Layer와 Ui Layer로 구성했습니다.
- Data Layer는 repository interface 및 repositoryImpl을 포함하고 있으며 Room에 대한 정보를 갖습니다. 
- Ui Layer는 View와 관련된 작업이 존재하며, 정의된 Action에 의해 State를 생성합니다. 

### 프로젝트 구조
![image](https://user-images.githubusercontent.com/45396949/195514368-9cb5a761-f7c0-4f92-8614-f0bc26f4b9a1.png)
- Gradle
   - KTS 를 통해 gradle을 Kotlin Script로 구성하였습니다.

- buildSrc
   - buildSrc는 dsl을 이용하여 생성한 모듈이며, 멀티 모듈에서 Dependency 및 Version을 효율적으로 관리하기 위해 생성했습니다.

- Keyboard 
   - presentation은 Ui Layer를 의미하며 ViewModel 및 activity가 존재합니다.
   - data는 Data Layer를 의미하며 Room 및 Repository에 대한 정보를 갖습니다.

### 단방향 아키텍처 패턴
![image](https://user-images.githubusercontent.com/45396949/195514960-d13c9839-a80d-4c1d-934e-9c9f9d3b5f12.png)

**Action** 

![image](https://user-images.githubusercontent.com/45396949/195515768-c618e03b-5948-4e40-89c6-d5a0659dddc1.png)
- 유저의 intent를 정의한 것을 의미합니다. 

**Actor**

![image](https://user-images.githubusercontent.com/45396949/195515802-74d0988e-413d-4123-a113-ca557fccc0f7.png)
- 유저의 action에 의해 실행되어야 할 내부 로직을 바인딩하는 역할을 합니다. 

**Handler**

![image](https://user-images.githubusercontent.com/45396949/195515619-2dc81744-308d-48ab-925f-b0019c8430fe.png)
- Action을 파라미터로 갖는 메소드가 존재하는 인터페이스입니다. 
- Action처리를 하나의 메소드에서 처리하기 위해 선언합니다.
   
![image](https://user-images.githubusercontent.com/45396949/195516187-2cc4cdb0-7324-4e2c-a1ac-9e736ec74e2d.png)
- action을 한곳으로 관리할 수 있으며, action에 따라 처리해야하는 메소드를 호출합니다. 
- Action이 수행 될 때마다 항상 새 객체인 State를 발행합니다. 

**State**

![image](https://user-images.githubusercontent.com/45396949/195515838-cb53b6e5-83ae-4ccd-9cae-bce3e0e59e29.png)
- Action에 의해 생성되는 State를 의미합니다. 
- State 내부 값들은 불변으로 항상 값을 할당해야 합니다.

**클립보드 영상**

https://user-images.githubusercontent.com/45396949/195967318-7224cde2-93a3-4a06-8695-2b3c2fc3d55e.mp4

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

## 김현국 - UI

<img src="https://user-images.githubusercontent.com/62296097/195742795-00d74e6d-b141-4abf-bfd0-f34158d13d94.gif">

Jetpack compose로 구현하였습니다.

____
## Typography & Color 적용

```kotlin
val customKeyBoardTypography = CustomKeyBoardTypography(
    title = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp, lineHeight = 28.sp),
    allTitle = TextStyle(fontWeight = FontWeight.Bold, fontSize = 14.sp, lineHeight = 24.sp),
    allBodyMid = TextStyle(fontWeight = FontWeight.Medium, fontSize = 14.sp, lineHeight = 20.sp),
    allBody = TextStyle(fontWeight = FontWeight.Normal, fontSize = 14.sp, lineHeight = 24.sp),
    subTitle = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp, lineHeight = 24.sp),
    ...
) 

// USED
val AllMainColor = Color(0xFFFF417D)
val AllTitleGray = Color(0xFF42444C)
val AllSubDarkGray = Color(0xFF919299)
...

object CustomKeyBoardTheme {
    val typography: CustomKeyBoardTypography
        @Composable
        get() = LocalCustomKeyBoardTypography.current

    val color: Colors
        @Composable
        get() = LocalCustomKeyBoardColor.current
}

Ex)
Text(
    text = "테스트",
    style = CustomKeyBoard.typography.allBodyMid,
    color = CustomKeyBoard.color.allMainColor
)
```
</br>

## Navigation구현 
```kotlin
sealed class NavigationRoute(val route: String) {
    object InfoScreenGraph : NavigationRoute("info_screen") {
        object InfoScreen : NavigationRoute("info_screen.screen")
    }
    object KeyBoardTestScreenGraph : NavigationRoute("keyboard_test_screen") {
        object KeyBoardTestScreen : NavigationRoute("keyboard_test_screen.screen")
        object ClipBoardTestScreen : NavigationRoute("keyboard_test_screen.clip_board")
    }
}
```

```kotlin
internal fun NavGraphBuilder.keyboardTestGraph(
    customKeyBoardAppState: CustomKeyBoardAppState
) {
    navigation(
        route = NavigationRoute.KeyBoardTestScreenGraph.route,
        startDestination = NavigationRoute.KeyBoardTestScreenGraph.KeyBoardTestScreen.route
    ) {
        composable(
            route = NavigationRoute.KeyBoardTestScreenGraph.KeyBoardTestScreen.route
        ) {(...)}

        composable(
            route = NavigationRoute.KeyBoardTestScreenGraph.ClipBoardTestScreen.route
        ) { (...)}
    }
}
```

### 네비게이션 동작


https://user-images.githubusercontent.com/62296097/195755028-ac5b8fed-1bf4-48ad-9583-ba61529f6aa1.mp4

----

## 디바이스 크기 대응
<img src="https://user-images.githubusercontent.com/62296097/195751483-09780c94-97ff-4095-9b59-2f2343ae7922.png" width =200 height = 400>
<img src="https://user-images.githubusercontent.com/62296097/195751490-a97247e3-5b6a-4257-af4e-b47d34a0ca01.png" width =400 height = 500>
<img src="https://user-images.githubusercontent.com/62296097/195751493-b9a8d069-91c9-4d57-9d47-ca377e8b28f5.png" width =200 height = 400>

### 태블릿과 기타 디바이스에서 레이아웃이 깨지지 않게 UI를 구성했습니다.


____


## 권혁준

<img width="308" alt="image" src="https://user-images.githubusercontent.com/70066242/195756265-42763c51-d928-4218-a654-caafc7bc6a2c.png">


```kotlin
// 키보드 버튼 구현 
val menupadText = listOf<String>("Home", "Clip")
val numpadText = listOf<String>("1","2","3","4","5","6","7","8","9","0")
val firstLineText = listOf<String>("ㅂ","ㅈ","ㄷ","ㄱ","ㅅ","ㅛ","ㅕ","ㅑ","ㅐ","ㅔ")
val secondLineText = listOf<String>("ㅁ","ㄴ","ㅇ","ㄹ","ㅎ","ㅗ","ㅓ","ㅏ","ㅣ")
val thirdLineText = listOf<String>("CAPS","ㅋ","ㅌ","ㅊ","ㅍ","ㅠ","ㅜ","ㅡ","DEL")
val fourthLineText = listOf<String>("!#1","한/영",",","space",".","Enter")

val menupadLine = koreanLayout.findViewById<LinearLayout>(
            R.id.menupad_line
        )
        val numpadLine = koreanLayout.findViewById<LinearLayout>(
            R.id.numpad_line
```

```kotlin
// 특정 모드에 따른 키보드 화면 구성 설정 
interface KeyboardInterationListener {
    fun modechange(mode:Int)
}

override fun modechange(mode: Int) {
            currentInputConnection.finishComposingText()
            when(mode) {
                1 -> {
                    keyboardFrame.removeAllViews()
                    keyboardKorean.inputConnection = currentInputConnection
                    keyboardFrame.addView(keyboardKorean.getLayout())
                }
                2 -> {
                    keyboardFrame.removeAllViews()
                    keyboardClipboard.inputConnection = currentInputConnection
                    keyboardFrame.addView(keyboardClipboard.getLayout())
                }
            }
        }
```

```kotlin
// 기능에 따라 키보드 기능 구현 
"Enter" -> {
    spacialKey.setImageResource(R.drawable.ic_enter)
    spacialKey.visibility = View.VISIBLE
    actionButton.visibility = View.GONE
    myOnClickListener = getEnterAction()
    spacialKey.setOnClickListener(myOnClickListener)
    spacialKey.setOnTouchListener(getOnTouchListener(myOnClickListener))
    spacialKey.setBackgroundResource(R.drawable.key_background)
}

"Fav" -> {
                        spacialKey.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                        spacialKey.visibility = View.VISIBLE
                        actionButton.visibility = View.GONE
                        myOnClickListener = getFavoriteData()
                        spacialKey.setOnClickListener(myOnClickListener)
                        spacialKey.setOnTouchListener(getOnTouchListener(myOnClickListener))
                        spacialKey.setBackgroundResource(R.drawable.key_background)
                    }
```


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
