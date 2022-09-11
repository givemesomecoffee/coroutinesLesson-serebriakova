package ru.mts.data.news.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import ru.mts.data.news.db.NewsEntity
import ru.mts.data.news.repository.News


class NewsDto {

    @Parcelize
    data class Response(
        @SerializedName("id") val id: Int,
        @SerializedName("title") val title: String,
        @SerializedName("date") val date: String
    ) : Parcelable

}

internal fun NewsDto.Response.toEntity(): NewsEntity {
    return NewsEntity(
        id = this.id,
        title = this.title,
        date = this.date
    )
}

internal fun NewsDto.Response.toDomain(): News {
    return News(
        id = this.id,
        title = this.title,
        date = this.date
    )
}
