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

    suspend fun getNews(forceRefresh: Boolean = false): Flow<Result<List<News>, Throwable>> {
        return flow {
            val newsResult = if (forceRefresh) {
                refreshNews()
            } else {
                getNewsFromCache()
            }
            this.emit(newsResult)
        }
    }

    private suspend fun getNewsFromCache(): Result<List<News>, Throwable> {
        return newsLocalDataSource.getNews().mapNestedSuccess { news ->
            if (news.isEmpty()) {
                refreshNews()
            } else {
                Result.Success(news.mapBy { toDomain() })
            }
        }
    }

    private suspend fun refreshNews(): Result<List<News>, Throwable> {
        return newsRemoteDataSource.getNews().mapSuccess { news ->
            newsLocalDataSource.setNews(news.mapBy { toEntity() })
            news.mapBy { toDomain() }
        }
    }

    suspend fun clearCache(): VoidResult<Throwable> {
        return newsLocalDataSource.deleteNews()
    }

}
