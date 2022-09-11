package ru.mts.data.news.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface NewsDao {
    @Query("SELECT * FROM news")
    fun getAll(): Flow<List<NewsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(news: NewsEntity?)

    @Query("DELETE FROM news")
    fun clearNews()

    @Query("SELECT COUNT(*) AS RowCnt FROM news")
    fun getRowsCount(): Int?
}
