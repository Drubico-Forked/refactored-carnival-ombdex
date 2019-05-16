package me.nelsoncastro.pokeapichingona.Repository

import android.util.Log
import retrofit2.Response
import java.io.IOException
import java.lang.Exception

sealed class Result<out T:Any> {
    data class Succes<out T: Any>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

open class BaseRepository {

    suspend fun <T: Any> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): T? {
        val result : Result<T> = safeApiResult(call, errorMessage)
        var data : T? = null

        when(result){
            is Result.Succes ->
                data = result.data
            is Result.Error ->
                Log.d("Base Repository", "$errorMessage & Exception ${result.exception}")
        }
        return data
    }

    private suspend fun <T: Any> safeApiResult(call: suspend () -> Response<T>, errorMessage: String) : Result<T>{
        val response = call.invoke()
        return if (response.isSuccessful) Result.Succes(response.body()!!)
        else Result.Error(IOException("Error Occurred during getting safe Api result, Custom ERROR - $errorMessage"))
    }
}