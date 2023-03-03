package com.example.zenithtechnologiesevaluation.api

sealed class Resources<T>(val data : T? = null,val code: Int? = null, val message : String? = null) {
    class Success<T>(data: T?): Resources<T>(data)
    class Error<T>( message: String,code:Int? = null, data: T? = null):
        Resources<T>(data,code, message)
    class Loading<T>(val isLoading : Boolean = true): Resources<T>()
}
