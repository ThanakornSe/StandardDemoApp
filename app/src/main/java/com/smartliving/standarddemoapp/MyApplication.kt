package com.smartliving.standarddemoapp

import android.app.Application
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp
import scgwedotest.sdk.init.SmartLivingSDK

@HiltAndroidApp
class MyApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        initialScgHomeSdk()
    }

    private fun initialScgHomeSdk() {
        SmartLivingSDK().initialize(
            this,
            appKey = "g9w4kapf9nvx4xjqymah",
            appSecret = "9sv7wryrvd449xusvugtk4raa83vkc9t",
        )
    }
}