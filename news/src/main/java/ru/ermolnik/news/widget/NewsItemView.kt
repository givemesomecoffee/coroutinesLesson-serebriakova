package ru.ermolnik.news.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.ermolnik.news.preview.NewsItemProvider
import ru.mts.data.news.repository.News

@Preview
@Composable
fun NewsItemView(@PreviewParameter(NewsItemProvider::class)newsItem: News) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        Text(
            text = newsItem.title,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(bottom = 4.dp)
        )
        Text(
            text = newsItem.date,
            fontSize = 12.sp,
            color = Color.DarkGray,
            modifier = Modifier
                .align(Alignment.End)
        )
    }
}