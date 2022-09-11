package ru.mts.data.news.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.mts.data.news.db.NewsLocalDataSource
import ru.mts.data.news.db.toDomain
import ru.mts.data.news.remote.NewsRemoteDataSource
import ru.mts.data.news.remote.toDomain
import ru.mts.data.news.remote.toEntity
import ru.mts.data.utils.*

class NewsRepository(
    private val newsLocalDataSource: NewsLocalDataSource,
    private val newsRemoteDataSource: NewsRemoteDataSource
) {

    suspend fun getNews(): Flow<Result<List<News>, Throwable>> {
        return flow {
            if (newsLocalDataSource.isEmpty()) {
                refreshNews()
            }
            newsLocalDataSource.getNews().collect { result ->
                this.emit(result.mapSuccess { news -> news.mapBy { toDomain() } })
            }
        }
    }

    suspend fun refreshNews(): VoidResult<Throwable> {
        return newsRemoteDataSource.getNews().mapSuccess { news ->
            newsLocalDataSource.setNews(news.mapBy { toEntity() })
        }
    }

    suspend fun clearCache(): VoidResult<Throwable> {
        return newsLocalDataSource.deleteNews()
    }

}
