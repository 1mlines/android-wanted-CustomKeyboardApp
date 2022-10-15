package com.hugh.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.hugh.presentation.ui.theme.CustomKeyBoardTheme

/**
 * @Created by 김현국 2022/10/13
 */

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchBar(
    placeHolderText: String,
    navigate: () -> Unit
) {
    val focusRequest = remember { FocusRequester.Default }
    LaunchedEffect(key1 = Unit) {
        focusRequest.requestFocus()
    }
    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 5.dp).height(40.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        var text by remember { mutableStateOf("") }
        val interaction = remember { MutableInteractionSource() }
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
            },
            modifier = Modifier.weight(0.9f).fillMaxHeight()
                .background(
                    color = CustomKeyBoardTheme.color.allBoxGray,
                    shape = RoundedCornerShape(20.dp)
                ).focusRequester(focusRequest),
            cursorBrush = SolidColor(CustomKeyBoardTheme.color.allMainColor)
        ) {
            TextFieldDefaults.TextFieldDecorationBox(
                value = text,
                innerTextField = it,
                enabled = true,
                singleLine = true,
                visualTransformation = VisualTransformation.None,
                interactionSource = interaction,
                trailingIcon = {
                    if (text.isNotEmpty()) {
                        IconButton(
                            onClick = { text = "" }
                        ) {
                            Icon(imageVector = Icons.Outlined.Remove, "", tint = CustomKeyBoardTheme.color.allBoxGray)
                        }
                    }
                },
                leadingIcon = {
                    Icon(
                        modifier = Modifier.size(12.dp),
                        imageVector = Icons.Outlined.Search,
                        contentDescription = null
                    )
                },
                placeholder = {
                    Text(placeHolderText)
                },
                contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(
                    top = 0.dp,
                    bottom = 0.dp
                ),
                colors = TextFieldDefaults.textFieldColors(
                    disabledLabelColor = CustomKeyBoardTheme.color.allMainColor,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )

            )
        }
        Box(
            modifier = Modifier.weight(0.2f)
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center).clip(
                    RoundedCornerShape(5.dp)
                )
                    .clickable { navigate() },
                text = "닫기",
                style = CustomKeyBoardTheme.typography.subTitle,
                color = CustomKeyBoardTheme.color.allBtnGray
            )
        }
    }
}
