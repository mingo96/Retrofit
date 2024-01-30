package com.example.pruebasretrofit.classes

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET


private val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"

//generacion objeto Retrofit
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface MarsApiService {
    @GET("photos")
    suspend fun getPhotos():String
}

/**objeto singleton que se declara solo la primera ejecucion, sirve para acceder al servicio*/
object MarsApi {
    val retrofitService : MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
}