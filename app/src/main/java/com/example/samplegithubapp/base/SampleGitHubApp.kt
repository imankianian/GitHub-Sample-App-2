package com.example.samplegithubapp.base

import android.app.Application
import coil.ImageLoaderFactory
import coil.ImageLoader
import coil.disk.DiskCache
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SampleGitHubApplication: Application(), ImageLoaderFactory {

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .diskCache(
                DiskCache.Builder()
                    .directory(this.cacheDir.resolve("images_cache"))
                    .maxSizePercent(0.1)
                    .build()
            )
            .crossfade(true)
            .build()
    }
}