package ru.ermolnik.news.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import ru.ermolnik.news.BuildConfig
import ru.ermolnik.news.model.NewsState
import ru.ermolnik.news.preview.NewsStateProvider
import ru.ermolnik.news.widget.ErrorView
import ru.ermolnik.news.widget.NewsListView

@Composable
fun NewsScreen(viewModel: NewsViewModel) {
    val state = viewModel.state.collectAsState()
    Root(
        state = state.value,
        onRefresh = {
            viewModel.refreshNews()
        },
        onClearCache = {
            viewModel.deleteNewsFromCache()
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Root(
    @PreviewParameter(NewsStateProvider::class) state: NewsState,
    onRefresh: (() -> Unit)? = null,
    onClearCache: (() -> Unit)? = null
) {
    Box {
        SwipeRefresh(
            state = rememberSwipeRefreshState(state.loading),
            onRefresh = { onRefresh?.invoke() },
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
            ) {
                if (state.error) {
                    ErrorView(isNetworkError = state.isNetworkError)
                }

                state.data?.let {
                    NewsListView(news = it)
                }
            }

            if (BuildConfig.DEBUG) {
                ExtendedFloatingActionButton(
                    onClick = { onClearCache?.invoke() },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(10.dp),
                    text = { Text(text = "Очистить кеш") }
                )
            }
        }
    }
}






