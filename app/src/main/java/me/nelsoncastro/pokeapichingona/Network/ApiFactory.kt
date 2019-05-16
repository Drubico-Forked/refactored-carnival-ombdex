package me.nelsoncastro.pokeapichingona.Network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import me.nelsoncastro.pokeapichingona.Constants.AppConstants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


object ApiFactory {

    private val authInterceptor = Interceptor {chain->
        val newUrl = chain.request().url()
                .newBuilder()
                .addQueryParameter("api_key", AppConstants.ombdApiKey)
                .build()

        val newRequest = chain.request()
                .newBuilder()
                .url(newUrl)
                .build()

        chain.proceed(newRequest)
    }

    private val ombdClient = OkHttpClient().newBuilder()
            .addInterceptor(authInterceptor) .build()

    fun retrofit() : Retrofit = Retrofit.Builder()
            .client(ombdClient)
            .baseUrl("http://www.omdbapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

    val ombdApi : OmbdApi = retrofit().create(OmbdApi::class.java)

}