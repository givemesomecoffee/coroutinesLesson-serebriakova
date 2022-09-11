package ru.ermolnik.news.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.ermolnik.news.model.NewsState
import ru.mts.data.news.repository.NewsRepository
import ru.mts.data.utils.doOnError
import ru.mts.data.utils.doOnSuccess
import ru.mts.data.utils.isNetworkException

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {
    private val _state: MutableStateFlow<NewsState> = MutableStateFlow(NewsState(loading = true))
    val state = _state.asStateFlow()

    private var currentJob: Job? = null

    init {
        viewModelScope.launch {
            repository.getNews().collect {
                it.doOnSuccess { news -> _state.emit(NewsState(data = news)) }
                    .doOnError { _state.emit(NewsState(error = true)) }
            }
        }
    }

    fun refreshNews() {
        viewModelScope.launch {
            val cachedNews = state.value.data
            val job = launch {
                _state.emit(NewsState(loading = true, data = cachedNews))
                repository.refreshNews().doOnError {
                    _state.emit(
                        NewsState(
                            error = true,
                            isNetworkError = it.isNetworkException(),
                            data = state.value.data
                        )
                    )
                }
            }
            currentJob = job
            job.join()
            if (currentJob == job) currentJob = null
        }
    }

    fun deleteNewsFromCache() {
        viewModelScope.launch {
            currentJob?.cancel()
            currentJob = null
            val cachedNews = state.value.data
            _state.emit(NewsState(loading = true, data = cachedNews))
            repository.clearCache()
                .doOnSuccess { _state.emit(NewsState(loading = false)) }
                .doOnError {
                    _state.emit(
                        NewsState(
                            error = true,
                            data = state.value.data
                        )
                    )
                }
        }
    }
}
