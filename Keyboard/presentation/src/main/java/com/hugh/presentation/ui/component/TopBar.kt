package com.hugh.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hugh.presentation.R
import com.hugh.presentation.ui.theme.CustomKeyBoardTheme

/**
 * @Created by 김현국 2022/10/12
 */

@Composable
fun TopBar(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth().height(56.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.width(18.dp).height(16.54.dp),
            painter = painterResource(
                R.drawable.ic_all_back
            ),
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInfoTopBar() {
    CustomKeyBoardTheme {
        TopBar()
    }
}