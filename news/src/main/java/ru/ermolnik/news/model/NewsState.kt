package ru.ermolnik.news.model

import ru.mts.data.news.repository.News

class NewsState(
    val loading: Boolean = false,
    val data: List<News>? = null,
    val error: Boolean = false,
    val isNetworkError: Boolean = false
)