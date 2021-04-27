package com.example.tvseries

import android.app.Application
import com.example.tvseries.dagger.components.BaseApplicationComponent
import com.example.tvseries.dagger.components.DaggerBaseApplicationComponent
import com.example.tvseries.dagger.modules.RetrofitModule
import com.example.tvseries.dagger.modules.RoomModule

class BaseApplication : Application() {

    companion object {
        lateinit var instance: BaseApplication
        lateinit var baseApplicationComponent: BaseApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        baseApplicationComponent = DaggerBaseApplicationComponent.builder()
            .retrofitModule(RetrofitModule())
            .roomModule(RoomModule(this))
            .build()

        baseApplicationComponent.inject(this)
        instance = this
    }

    fun getBaseApplicationComponent() = baseApplicationComponent
}