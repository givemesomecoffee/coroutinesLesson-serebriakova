package ru.ermolnik.news.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.ermolnik.news.model.NewsState
import ru.mts.data.news.repository.NewsRepository
import ru.mts.data.utils.doOnError
import ru.mts.data.utils.doOnSuccess
import ru.mts.data.utils.isNetworkException

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {
    private val _state: MutableStateFlow<NewsState> = MutableStateFlow(NewsState())
    val state = _state.asStateFlow()

    private var currentJob: Job? = null

    init {
        getNews()
    }


    fun getNews(forceRefresh: Boolean = false) {
        viewModelScope.launch {
            launchSingleJob(
                launch {
                    val previousSuccessResult = _state.value.data
                         _state.emit(NewsState(loading = true, data = previousSuccessResult))

                    repository.getNews(forceRefresh).collect {
                        it.doOnError { error ->
                            _state.emit(
                                NewsState(
                                    data = previousSuccessResult,
                                    error = true,
                                    isNetworkError = error.isNetworkException()
                                )
                            )
                        }.doOnSuccess { news ->
                            _state.emit(NewsState(data = news))
                        }
                    }
                }
            )
        }
    }

    fun deleteNewsFromCache() {
        viewModelScope.launch {
            launchSingleJob(
                launch {
                    _state.emit(NewsState(loading = true))
                    repository.clearCache().apply {
                        doOnSuccess { _state.emit(NewsState(loading = false)) }
                        doOnError {
                            _state.emit(
                                NewsState(
                                    error = true
                                )
                            )
                        }
                    }
                }
            )
        }
    }

    private suspend fun launchSingleJob(newJob: Job) {
        withContext(Dispatchers.Default) {
            currentJob?.cancel()
            currentJob = newJob
            newJob.join()
           if (currentJob?.isCompleted == true) {
               currentJob = null
           }
        }
    }

}
