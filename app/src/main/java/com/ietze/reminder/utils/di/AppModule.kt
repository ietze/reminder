package com.ietze.reminder.utils.di

import android.app.Application
import com.ietze.reminder.data.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(
    private val application: Application
) {

    @Provides
    fun provideApplication(): Application {
        return application
    }

    @Provides
    fun provideAppDatabase(application: Application): AppDatabase {
        return AppDatabase.build(application)
    }
}