package de.skash.ticketing.core.util

sealed class ErrorType(val error: Int) {
    //TODO: Replace placeholder with actual value
    object NoInternet : ErrorType(0)
}