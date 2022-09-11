package ru.mts.data.news.db

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import ru.mts.data.main.AppDatabase
import ru.mts.data.utils.Result
import ru.mts.data.utils.VoidResult
import ru.mts.data.utils.runOperationCatching

class NewsLocalDataSource(private val context: Context) {

    suspend fun getNews(): Flow<Result<List<NewsEntity>, Throwable>> {
        return flow {
            try {
                AppDatabase.getDatabase(context).newsDao().getAll().collect {
                    this.emit(Result.Success(it))
                }
            } catch (e: Exception) {
                this.emit(Result.Error(e))
            }
        }
    }

    suspend fun setNews(new: List<NewsEntity>): VoidResult<Throwable> {
        return runOperationCatching {
            withContext(Dispatchers.IO) {
                new.map {
                    AppDatabase.getDatabase(context).newsDao().insert(it)
                }
            }
        }
    }

    suspend fun deleteNews(): VoidResult<Throwable> {
        return runOperationCatching {
            withContext(Dispatchers.IO) {
                AppDatabase.getDatabase(context).newsDao().clearNews()
            }
        }
    }

    suspend fun isEmpty(): Boolean {
        return withContext(Dispatchers.IO) {
            AppDatabase.getDatabase(context).newsDao().getRowsCount() == 0
        }
    }

}
