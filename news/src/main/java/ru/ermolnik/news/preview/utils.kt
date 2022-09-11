package ru.ermolnik.news.preview

import ru.mts.data.news.repository.News

internal fun getSampleNews(count: Int): List<News>{
    val list = mutableListOf<News>()
    for (i in 1..count){
        list.add(News(i, getTitle(i), "10-20-2030"))
    }
    return list.toList()
}

private fun getTitle(repeatCount: Int): String{
    val text = StringBuffer()
    for (i in 1..repeatCount){
        text.append("lorem ipsum ")
    }
    return text.toString()
}
