package com.ietze.reminder.data

sealed class Result<out T> {

    data class Success<out T>(val data: T): Result<T>()

    data class Fail<E>(val error: E): Result<Nothing>() where E: Error
}

sealed class Error {

    object NotExist : Error()
}
