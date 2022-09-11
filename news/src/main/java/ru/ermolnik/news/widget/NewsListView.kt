package ru.ermolnik.news.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import ru.ermolnik.news.preview.NewsListProvider
import ru.mts.data.news.repository.News

@Preview
@Composable
fun NewsListView(@PreviewParameter(NewsListProvider::class) news: List<News>) {
    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        news.forEach { newsItem ->
            NewsItemView(newsItem = newsItem)
        }
    }
}