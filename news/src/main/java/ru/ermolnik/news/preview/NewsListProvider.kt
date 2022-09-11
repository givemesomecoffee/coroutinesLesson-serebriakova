package ru.ermolnik.news.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import ru.mts.data.news.repository.News

class NewsListProvider : PreviewParameterProvider<List<News>> {
    override val values = sequenceOf(getSampleNews(5))
}
