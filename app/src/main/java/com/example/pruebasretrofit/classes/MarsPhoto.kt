package com.example.pruebasretrofit.classes

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**objeto serializable que se usara para enumerar las fotos*/
@Serializable
data class MarsPhoto(
    val id: String,
    /**al no poder usar el '_', lo etiquetamos como @SerialName y le decimos que el nombre de acceso sea el indicado*/
    @SerialName(value = "img_src")
    val imgSrc: String
)

