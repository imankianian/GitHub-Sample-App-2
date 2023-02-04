package com.example.samplegithubapp.base

import android.app.Application
import coil.ImageLoaderFactory
import coil.ImageLoader
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SampleGitHubApplication: Application(), ImageLoaderFactory {

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .crossfade(true)
            .build()
    }
}