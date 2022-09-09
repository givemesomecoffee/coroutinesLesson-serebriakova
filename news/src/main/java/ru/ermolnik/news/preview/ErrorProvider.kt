package ru.ermolnik.news.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class ErrorProvider: PreviewParameterProvider<Boolean> {
    override val values = sequenceOf(true, false)
}