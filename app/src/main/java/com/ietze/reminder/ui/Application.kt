package com.ietze.reminder.ui

import android.app.Application
import com.ietze.reminder.utils.di.AppModule
import com.ietze.reminder.utils.di.DaggerApplicationComponent

class Application: Application() {

    val appComponent = DaggerApplicationComponent.builder()
        .appModule(AppModule(this))
        .build()
}