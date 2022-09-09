package ru.ermolnik.news.screen

import ru.ermolnik.news.config.DEFAULT_ERROR_TEXT
import ru.ermolnik.news.config.NET_ERROR_TEXT

internal object UiSelectors {
    internal fun getErrorText(isNetworkError: Boolean): String {
        return if (isNetworkError) {
            NET_ERROR_TEXT
        } else {
            DEFAULT_ERROR_TEXT
        }
    }
}
