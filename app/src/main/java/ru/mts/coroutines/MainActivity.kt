package ru.mts.coroutines

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import ru.ermolnik.news.screen.NewsScreen
import ru.ermolnik.news.screen.NewsViewModel
import ru.mts.coroutines.ui.theme.CoroutinesTheme
import ru.mts.data.news.db.NewsLocalDataSource
import ru.mts.data.news.remote.NewsRemoteDataSource
import ru.mts.data.news.repository.NewsRepository

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoroutinesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NewsScreen(
                        NewsViewModel(
                            NewsRepository(
                                NewsLocalDataSource(applicationContext),
                                NewsRemoteDataSource(),
                            )
                        )
                    )
                }
            }
        }
    }
}
