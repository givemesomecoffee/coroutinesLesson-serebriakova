package ru.mts.data.utils

internal inline fun <O, N> List<O>.mapBy(mapper: O.() -> N): List<N> {
    return this.map { it.mapper() }
}
