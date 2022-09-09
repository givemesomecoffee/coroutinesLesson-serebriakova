package ru.mts.data.utils

import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Throwable.isNetworkException(): Boolean{
    return this is HttpException || this is SocketTimeoutException || this is UnknownHostException
}