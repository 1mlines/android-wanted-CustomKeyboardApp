# 📖 Summary

- 레이아웃 & 사용자 인터페이스를 구현합니다.
- `InputMethodService` 를 활용하여 어느 앱에서나 작동하는 두벌식 한글 키보드를 구현합니다.
- 키보드에 클립보드와 단축키 기능을 구현합니다.

# 👨‍👨‍👦‍👦 Members

<div align="center">
  <table style="font-weight : bold">
      <tr>
          <td align="center">
              <a href="https://github.com/hoyahozz">                 
                  <img alt="김정호" src="https://avatars.githubusercontent.com/hoyahozz" width="80" />            
              </a>
          </td>
          <td align="center">
              <a href="https://github.com/gyurim">                 
                  <img alt="박규림" src="https://avatars.githubusercontent.com/gyurim" width="80" />            
              </a>
          </td>
          <td align="center">
              <a href="https://github.com/INAH-PAK">                 
                  <img alt="박인아" src="https://avatars.githubusercontent.com/INAH-PAK" width="80" />            
              </a>
          </td>
          <td align="center">
              <a href="https://github.com/leehandsub">                 
                  <img alt="이현섭" src="https://avatars.githubusercontent.com/leehandsub" width="80" />            
              </a>
          </td>
          <td align="center">
              <a href="https://github.com/sujin-kk">                 
                  <img alt="임수진" src="https://avatars.githubusercontent.com/sujin-kk" width="80" />            
              </a>
          </td>
      </tr>
      <tr>
          <td align="center">김정호</td>
          <td align="center">박규림</td>
          <td align="center">박인아</td>
          <td align="center">이현섭</td>
          <td align="center">임수진</td>
      </tr>
  </table>
</div>

# 🤝 Convention

## 📚 Branch

- `{behavior}/issue-{number}-{something}` 
- e.g. : `feature/issue-007-data-module`

## 🤔 Issue

- `[{behavior}] {something}`
- e.g. : `[FEATURE] 프로젝트 세팅`

## 🤲 Pull Request

- `[ISSUE-{number}] {something}`
- e.g. : `[ISSUE-007] 데이터 모듈 추가`
- 문장 단위가 아닌, **단어 단위**로 제목을 작성합니다.

## 😊 Commit

- `feat - {something}` : 새로운 기능을 추가했을 때
- `fix - {something}` : 기능 중 버그를 수정했을 때
- `design - {something}` : 디자인 일부를 변경했을 때
- `refactor - {something}` : 코드를 재정비 하였을 때
- `chore - {something}` : 빌드 관련 업무를 수정하거나 패키지 매니저를 수정했을 때
- `docs - {something}` : README와 같은 문서를 변경했을 때
- 문장 단위가 아닌, **단어 단위**로 제목을 작성합니다.

## ⌨️ Coding
- [android-style-guide](https://github.com/PRNDcompany/android-style-guide)의 코딩 컨벤션과 동일하게 진행합니다.

## ⚙️ Tech Stack
`Clean Architecture`, `Multi-Module`, `MVVM`, `Kotlin`, `Hilt`, `Coroutine`, `Room`, `Lifecycle`, `Databinding`, `Livedata`, `Timber`

# ✍️ Implementation

## 김정호

### 프로젝트 베이스 구축

![image](https://user-images.githubusercontent.com/85336456/195961991-30730b79-45ba-4b0b-9fd9-4de713dc2c64.png)

- `Clean Architecture` 를 주요 아키텍처 패턴으로 채택 및 구현, 레이어별 모듈 구성
- `Presentation` 모듈의 경우 `MVVM` 패턴에 따라 베이스 구현
- `Hilt` 모듈 구현
- `Room` 데이터베이스 설계 및 구축
- 규격화된 폰트 적용을 위해 폰트 리소스 구현

### 메인 페이지 UI 구현

![UI](https://user-images.githubusercontent.com/85336456/195962020-1de82596-16f8-498b-a721-0efc79b35d48.gif)

- 제공된 디자인 시스템에 따라 UI 구현
- 버튼 클릭 시 `ripple` 이펙트가 나타나도록 구현
- 반응형으로 모든 기기에서 동일하게 보여지도록 구현
- 다이얼로그 UI 구현

## 이현섭

https://user-images.githubusercontent.com/35682233/195629876-03ce9f00-712f-4fe1-9b90-eba514506998.mp4

- **기본적인 키보드 ui**

```kotlin
private lateinit var qwertyKeyboardLayout: LinearLayout
    private val qwertyMainKeyboardText = arrayOf(
        arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "0"),
        arrayOf("ㅂ", "ㅈ", "ㄷ", "ㄱ", "ㅅ", "ㅛ", "ㅕ", "ㅑ", "ㅐ", "ㅔ"),
        arrayOf("ㅁ", "ㄴ", "ㅇ", "ㄹ", "ㅎ", "ㅗ", "ㅓ", "ㅏ", "ㅣ"),
        arrayOf(context.getString(R.string.key_shift), "ㅋ", "ㅌ", "ㅊ", "ㅍ", "ㅠ", "ㅜ", "ㅡ", context.getString(R.string.key_back)),
        arrayOf(
            context.getString(R.string.key_special),
            context.getString(R.string.key_short),
            ",",
            context.getString(R.string.key_space),
            ".",
            context.getString(R.string.key_enter)
        )
    )
    private val qwertySubKeyboardText = arrayOf(
        arrayOf("ㅃ", "ㅉ", "ㄸ", "ㄲ", "ㅆ", "/", "<", ">", "ㅒ", "ㅖ"),
        arrayOf("!", "@", "#", "%", "^", "&", "*", "(", ")"),
        arrayOf("", "-", "\'", "\"", ":", ";", ",", "?")
    )
```

- **모든앱에 사용하게 만들기**

manifest

```kotlin
<service
            android:name=".presentation.keyboard.KeyBoardService"
            android:enabled="true"
            android:exported="true"
            android:label="MyKeyboard"
            android:permission="android.permission.BIND_INPUT_METHOD">
            <meta-data
                android:name="android.view.im"
                android:resource="@xml/xml"/>

            <intent-filter>
                <action android:name="android.view.InputMethod"/>
            </intent-filter>
        </service>
```

- **쉬프트 키 눌럿을 때 구현**

```kotlin
private fun changeCaps() {
        for (keyNum in qwertyMainKeyboardText[SHIFT_CHANGE_LINE].indices) {
            val layout = layoutLines[SHIFT_CHANGE_LINE].children.toList()
            val mainKeyText = layout[keyNum].findViewById<TextView>(R.id.main_key_text)

            if (isCaps) {
                ...
						}
        isCaps = !isCaps
    }
```

- 해당 ui에서 글씨를 바꾸게 했습니다.

## 박규림

- **CustomKeyboard 앱에서 작동 시연**

https://user-images.githubusercontent.com/31344894/195647844-a097d512-5a68-4748-b694-adfdd340a1bf.mp4

- **다른 앱에서 작동 시연**

https://user-images.githubusercontent.com/31344894/195647674-938a2430-ab76-41bf-af99-956af9e8c751.mp4

- **이중 자음, 모음 판별을 위한 확장 함수**
- `DoubleEnableExtension.kt`

```kotlin
// 이중 모음 판별
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

// 이중 자음 판별
fun Char.isDoubleConsonantEnable(c: Char): Char {
  // 생략 
}
```
- **한글 키보드 입력 상태 분류**
- `InputState.kt`

```kotlin
enum class InputState {
    NULL, 
    CHO,  
    CHOJUNG,
    CHOJUNGJONG
}
```

- **Custom Keyboard으로 입력받은 한글을 조합**
- InputMethodService로 사용자가 텍스트를 입력하면 InputConnection 인스턴스를 사용하여 텍스트를 전달 

<img width="450" alt="스크린샷 2022-10-14 오후 12 41 10" src="https://user-images.githubusercontent.com/31344894/195757367-fb59af9f-a93c-4ece-89c5-089b315223a4.png">


<img width="1309" alt="스크린샷 2022-10-14 오후 12 41 17" src="https://user-images.githubusercontent.com/31344894/195757405-19a0e0e5-af4b-41b0-b0e5-a54d76da26a1.png">

<img width="1800" alt="스크린샷 2022-10-14 오후 12 41 23" src="https://user-images.githubusercontent.com/31344894/195757418-3386c43f-173c-49b9-838b-32e208ead643.png">


<img width="1900" alt="스크린샷 2022-10-14 오후 12 41 44" src="https://user-images.githubusercontent.com/31344894/195757431-41cc0506-36fb-402b-838c-22faf403f3d4.png">

- `KoreaLanguageMaker.kt`

```kotlin
private var choSung: Char = MIN_VALUE
private var jungSung: Char = MIN_VALUE
private var jongSung: Char = MIN_VALUE

private val choSungList: List<Int> = listOf(0x3131, 0x3132, 0x3134, 0x3137, 0x3138, 0x3139, 0x3141,0x3142, 0x3143, 0x3145, 0x3146, 0x3147, 0x3148, 0x3149, 0x314a, 0x314b, 0x314c, 0x314d, 0x314e)
private val jungSungList:List<Int> = listOf(0x314f, 0x3150, 0x3151, 0x3152, 0x3153, 0x3154, 0x3155, 0x3156, 0x3157, 0x3158, 0x3159, 0x315a, 0x315b, 0x315c, 0x315d, 0x315e, 0x315f, 0x3160, 0x3161, 0x3162, 0x3163)
private val jongSungList:List<Int> = listOf(0x0000, 0x3131, 0x3132, 0x3133, 0x3134, 0x3135, 0x3136, 0x3137, 0x3139, 0x313a, 0x313b, 0x313c, 0x313d, 0x313e, 0x313f, 0x3140, 0x3141, 0x3142, 0x3144, 0x3145, 0x3146, 0x3147, 0x3148, 0x314a, 0x314b, 0x314c, 0x314d, 0x314e)

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
```

## 박인아


실행영상



https://user-images.githubusercontent.com/95750706/195759969-6acbd1fc-f0cc-412d-adc9-8c44fa292a3a.mp4


