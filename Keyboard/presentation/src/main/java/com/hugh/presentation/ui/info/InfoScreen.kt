package com.hugh.presentation.ui.info

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import com.hugh.presentation.R
import com.hugh.presentation.action.compose.InfoNavTarget
import com.hugh.presentation.action.compose.info.InfoAction
import com.hugh.presentation.action.compose.info.InfoActionActor
import com.hugh.presentation.navigation.NavigationRoute
import com.hugh.presentation.ui.component.KeyWordCard
import com.hugh.presentation.ui.component.MultiStyleText
import com.hugh.presentation.ui.component.ReviewCard
import com.hugh.presentation.ui.component.Tag
import com.hugh.presentation.ui.component.ThemeCard
import com.hugh.presentation.ui.component.TopBar
import com.hugh.presentation.ui.info.data.KeyWordData
import com.hugh.presentation.ui.info.data.ReviewData
import com.hugh.presentation.ui.info.data.TagsData
import com.hugh.presentation.ui.info.data.ThemeData
import com.hugh.presentation.ui.main.CustomKeyBoardAppState
import com.hugh.presentation.ui.theme.CustomKeyBoardTheme
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * @Created by 김현국 2022/10/12
 */

@Composable
fun InfoRoute(
    customKeyBoardAppState: CustomKeyBoardAppState,
    infoScreenViewModel: InfoScreenViewModel = hiltViewModel()
) {
    val infoActor by lazy { InfoActionActor(infoScreenViewModel) }
    LaunchedEffect(key1 = infoScreenViewModel.navTarget) {
        infoScreenViewModel.navTarget.onEach { state ->
            when (state) {
                InfoNavTarget.KeyBoardTestScreen -> {
                    customKeyBoardAppState.navigateRoute(
                        NavigationRoute.KeyBoardTestScreenGraph.KeyBoardTestScreen.route
                    )
                }
            }
        }.launchIn(this)
    }
    InfoScreen(
        navigateTestScreen = infoActor::navigationKeyBoardScreen
    )
}

@Composable
fun InfoScreen(
    navigateTestScreen: (InfoAction) -> Unit
) {
    var click by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = CustomKeyBoardTheme.color.white,
        bottomBar =
        {
            Box {
                BottomShadow(alpha = 0.01f, height = 66.dp)
                InfoBottomBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = CustomKeyBoardTheme.color.white
                        )
                        .align(Alignment.BottomCenter),
                    onClick = {
                        click = !click
                    }
                )
            }
        }
    ) {
        LazyColumn {
            item {
                TopBar(
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
            item {
                InfoKeyBoardItemImage(
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
            item {
                InfoTitle(
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(40.dp))
            }
            item {
                InfoMainContent(
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(48.dp))
            }
            item {
                InfoTag(
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(40.dp))
            }
            item {
                InfoKeyWord(
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(48.dp))
            }
            item {
                InfoThinkingTheme(
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(
                    modifier = Modifier.height(50.dp)
                )
            }
            item {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    painter = painterResource(id = R.drawable.img_ad),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Spacer(
                    modifier = Modifier.height(48.dp)
                )
            }
            item {
                InfoReview(
                    Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(13.dp))
            }
            itemsIndexed(
                ReviewData.List
            ) { index, item ->
                ReviewCard(
                    modifier = Modifier.padding(start = 12.dp),
                    reviewData = item,
                    isCreator = index == 0
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                Spacer(modifier = Modifier.height(64.dp))
            }
        }
        if (click) {
            ClickDialog(
                changeVisible = { click = !click },
                navigateTestScreen = navigateTestScreen
            )
        }
    }
}

@Composable
fun InfoKeyBoardItemImage(
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .shadow(10.dp, shape = RoundedCornerShape(5.dp)),
        painter = painterResource(id = R.drawable.img_info_keyboard_item),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun InfoTitle(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "앙무",
            style = CustomKeyBoardTheme.typography.title
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "코핀",
            style = CustomKeyBoardTheme.typography.allBodyMid,
            color = CustomKeyBoardTheme.color.allSubDarkGray
        )
        Spacer(modifier = Modifier.height(16.dp))
        MultiStyleText(
            style = CustomKeyBoardTheme.typography.allBodyMid,
            text1 = "78",
            color1 = CustomKeyBoardTheme.color.allMainColor,
            text2 = "명이참여했어요!",
            color2 = CustomKeyBoardTheme.color.allSubDarkGray
        )
    }
}

@Composable
fun InfoMainContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "\uD83C\uDF89플레이키보드 첫 이벤트 테마를 공개합니다.\uD83C\uDF89",
            style = CustomKeyBoardTheme.typography.allTitle
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "밀당해피니스 유튜브 채널을 방문하면 “테마명” 이벤트 테마를 무료로 받을 수 있다구요?" +
                "지금 바로 ‘참여하기' 버튼을 눌러 새로워진 밀당해피니스 유튜브 채널을 확인해보세요!",
            style = CustomKeyBoardTheme.typography.allBody
        )
    }
}

@Composable
fun InfoTag(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "태그",
            style = CustomKeyBoardTheme.typography.subTitle,
            color = CustomKeyBoardTheme.color.allTitleGray
        )
        Spacer(modifier = Modifier.height(16.dp))
        FlowRow(
            mainAxisSize = SizeMode.Expand,
            mainAxisAlignment = FlowMainAxisAlignment.Start,
            crossAxisSpacing = 8.dp,
            mainAxisSpacing = 4.12.dp

        ) {
            TagsData.List.forEach {
                Tag(
                    tagText = it.tagTitle,
                    color = CustomKeyBoardTheme.color.allBodyGray,
                    style = CustomKeyBoardTheme.typography.allBodyMid
                )
            }
        }
    }
}

@Composable
fun InfoKeyWord(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "이런 키워드에 반응해요",
            style = CustomKeyBoardTheme.typography.subTitle,
            color = CustomKeyBoardTheme.color.allTitleGray
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow() {
            itemsIndexed(KeyWordData.List) { index, item ->
                if (index == 0) {
                    Spacer(modifier = Modifier.width(2.dp))
                }
                KeyWordCard(item.image, item.text)
                Spacer(modifier = Modifier.width(16.dp))
            }
        }
        Spacer(modifier = Modifier.height(1.dp))
    }
}

@Composable
fun InfoThinkingTheme(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "이 테마를 어떻게 생각하나요?",
            style = CustomKeyBoardTheme.typography.subTitle,
            color = CustomKeyBoardTheme.color.allTitleGray
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ThemeData.List.forEachIndexed { index, themeData ->
                ThemeCard(
                    emoji = themeData.emoji,
                    text = themeData.text,
                    count = themeData.count,
                    color = if (index == 1) {
                        CustomKeyBoardTheme.color.allMainColor
                    } else {
                        CustomKeyBoardTheme.color.allSubDarkGray
                    }
                )
            }
        }
    }
}

@Composable
fun InfoReview(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        MultiStyleText(
            style = CustomKeyBoardTheme.typography.subTitle,
            text1 = "구매 리뷰 ",
            color1 = CustomKeyBoardTheme.color.allTitleGray,
            text2 = 10.toString(),
            color2 = CustomKeyBoardTheme.color.allMainColor
        )
        Spacer(modifier = Modifier.height(13.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.size(13.33.dp),
                    painter = painterResource(id = R.drawable.ic_warning),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(11.33.dp))
                Text(
                    text = "테마를 구매해야 리뷰를 남길 수 있어요."
                )
            }

            Image(
                modifier = Modifier,
                painter = painterResource(id = R.drawable.ic_drop_down),
                contentDescription = null
            )
        }
    }
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun InfoBottomBar(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .height(64.dp)
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.size(11.dp),
                    painter = painterResource(id = R.drawable.ic_zam),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(6.33.dp))
                Text(
                    text = "5",
                    color = CustomKeyBoardTheme.color.themeInfoGem,
                    style = CustomKeyBoardTheme.typography.subTitle
                )
            }
            Row {
                MultiStyleText(
                    style = CustomKeyBoardTheme.typography.subBody.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    text1 = "0젬 ",
                    color1 = CustomKeyBoardTheme.color.allMainColor,
                    text2 = "보유 중",
                    color2 = CustomKeyBoardTheme.color.allSubDarkGray
                )
            }
        }
        Box(
            modifier = Modifier
                .height(40.dp)
                .width(144.dp)
                .background(
                    color = CustomKeyBoardTheme.color.allMainColor,
                    shape = RoundedCornerShape(24.dp)
                )
                .clip(RoundedCornerShape(24.dp))
                .clickable {
                    onClick()
                }
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "구매하기",
                style = CustomKeyBoardTheme.typography.subTitle3,
                color = CustomKeyBoardTheme.color.white
            )
        }
    }
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun ClickDialog(
    changeVisible: () -> Unit,
    navigateTestScreen: (InfoAction) -> Unit
) {
    Dialog(
        onDismissRequest = {
            changeVisible()
        }
    ) {
        Surface(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(24.dp),
            color = CustomKeyBoardTheme.color.white
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.size(100.dp),
                    painter = painterResource(id = R.drawable.img_keyword_1),
                    contentDescription = null
                )
                MultiStyleText(
                    style = CustomKeyBoardTheme.typography.subTitle,
                    text1 = "N젬",
                    color1 = CustomKeyBoardTheme.color.themeInfoGem,
                    text2 = "이 부족해요\n빠르게 충전해 보세요!",
                    color2 = CustomKeyBoardTheme.color.allTitleGray
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "젬 수량",
                        style = CustomKeyBoardTheme.typography.subTitle3
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_back_arrow),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(24.dp))
                        Image(
                            modifier = Modifier.size(11.dp),
                            painter = painterResource(id = R.drawable.ic_zam),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(6.33.dp))
                        Text(
                            text = "5",
                            color = CustomKeyBoardTheme.color.themeInfoGem,
                            style = CustomKeyBoardTheme.typography.subTitle
                        )
                        Spacer(modifier = Modifier.width(24.dp))
                        Image(
                            painter = painterResource(id = R.drawable.ic_forward_arrow),
                            contentDescription = null
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "결제 금액",
                        style = CustomKeyBoardTheme.typography.subTitle3
                    )
                    Text(
                        text = "₩ 1,100",
                        style = CustomKeyBoardTheme.typography.allBodyMid,
                        color = CustomKeyBoardTheme.color.allMainColor
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(42.dp)
                        .background(
                            color = CustomKeyBoardTheme.color.allMainColor,
                            shape = RoundedCornerShape(24.dp)
                        )
                        .clip(RoundedCornerShape(24.dp))
                        .clickable {
                            navigateTestScreen(InfoAction.NavigateKeyBoard)
                        }
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "충전하고 바로 사용하기",
                        style = CustomKeyBoardTheme.typography.subTitle3,
                        color = CustomKeyBoardTheme.color.white
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun BottomShadow(alpha: Float = 0.1f, height: Dp = 8.dp) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        CustomKeyBoardTheme.color.black.copy(alpha = alpha),
                        Color.Transparent
                    )
                )
            )
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewInfoTitle() {
    CustomKeyBoardTheme {
        InfoTitle()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInfoMainContent() {
    CustomKeyBoardTheme {
        InfoMainContent()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInfoTag() {
    CustomKeyBoardTheme {
        InfoTag()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBottomBar() {
    CustomKeyBoardTheme {
//        InfoBottomBar()
    }
}
