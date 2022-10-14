# 원티드 프리온보딩 안드로이드
- 동료들과 상의해서 프로젝트 진행 과정과 구현 결과를 한 눈에 파악할 수 있도록 README를 작성합니다.

## 황준성
- 맡은 부분
    - 첫번째 화면
        - Compose를 이용한 UI 표현
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

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/fae066b6-871b-4b27-999a-344d97317a54/Untitled.jpeg)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/0af94fa9-141b-48f8-ac32-3cbb04da3da2/Untitled.jpeg)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/dbb1676f-406f-4fb5-82a9-4cb50ec1aa73/Untitled.jpeg)

### 남은 일
- padding,해상도처리,하드코딩 수정
- scaffold,apativeLayout,composeState 추가?
