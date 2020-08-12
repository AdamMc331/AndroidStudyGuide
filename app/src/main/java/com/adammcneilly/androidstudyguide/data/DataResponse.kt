package com.adammcneilly.androidstudyguide.data

/**
 * This is a sealed class that represents two options for a data response, where the response is
 * either successful or a failure.
 *
 * By wrapping a response into this single type, we can provide a way for asynchronous streams to
 * handle both success and failure scenarios, without having to catch exceptions. This is because any
 * exceptions will be wrapped inside an [Error] class.
 *
 * @see ArticleRepository.fetchArticles
 */
sealed class DataResponse<T> {
    data class Success<T>(val data: T) : DataResponse<T>()
    data class Error<T>(val error: Throwable) : DataResponse<T>()
}
