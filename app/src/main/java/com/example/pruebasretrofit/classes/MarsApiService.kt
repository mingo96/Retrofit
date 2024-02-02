package com.example.pruebasretrofit.classes

import retrofit2.Retrofit
import retrofit2.http.GET
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType

/**url a la que accederemos*/
private val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"

/**objeto que obtiene el json de la peticion web*/
private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

/**interfaz que el sistema usará para generar prompts(peticiones)*/
interface MarsApiService {
    @GET("photos")
    suspend fun getPhotos():List<MarsPhoto>
}

/**objeto singleton que se declara solo la primera ejecucion, sirve para acceder al servicio*/
object MarsApi {
    val retrofitService : MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
}