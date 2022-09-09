package ru.ermolnik.news.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import ru.ermolnik.news.preview.ErrorProvider
import ru.ermolnik.news.screen.UiSelectors

@Preview
@Composable
fun ErrorView(@PreviewParameter(ErrorProvider::class) isNetworkError: Boolean) {
    Text(
        text = UiSelectors.getErrorText(isNetworkError),
        modifier = Modifier
            .background(Color.LightGray)
            .padding(16.dp)
            .fillMaxWidth()
    )
}