package com.hugh.presentation.ui.component

/**
 * @Created by 김현국 2022/10/13
 */

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hugh.presentation.R
import com.hugh.presentation.ui.info.data.ReviewData
import com.hugh.presentation.ui.info.data.ThemeData
import com.hugh.presentation.ui.theme.CustomKeyBoardTheme

/**
 * @Created by 김현국 2022/10/12
 */

@Composable
fun KeyWordCard(
    image: Int,
    text: String
) {
    Card(
        modifier = Modifier
            .padding(start = 5.dp)
            .width(129.dp)
            .height(162.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 5.dp
    ) {
        Box {
            Image(
                modifier = Modifier
                    .padding(
                        start = 12.dp,
                        end = 12.dp,
                        top = 10.51.dp,
                        bottom = 46.49.dp
                    )
                    .fillMaxSize(),
                painter = painterResource(id = image),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 9.56.dp),
                    text = text,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun ThemeCard(
    emoji: String,
    text: String,
    count: Int,
    color: Color
) {
    Column(
        modifier = Modifier.wrapContentWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = emoji,
            fontSize = 30.sp
        )
        Text(
            text = "$text\n $count",
            style = CustomKeyBoardTheme.typography.subBody.copy(
                lineHeight = 15.sp
            ),
            textAlign = TextAlign.Center,
            color = color
        )
    }
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun ReviewCard(
    modifier: Modifier = Modifier,
    reviewData: ReviewData,
    isCreator: Boolean
) {
    Row(
        modifier = modifier.fillMaxWidth().height(78.dp)
    ) {
        Box(
            modifier = Modifier.width(58.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier.padding(1.dp).size(48.dp),
                    painter = painterResource(R.drawable.img_profile),
                    contentDescription = null
                )
            }

            if (isCreator) {
                Column {
                    Spacer(modifier = Modifier.height(38.dp))
                    Box(
                        modifier = Modifier.fillMaxWidth().height(20.dp).background(color = CustomKeyBoardTheme.color.allMainColor, shape = RoundedCornerShape(24.dp))
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = "크리에이터",
                            style = CustomKeyBoardTheme.typography.thirdSubBody,
                            color = CustomKeyBoardTheme.color.white
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.width(11.dp))
        Column {
            Column(
                modifier = Modifier.background(color = CustomKeyBoardTheme.color.allBoxGray, shape = RoundedCornerShape(20.dp)).height(54.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier.padding(vertical = 6.dp, horizontal = 12.dp)
                ) {
                    Text(
                        text = reviewData.reviewerName,
                        style = CustomKeyBoardTheme.typography.subBody
                    )
                    Text(
                        text = reviewData.reviewText,
                        style = CustomKeyBoardTheme.typography.allBodyMid,
                        color = CustomKeyBoardTheme.color.allBodyGray
                    )
                }
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                modifier = Modifier.padding(start = 12.dp),
                text = reviewData.date,
                style = CustomKeyBoardTheme.typography.subBody,
                color = CustomKeyBoardTheme.color.allSubDarkGray
            )
        }
    }
}

@Preview
@Composable
fun PreviewKeyWordCard() {
    CustomKeyBoardTheme {
        KeyWordCard(image = R.drawable.img_keyword_3, text = "신나")
    }
}

@Preview
@Composable
fun PreviewThemeCard() {
    CustomKeyBoardTheme {
        ThemeCard(
            emoji = ThemeData.List[0].emoji,
            text = ThemeData.List[0].text,
            count = 1,
            color = CustomKeyBoardTheme.color.allMainColor
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewReviewCard() {
    CustomKeyBoardTheme {
        ReviewCard(reviewData = ReviewData.List[0], isCreator = true)
    }
}
