package com.example.pruebasretrofit.screens/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebasretrofit.classes.MarsApi
import com.example.pruebasretrofit.classes.MarsPhoto
import kotlinx.coroutines.launch

class MarsViewModel : ViewModel() {

    /** estado mutable que contiene el [MarsUiState] actual*/
    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
        private set

    /**
     * llamamos al metodo de obtener para tener la info cargando desde el principio
     */
    init {
        getMarsPhotos()
    }

    /**
     * recibe la info en forma de [MarsPhoto]s y actualiza los contenidos en corrutina
     */
    fun getMarsPhotos() {
        viewModelScope.launch {
            marsUiState = try {
                val listResult = MarsApi.retrofitService.getPhotos()
                MarsUiState.Success(
                    listResult
                )
            }catch (e:Exception){
                MarsUiState.Error
            }
        }
    }

}

/**define el estado actual del cargado de informacion, solo se crea uno y es universal*/
sealed interface MarsUiState{
    /**contiene la lista de [MarsPhoto]*/
    data class Success(val Photos:List<MarsPhoto>) : MarsUiState
    object Loading :MarsUiState

    object Error : MarsUiState
}