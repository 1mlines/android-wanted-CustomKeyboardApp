# 원티드 프리온보딩 안드로이드

## 요약
InputMethodService를 활용하여 어느 앱에서나 작동하는 두벌식 한글 키보드

## Members

<div align="center">
<table style="font-weight : bold">
  <tr>
    <td align="center">
      <a href="[https://github.com/KimHance](https://github.com/KimHance)">
        <img alt="김현수" src="https://avatars.githubusercontent.com/KimHance" width="80" />
      </a>
    </td>
    <td align="center">
      <a href="[https://github.com/gksgpd97](https://github.com/gksgpd97)">
        <img alt="한혜원" src="https://avatars.githubusercontent.com/gksgpd97" width="80" />
      </a>
    </td>
    <td align="center">
      <a href="[https://github.com/JaesungLeee](https://github.com/JaesungLeee)">
        <img alt="이재성" src="https://avatars.githubusercontent.com/JaesungLeee" width="80" />
      </a>
    </td>
    <td align="center">
      <a href="[https://github.com/YellowC-137](https://github.com/YellowC-137)">
        <img alt="황준성" src="https://avatars.githubusercontent.com/YellowC-137" width="80" />
      </a>
    </td>
    <td align="center">
      <a href="[https://github.com/seoyoon513](https://github.com/seoyoon513)">
        <img alt="이서윤" src="https://avatars.githubusercontent.com/seoyoon513" width="80" />
      </a>
    </td>
</tr>

<tr>
<td align="center">김현수</td>
<td align="center">한헤원</td>
<td align="center">이재성</td>
<td align="center">황준성</td>
<td align="center">이서윤</td>
</tr>
</table>
</div>

## 황준성
- 맡은 부분
    - 첫번째 화면(Compose를 이용한 UI 표현)


간단하게 전체 화면을 Column에 담아서 차례로 표현했습니다.

```kotlin
@Composable
fun MainColumn() {
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    )
    {
        IconBackPressed()
        ImageKeyBoard()
        TextKeyBoardName()
        TextEvent()
        ListTags()
        ListKeyWords()
        ListVotes()
        ImageBanner()
        TextReviewTitle()
        ListReview(sampleReview)
        BottomNav()
    }
}
```

Custom Typography를 적용하였습니다.

```kotlin

valNotoSansKR=FontFamily(
Font(R.font.noto_sanskr_bold, FontWeight.Bold),
Font(R.font.noto_sanskr_black, FontWeight.Black),
Font(R.font.noto_sanskr_light, FontWeight.Light),
Font(R.font.noto_sanskr_medium, FontWeight.Medium),
Font(R.font.noto_sanskr_regular, FontWeight.Normal),
Font(R.font.noto_sanskr_thin, FontWeight.Thin)
)

valLocalTypography=staticCompositionLocalOf{
ReplacementMyTypography(
        title_20_bold =myTypography.title_20_bold,
        title_16_bold =myTypography.title_16_bold,
        title_14 =myTypography.title_14,
        body_16 =myTypography.body_16,
        body_14 =myTypography.body_14,
        body_12 =myTypography.body_12,
        body_10 =myTypography.body_10,
        sub_title =myTypography.sub_title
    )
}

```
![KakaoTalk_20221014_122634781](https://user-images.githubusercontent.com/55780312/195756870-60ee5571-408d-4400-8f85-e62a0d26783b.jpg)

![KakaoTalk_20221014_122634781_01](https://user-images.githubusercontent.com/55780312/195756882-9c00dc42-f362-4d60-a65d-ac2ea4e9402b.jpg)

![KakaoTalk_20221014_122634781_02](https://user-images.githubusercontent.com/55780312/195756894-d44d4c05-c419-4301-a0c7-111b862b2fce.jpg)

### 남은 일
- 다이얼로그
- padding,해상도처리 수정
- scaffold,apativeLayout or constraintLayout 추가


## 한혜원
- 담당한 일
  - InputMethodService를 이용해 한글 키보드 구현
- 남은 일
  - 한글 오토마타 적용이 잘 안돼서 수정 필요
### 구현
- 키보드 뷰로 사용하기 위해 레이아웃에 필요한 만큼의 버튼을 배치
![image](https://user-images.githubusercontent.com/35549958/195756978-8f383f5b-1201-427b-bd9b-fba22224cfa3.png)

- FrameLayout에 키보드 뷰를 붙이고 KeyboardAction 클래스를 정의해서 inputConnection을 통해 타이핑이 가능하도록 함
```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/linearlayout_keyboard"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <FrameLayout
        android:id="@+id/framelayout_keyboard"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />
</LinearLayout>
```
```kotlin
override fun onCreateInputView(): View {
        keyboardView = layoutInflater.inflate(R.layout.view_keyboard, null) as LinearLayout
        keyboardFrame = keyboardView.findViewById(R.id.framelayout_keyboard)
        keyboardAction =
            KeyboardAction(applicationContext, layoutInflater)
        keyboardAction.inputConnection = currentInputConnection
        keyboardAction.init()
        return keyboardView
    }

override fun updateInputViewShown() {
    super.updateInputViewShown()
    currentInputConnection.finishComposingText()
    keyboardInterationListener.addView()
    /* addView()
    * currentInputConnection.finishComposingText()
    * keyboardFrame.removeAllViews()
    * keyboardAction.inputConnection = currentInputConnection
    * keyboardFrame.addView(keyboardAction.getLayout())
    * */
}
```
### 실행영상

https://user-images.githubusercontent.com/35549958/195693499-2098e7ee-3e15-40ab-8ce7-f89f799cae0d.mp4

## 김현수
- 커스텀 한글 키보드 구현
- 클립보드 구현

### 키보드
- 확장성을 고려하여 추상클래스 키보드를 상속받아 한글키보드 
```kotlin
abstract class Keyboard(
    context: Context,
    layoutInflater: LayoutInflater,
    listener: KeyboardActionListener
) {

    private val height = 150
    protected val config = context.resources.configuration

    protected val binding = ViewKeyboardActionBinding.inflate(layoutInflater)
    protected val keyboardActionListener = listener
    var inputConnection: InputConnection? = null

    protected fun setLayoutParamsLandScape(layout: LinearLayout) {
        layout.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            (height * 0.7).toInt()
        )
    }

    protected fun setLayoutParams(layout: LinearLayout) {
        layout.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            height
        )
    }

    abstract fun init()
    abstract fun changeMode()
    abstract fun setLayoutComponents()
    abstract fun doSpaceClick(): View.OnClickListener
    abstract fun doEnterClick(): View.OnClickListener
    abstract fun doShiftClick(): View.OnClickListener
    abstract fun doDeleteClick(): View.OnClickListener
}
```
```kotlin
class KoreanKeyBoard(
    private val context: Context,
    layoutInflater: LayoutInflater,
    listener: KeyboardActionListener
) : Keyboard(context, layoutInflater, listener)
```
||||
|:---|---|---|
| <img src = "https://user-images.githubusercontent.com/86879099/195752799-0a23df82-9031-47d7-98af-9f9b6e71ac36.gif" width = 200 height = 400>| <img src = "https://user-images.githubusercontent.com/86879099/195753038-2a451c4e-38b5-4eb5-8dbe-c8a27e8e5317.gif" width = 200 height = 400>|<img src = "https://user-images.githubusercontent.com/86879099/195753048-399d89c8-540c-4aeb-b9c4-54189bf24b1a.gif" width = 200 height = 400>|

### 클립보드
- 클립보드 변화 감지
```kotlin
class KeyboardService : InputMethodService(), ClipboardManager.OnPrimaryClipChangedListener {
    @Inject
    lateinit var roomUseCase: RoomUseCase

    private val clipList = MutableStateFlow<List<Clipboard>>(emptyList())
    private lateinit var clipboardManager: ClipboardManager

    ...
   
    override fun onPrimaryClipChanged() {
        serviceScope.launch {
            val clipData = clipboardManager.primaryClip
            clipData?.let {
                runCatching {
                    roomUseCase.insertClipData(clipData.getItemAt(0).text.toString())
                }.also {
                    roomUseCase.getAllClipData().collect { list ->
                        clipList.update {
                            list.toList()
                        }
                    }
                }
            }
        }
    }
}
```
- onPrimaryClipChanged()를 통해 클립보드의 변화를 감지하고 DB에 저장 후 DB의 모든 클립보드를 받아옵니다.
---

- 클립보드 리스트 업데이트
```kotlin
// KeyboardService
private val clipList = MutableStateFlow<List<Clipboard>>(emptyList())

...

private val clipboardListener = object : ClipboardActionListener {
    override fun deleteClipData(clipboard: ClipboardEntity) {
        serviceScope.launch {
             roomUseCase.deleteClipData(clipboard)
             roomUseCase.getAllClipData().collect { list ->
                clipList.update {list.toList()}
            }
        }   
    }

    override fun pasteClipData(clipData: String) {
        serviceScope.launch {
             val pasteText = roomUseCase.getClipData(clipData).clipData
             currentInputConnection.commitText(pasteText, 1)
        }
    }
}


override fun onCreateInputView(): View {
    
    ...
    
    serviceScope.launch {
        clipList.collect { list ->
            keyboardClipboard.updateClipList(list)
         }
    }
}
```
- 키보드 뷰가 생성되면 클립보드 리스트틑 collect하여 클립보드 어댑터의 리스트를 변경해줍니다.
- 클립보드 삭제와 붙혀넣기는 clipboardListener를 통하여 실행됩니다.

|||
|:---|---|
| <img src = "https://user-images.githubusercontent.com/86879099/195979528-f202087c-5971-42e8-89b0-204bda619e1f.gif" width = 200 height = 400>| <img src = "https://user-images.githubusercontent.com/86879099/195979745-42c09c15-35b3-479d-ad4e-6d61680f6084.gif" width = 200 height = 400>|

## 이재성
