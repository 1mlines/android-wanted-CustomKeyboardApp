package com.preonboarding.customkeyboard.presentation


import android.graphics.Color.blue
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.google.accompanist.flowlayout.FlowRow
import com.preonboarding.customkeyboard.R
import com.preonboarding.customkeyboard.data.local.entity.Review
import com.preonboarding.customkeyboard.presentation.ui.theme.ReplacementTheme


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                MainColumn()
            }

        }
    }
}

@Composable
fun MainColumn() {
    val sampleReview = mutableListOf<Review>()
    (0..9).forEach {
        sampleReview.add(
            it,
            Review(
                stringResource(id = R.string.review_customer_name) + " $it",
                stringResource(id = R.string.review_customer)
            )
        )
    }

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

@Composable
fun IconBackPressed() {
    Icon(
        painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
        contentDescription = null,
        modifier = Modifier.padding(bottom = 10.dp, start = 16.dp, top = 16.dp)
    )
}

@Composable
fun ImageKeyBoard() {
    Card(elevation = 10.dp, modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)) {
        Image(
            painter = painterResource(id = R.drawable.keyboard_img),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
fun TextKeyBoardName() {
    Text(
        text = "앙무",
        modifier = Modifier.padding(start = 16.dp),
        style = ReplacementTheme.NewTypography.title_20_bold
    )
    Text(
        text = "코핀",
        modifier = Modifier
            .padding(start = 16.dp)
            .offset(y = (-16).dp),
        style = ReplacementTheme.NewTypography.body_14, color = colorResource(id = R.color.gray)
    )
    Text(buildAnnotatedString {
        withStyle(style = SpanStyle(color = colorResource(id = R.color.main))) {
            append("78")
        }
        withStyle(
            style = SpanStyle(color = colorResource(id = R.color.gray))
        ) { append("명이참여했어요!") }
    }, modifier = Modifier.padding(start = 16.dp))
}

@Composable
fun TextEvent() {
    Text(
        text = stringResource(id = R.string.event_title),
        modifier = Modifier.padding(top = 40.dp, start = 16.dp),
        style = ReplacementTheme.NewTypography.title_14
    )
    Text(
        text = stringResource(id = R.string.event_description),
        style = ReplacementTheme.NewTypography.body_14,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 16.dp, end = 16.dp)
    )
}

@Composable
fun ListTags() {
    Text(
        text = "태그",
        modifier = Modifier.padding(top = 48.dp, start = 16.dp),
        style = ReplacementTheme.NewTypography.title_16_bold
    )
    FlowRow(
        modifier = Modifier.padding(top = 16.dp, start = 16.dp)
    ) {

        Text(
            text = stringResource(id = R.string.tag1),
            modifier = Modifier
                .padding(8.dp)
                .background(colorResource(id = R.color.background), RoundedCornerShape(18.dp))
                .padding(start = 10.dp, end = 10.dp),
            style = ReplacementTheme.NewTypography.body_14
        )

        Text(
            text = stringResource(id = R.string.tag2),
            modifier = Modifier
                .padding(8.dp)
                .background(colorResource(id = R.color.background), RoundedCornerShape(18.dp))
                .padding(start = 10.dp, end = 10.dp),
            style = ReplacementTheme.NewTypography.body_14
        )

        Text(
            text = stringResource(id = R.string.tag3),
            modifier = Modifier
                .padding(8.dp)
                .background(colorResource(id = R.color.background), RoundedCornerShape(18.dp))
                .padding(start = 10.dp, end = 10.dp),
            style = ReplacementTheme.NewTypography.body_14
        )

        Text(
            text = stringResource(id = R.string.tag4),
            modifier = Modifier
                .padding(8.dp)
                .background(colorResource(id = R.color.background), RoundedCornerShape(18.dp))
                .padding(start = 10.dp, end = 10.dp),
            style = ReplacementTheme.NewTypography.body_14
        )

        Text(
            text = stringResource(id = R.string.tag5),
            modifier = Modifier
                .padding(8.dp)
                .background(colorResource(id = R.color.background), RoundedCornerShape(18.dp))
                .padding(start = 10.dp, end = 10.dp),
            style = ReplacementTheme.NewTypography.body_14
        )

        Text(
            text = stringResource(id = R.string.tag6),
            modifier = Modifier
                .padding(8.dp)
                .background(colorResource(id = R.color.background), RoundedCornerShape(18.dp))
                .padding(start = 10.dp, end = 10.dp),
            style = ReplacementTheme.NewTypography.body_14
        )
    }


}

@Composable
fun ListKeyWords() {

    Text(
        text = "이런 키워드에 반응해요",
        modifier = Modifier.padding(top = 40.dp, start = 16.dp),
        style = ReplacementTheme.NewTypography.title_16_bold
    )
    Row(modifier = Modifier.padding(top = 40.dp, start = 16.dp)) {
        Card(elevation = 32.dp, modifier = Modifier.padding(end = 16.dp)) {
            Column(
                modifier = Modifier
                    .height(160.dp)
                    .width(130.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.keyword1),
                    contentDescription = null,
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp)
                        .padding(8.dp)
                )
                Text(
                    text = stringResource(id = R.string.keyword1),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(8.dp),
                    style = ReplacementTheme.NewTypography.title_14
                )
            }
        }
        Card(elevation = 32.dp, modifier = Modifier.padding(end = 16.dp)) {
            Column(
                modifier = Modifier
                    .height(160.dp)
                    .width(130.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.keyword2),
                    contentDescription = null,
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp)
                        .padding(8.dp)
                )
                Text(
                    text = stringResource(id = R.string.keyword2),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(8.dp),
                    style = ReplacementTheme.NewTypography.title_14
                )
            }
        }
        Card(elevation = 32.dp) {
            Column(
                modifier = Modifier
                    .height(160.dp)
                    .width(130.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.keyword3),
                    contentDescription = null,
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp)
                        .padding(8.dp), contentScale = ContentScale.Crop
                )
                Text(
                    text = stringResource(id = R.string.keyword3),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(8.dp),
                    style = ReplacementTheme.NewTypography.title_14
                )
            }
        }


    }
}

@Composable
fun ListVotes() {
    Text(
        text = "이 테마를 어떻게 생각하나요?",
        modifier = Modifier.padding(top = 48.dp, start = 16.dp),
        style = ReplacementTheme.NewTypography.title_16_bold
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, start = 16.dp), horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(end = 8.dp)
        ) {
            Text(text = stringResource(id = R.string.vote_emoji1), fontSize = 30.sp)
            Text(
                text = stringResource(id = R.string.vote_title1),
                modifier = Modifier.padding(top = 8.dp),
                style = ReplacementTheme.NewTypography.body_12,
                color = colorResource(id = R.color.gray)
            )
            Text(
                text = "0", style = ReplacementTheme.NewTypography.body_12, color = colorResource(
                    id = R.color.gray
                )
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            Text(text = stringResource(id = R.string.vote_emoji2), fontSize = 30.sp)
            Text(
                text = stringResource(id = R.string.vote_title2),
                modifier = Modifier.padding(top = 8.dp),
                color = colorResource(id = R.color.main),
                style = ReplacementTheme.NewTypography.body_12,
            )
            Text(
                text = "1",
                color = colorResource(id = R.color.main),
                style = ReplacementTheme.NewTypography.body_12
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            Text(text = stringResource(id = R.string.vote_emoji3), fontSize = 30.sp)
            Text(
                text = stringResource(id = R.string.vote_title3),
                modifier = Modifier.padding(top = 8.dp),
                style = ReplacementTheme.NewTypography.body_12,
                color = colorResource(id = R.color.gray)
            )
            Text(
                text = "0", style = ReplacementTheme.NewTypography.body_12, color = colorResource(
                    id = R.color.gray
                )
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Text(text = stringResource(id = R.string.vote_emoji4), fontSize = 30.sp)
            Text(
                text = stringResource(id = R.string.vote_title4),
                modifier = Modifier.padding(top = 8.dp),
                style = ReplacementTheme.NewTypography.body_12,
                color = colorResource(id = R.color.gray)
            )
            Text(
                text = "0", style = ReplacementTheme.NewTypography.body_12, color = colorResource(
                    id = R.color.gray
                )
            )
        }
    }
}

@Composable
fun ImageBanner() {
    Image(
        painter = painterResource(id = R.drawable.banner_img),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp)
    )
}

@Composable
fun TextReviewTitle() {
    Row(modifier = Modifier.padding(top = 48.dp, start = 16.dp)) {
        Text(
            text = "구매 리뷰",
            modifier = Modifier
                .padding(end = 8.dp)
                .align(Alignment.CenterVertically),
            style = ReplacementTheme.NewTypography.title_16_bold
        )
        Text(
            text = "10",
            color = colorResource(id = R.color.main),
            modifier = Modifier.align(Alignment.CenterVertically),
            style = ReplacementTheme.NewTypography.title_16_bold
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_warn),
            contentDescription = null,
            modifier = Modifier
                .padding(end = 12.dp)
                .align(Alignment.CenterVertically)
        )
        Text(
            text = "테마를 구매해야 리뷰를 남길 수 있어요", modifier = Modifier
                .weight(1F)
                .align(Alignment.CenterVertically), style = ReplacementTheme.NewTypography.body_14
        )
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_keyboard_arrow_down_24),
            contentDescription = null, modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(end = 16.dp)

        )

    }

}

@Composable
fun ListReview(sampleReview: MutableList<Review>) {
    val scrollState = rememberLazyListState()

    LazyColumn(
        state = scrollState,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        item {
            Row(
                modifier = Modifier.padding(8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.wrapContentSize()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = "크리에이터",
                        style = TextStyle(color = Color.White, fontSize = 10.sp),
                        modifier = Modifier
                            .offset(y = -15.dp)
                            .background(colorResource(id = R.color.main), RoundedCornerShape(24.dp))
                            .padding(horizontal = 6.dp)
                    )
                }

                Column() {
                    Column(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(horizontal = 12.dp)
                            .background(
                                colorResource(id = R.color.background),
                                RoundedCornerShape(20.dp)
                            )
                    ) {
                        Text(
                            text = stringResource(id = R.string.review_creator_name),
                            style = ReplacementTheme.NewTypography.sub_title,
                            modifier = Modifier.padding(horizontal = 12.dp)
                        )
                        Text(
                            text = stringResource(id = R.string.review_creator),
                            style = ReplacementTheme.NewTypography.body_14,
                            modifier = Modifier.padding(horizontal = 12.dp)
                        )
                    }
                    Text(
                        text = "1일",
                        color = colorResource(id = R.color.background),
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                }
            }
        }


        items(sampleReview) { sampleReview ->
            Row(
                modifier = Modifier.padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = null,
                    modifier = Modifier.size(50.dp),
                    contentScale = ContentScale.Crop
                )
                Column() {
                    Column(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(start = 8.dp)
                            .background(
                                colorResource(id = R.color.background),
                                RoundedCornerShape(20.dp)
                            )
                    ) {
                        Text(
                            text = sampleReview.name,
                            style = ReplacementTheme.NewTypography.sub_title.copy(fontSize = 12.sp),
                            modifier = Modifier.padding(horizontal = 12.dp)
                        )
                        Text(
                            text = sampleReview.content,
                            style = ReplacementTheme.NewTypography.body_14.copy(fontSize = 12.sp),
                            modifier = Modifier.padding(horizontal = 12.dp)
                        )
                    }
                    Row(modifier = Modifier.padding(horizontal = 12.dp)) {
                        Text(text = "1분 ", color = colorResource(id = R.color.background))
                        Text(text = "신고", color = colorResource(id = R.color.background))
                    }


                }
            }

        }

    }
}

@Composable
fun BottomNav() {
    val openDialog = remember { (mutableStateOf(false)) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .border(0.1.dp, color = colorResource(id = R.color.background), RectangleShape)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(bottom = 5.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.diamond),
                contentDescription = null,
                Modifier
                    .align(Alignment.CenterVertically)
                    .height(12.dp)
                    .width(12.dp)
            )
            Text(
                text = " 5",
                modifier = Modifier.align(Alignment.CenterVertically),
                style = ReplacementTheme.NewTypography.title_16_bold,
                color = colorResource(id = R.color.blue)
            )
        }

        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(color = colorResource(id = R.color.main))) {
                    append("0젬 ")
                }
                withStyle(style = SpanStyle(color = colorResource(id = R.color.gray))) {
                    append(" 보유 중 ")
                }

            }, modifier = Modifier
                .align(Alignment.BottomStart)
        )
        ClickableText(
            text = AnnotatedString(
                "구매하기"
            ),
            style = TextStyle(color = Color.White, fontSize = 14.sp),
            onClick = { openDialog.value = true },
            modifier = Modifier
                .background(colorResource(id = R.color.main), RoundedCornerShape(24.dp))
                .align(Alignment.CenterEnd)
                .padding(top = 12.dp, bottom = 12.dp, end = 16.dp, start = 16.dp)
        )

    }
    if (openDialog.value) {
        DiamondDialog(openDialog)
    }
}

@Composable
fun DiamondDialog(openDialog: MutableState<Boolean>) {
    if (openDialog.value) {
        CustomDialog(onDismissRequest = { openDialog.value = false }) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .background(Color.White), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.keyword1),
                    contentDescription = null,
                    Modifier
                        .height(100.dp)
                        .width(100.dp)
                        .padding(8.dp)
                )
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(color = colorResource(id = R.color.blue))) {
                            append("N젬")
                        }
                        append(stringResource(id = R.string.dialog_recharge))
                    }, modifier = Modifier
                )
                Text(
                    text = stringResource(id = R.string.dialog_recharge2),
                    modifier = Modifier
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = " 젬 수량"
                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_baseline_keyboard_arrow_left_24),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(24.dp))
                        Image(
                            painter = painterResource(id = R.drawable.diamond),
                            contentDescription = null
                        )
                        Text(text = "5", color = colorResource(id = R.color.blue))
                        Spacer(modifier = Modifier.width(24.dp))
                        Image(
                            painter = painterResource(id = R.drawable.ic_baseline_keyboard_arrow_right_24),
                            contentDescription = null
                        )
                    }


                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, start = 8.dp, end = 8.dp, bottom = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "결제 금액", modifier = Modifier
                    )
                    Text("₩ 1,100", color = colorResource(id = R.color.main))
                }
                ClickableText(
                    text = AnnotatedString(
                        "충전하고 바로 사용하기"
                    ),
                    style = TextStyle(color = Color.White, fontSize = 14.sp),
                    onClick = { openDialog.value = false },
                    modifier = Modifier
                        .padding(vertical = 16.dp, horizontal = 24.dp)
                        .background(
                            colorResource(id = R.color.main),
                            RoundedCornerShape(24.dp)
                        )
                        .align(Alignment.CenterHorizontally)
                        .padding(8.dp)

                )

            }


        }

    }
}

@Composable
fun CustomDialog(
    onDismissRequest: () -> Unit,
    properties: DialogProperties = DialogProperties(),
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties
    ) {
        Surface(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(24.dp)
        ) { content() }

    }
}


@Preview(showBackground = true)
@Composable
fun ReviewPreview() {
    val sampleReview = mutableListOf<Review>()
    (0..9).forEach {
        sampleReview.add(
            it,
            Review(
                stringResource(id = R.string.review_customer_name) + "$it",
                stringResource(id = R.string.review_customer)
            )
        )
    }
    ListReview(sampleReview)
}
