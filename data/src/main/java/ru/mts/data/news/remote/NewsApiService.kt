package ru.mts.data.news.remote

import retrofit2.http.Headers
import retrofit2.http.POST

interface NewsApiService {

    @POST("api/v1/sample")
    @Headers("Content-Type:application/json; charset=utf-8;")
    suspend fun getSampleData(): List<NewsDto.Response>

}
