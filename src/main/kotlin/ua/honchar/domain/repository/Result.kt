package ua.honchar.domain.repository


sealed class Result<T> {
    data class Success<T>(val data: T): Result<T>()
    data class Error<T>(val message: String, val data: T? = null): Result<T>()
}