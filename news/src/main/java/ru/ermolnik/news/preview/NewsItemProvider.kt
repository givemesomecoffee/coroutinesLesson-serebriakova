package ru.ermolnik.news.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import ru.mts.data.news.repository.News

class NewsItemProvider: PreviewParameterProvider<News> {
    override val values = sequenceOf(
        News(
            id = 1,
            title = "lorem ipsum",
            date = "10-03-2022"
        )
    )
}
