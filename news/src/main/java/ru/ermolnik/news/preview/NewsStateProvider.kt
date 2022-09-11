package ru.ermolnik.news.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import ru.ermolnik.news.model.NewsState

class NewsStateProvider : PreviewParameterProvider<NewsState> {
    override val values = sequenceOf(
        NewsState(loading = true),
        NewsState(loading = true, data = getSampleNews(2)),
        NewsState(data = getSampleNews(20)),
        NewsState(error = true, data = getSampleNews(20)),
        NewsState(error = true),
        NewsState(error = true, isNetworkError = true, data = getSampleNews(20)),
    )
}