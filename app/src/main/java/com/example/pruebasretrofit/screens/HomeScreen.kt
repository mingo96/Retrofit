/*
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
package com.example.pruebasretrofit.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pruebasretrofit.R
import com.example.pruebasretrofit.classes.MarsPhoto
import com.example.pruebasretrofit.ui.theme.PruebasRetrofitTheme
import org.jetbrains.annotations.Async

@Composable
fun HomeScreen(
    marsUiState: MarsUiState, modifier: Modifier = Modifier
) {
    when(marsUiState){
        is MarsUiState.Success->{
            ResultScreen(photos = marsUiState.Photos, modifier = Modifier.fillMaxWidth())
        }
        is MarsUiState.Error->{
            Text(text = "Error cargando los datos", modifier = Modifier.fillMaxWidth(), style = TextStyle(textAlign = TextAlign.Center))
        }
        is MarsUiState.Loading -> CircularProgressIndicator(modifier.fillMaxSize(0.8f))
    }
}

/**
 * ResultScreen displaying number of photos retrieved.
 */
@Composable
fun ResultScreen(photos: List<MarsPhoto>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(GridCells.Fixed(1)){
        items(photos){
            AsyncImage(model = it.imgSrc, contentDescription = "imagen", modifier = Modifier.padding(8.dp))
        }
    }
}
