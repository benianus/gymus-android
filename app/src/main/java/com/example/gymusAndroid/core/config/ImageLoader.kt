package com.example.gymusAndroid.core.config

import android.content.Context
import coil3.ImageLoader
import coil3.annotation.ExperimentalCoilApi
import coil3.network.ktor3.KtorNetworkFetcherFactory
import coil3.request.crossfade
import io.ktor.client.HttpClient

@OptIn(ExperimentalCoilApi::class)
fun createImageLoader(context: Context, httpClient: HttpClient) =
        ImageLoader.Builder(context)
            .components { add(KtorNetworkFetcherFactory(httpClient)) }
            .crossfade(true)
            .build()
