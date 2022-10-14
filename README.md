# 원티드 프리온보딩 안드로이드
- 동료들과 상의해서 프로젝트 진행 과정과 구현 결과를 한 눈에 파악할 수 있도록 README를 작성합니다.

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
![KakaoTalk_20221014_122634781](https://user-images.githubusercontent.com/55780312/195756870-60ee5571-408d-4400-8f85-e62a0d26783b.jpg)

![KakaoTalk_20221014_122634781_01](https://user-images.githubusercontent.com/55780312/195756882-9c00dc42-f362-4d60-a65d-ac2ea4e9402b.jpg)

![KakaoTalk_20221014_122634781_02](https://user-images.githubusercontent.com/55780312/195756894-d44d4c05-c419-4301-a0c7-111b862b2fce.jpg)


### 남은 일
- 다이얼로그 
- padding,해상도처리,하드코딩 수정
- scaffold,apativeLayout or constraintLayout,composeState 추가
